package com.example.guidegenshinimpact.ui.detailcharacter;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.databinding.FragmentCharacterBinding;
import com.example.guidegenshinimpact.models.CharacterGenshin;
import com.example.guidegenshinimpact.ui.home.CharacterViewHolder;
import com.example.guidegenshinimpact.utils.StringFormatter;

public class CharacterFragment extends Fragment {

    private CharacterViewModel mViewModel;
    private FragmentCharacterBinding binding;
    private CharacterGenshin character;
    private ImageView ivCharacterImage;
    private TextView tvCharacterName;
    private String name;
    private ProgressBar loadProgress;
    private FrameLayout loadPregressGeneral;

    public static CharacterFragment newInstance() {
        return new CharacterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=  FragmentCharacterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        mViewModel.getCharacterGenshin().observe(getViewLifecycleOwner(), new Observer<CharacterGenshin>() {
            @Override
            public void onChanged(CharacterGenshin characterGenshin) {
                 if(characterGenshin != null){
                     character = characterGenshin;
                     setCharacterInformation();
                 }
            }
        });

        ivCharacterImage = binding.ivDetailCharacterImage;
        tvCharacterName = binding.tvCharacterNameDetail;
        loadProgress = binding.loadProgressbar;
        loadPregressGeneral = binding.loadProgressbarGeneral;

        name = getArguments().getString("name");

        mViewModel.findCharacter(name);

        return root;
    }

    private void setCharacterInformation() {
        loadPregressGeneral.setVisibility(View.VISIBLE);
        RequestListener<Drawable> request = new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                loadProgress.setVisibility(View.GONE);
                return false;
            }
        };
        String aux = "";
        loadProgress.setVisibility(View.VISIBLE);
        if(name.contains("traveler")){
            int random = (int) Math.floor(Math.random()*2+1);
            switch (random){
                case 1:
                    Glide.with(getActivity()).load("https://api.genshin.dev/characters/"+name+"/portraitf.png").addListener(request).into(ivCharacterImage);
                    aux = StringFormatter.upperCaseFirst(character.getName());
                    tvCharacterName.setText(aux);
                    break;
                case 2:
                    Glide.with(getActivity()).load("https://api.genshin.dev/characters/"+name+"/portraitm.png").addListener(request).into(ivCharacterImage);
                    aux = StringFormatter.upperCaseFirst(character.getName());
                    tvCharacterName.setText(aux);
                    break;
            }

        }else{
            aux = StringFormatter.upperCaseFirst(character.getName());
            Glide.with(ivCharacterImage).load("https://api.genshin.dev/characters/"+name+"/gacha-splash.png").addListener(request).into(ivCharacterImage);
            tvCharacterName.setText(aux);
        }
        loadPregressGeneral.setVisibility(View.GONE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}