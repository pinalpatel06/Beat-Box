package com.knoxpo.beatbox.model;

/**
 * Created by Tejas Sherdiwala on 11/30/2016.
 * &copy; Knoxpo
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String path){
        mAssetPath = path;
        String[] components = mAssetPath.split("/");
        String fileName = components[components.length-1];
        mName = fileName.replace(".wav","");

    }
    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
