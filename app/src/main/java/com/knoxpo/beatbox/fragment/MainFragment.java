package com.knoxpo.beatbox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.knoxpo.beatbox.R;
import com.knoxpo.beatbox.model.Sound;
import com.knoxpo.beatbox.utils.BeatBox;

import java.util.List;

/**
 * Created by Tejas Sherdiwala on 11/30/2016.
 * &copy; Knoxpo
 */

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mSoundListRV;
    private SoundAdapter mSoundAdapter;
    private BeatBox mBeatBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        mBeatBox = new BeatBox(getActivity());
        init(rootView);
        mSoundListRV.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mSoundListRV.setAdapter(mSoundAdapter);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    private void init(View view){
        mSoundListRV = (RecyclerView) view.findViewById(R.id.rv_beat_box);
        mSoundAdapter = new SoundAdapter(mBeatBox.getSoundList());
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private LayoutInflater mInflater;
        private List<Sound> mSoundList;
        public SoundAdapter(List<Sound> sound){
            mInflater = LayoutInflater.from(getActivity());
            mSoundList = sound;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.list_item_sound,parent,false);
            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
                Sound sound = mSoundList.get(position);
                holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSoundList.size();
        }
    }
    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button mSoundBtn;
        private Sound mSound;

        public SoundHolder(View viewItem){
            super(viewItem);
            mSoundBtn = (Button) viewItem.findViewById(R.id.btn_sound);
            mSoundBtn.setOnClickListener(this);
        }
        public void bindSound(Sound sound){
            mSound = sound;
            mSoundBtn.setText(mSound.getName());
        }

        @Override
        public void onClick(View view) {
            mBeatBox.play(mSound);
        }
    }
}
