package com.knoxpo.beatbox.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.knoxpo.beatbox.model.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tejas Sherdiwala on 11/30/2016.
 * &copy; Knoxpo
 */

public class BeatBox {
    private static final String
            TAG = BeatBox.class.getSimpleName(),
            SOUND_FOLDER = "sample_sounds";
    private static final int MAX_SOUND = 5;
    private AssetManager mAssetManager;
    private List<Sound> mSoundList;
    private SoundPool mSoundPool;

    public BeatBox(Context context){
        mAssetManager = context.getAssets();
        mSoundList = new ArrayList<>();
        mSoundPool = new SoundPool(MAX_SOUND, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }

    public void release(){
        mSoundPool.release();
    }
    private void loadSounds(){
        String soundNames[]=null;
        try{
            soundNames = mAssetManager.list(SOUND_FOLDER);

            Log.d(TAG, "loadSounds: founds "+soundNames.length + "Songs");
        }catch (IOException e){
            Log.e(TAG,"Could not list assets",e);
        }
        for(String filename : soundNames){
            try {
                String assetPath = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSoundList.add(sound);
            }catch (IOException e){
                Log.e(TAG,"Could not load Sound",e);
            }
        }
    }

    private void load(Sound sound)throws IOException{
        AssetFileDescriptor afd = mAssetManager.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }

    public List<Sound> getSoundList() {
        return mSoundList;
    }
}
