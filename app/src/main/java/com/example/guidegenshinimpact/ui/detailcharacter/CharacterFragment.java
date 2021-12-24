package com.example.guidegenshinimpact.ui.detailcharacter;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.databinding.FragmentCharacterBinding;
import com.example.guidegenshinimpact.models.CharacterGenshin;
import com.example.guidegenshinimpact.utils.Singleton;
import com.example.guidegenshinimpact.utils.StringFormatter;

public class CharacterFragment extends Fragment {

    private TextView  tvCharacterDescripcion;
    private TextView  tvCharacterElement;
    private FragmentCharacterBinding binding;
    private CharacterGenshin character;
    private ImageView ivCharacterImage;
    private ImageView ivIconElements;
    private TextView tvCharacterName;
    private LottieAnimationView loadProgress;
    private int colorToolOriginal;
    private LottieAnimationView stars[];

    public static CharacterFragment newInstance() {
        return new CharacterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=  FragmentCharacterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        stars = new LottieAnimationView[5];
        stars[0] = binding.start1;
        stars[1] = binding.start2;
        stars[2] = binding.start3;
        stars[3] = binding.start4;
        stars[4] = binding.start5;
        tvCharacterElement = binding.tvCharacterElementDetail;
        ivIconElements = binding.ivElelemntIcon;
        ivCharacterImage = binding.ivDetailCharacterImage;
        tvCharacterName = binding.tvCharacterNameDetail;
        loadProgress = binding.loadProgressbar;
        tvCharacterDescripcion = binding.tvCharacterDescriptionDetail;

        character = (CharacterGenshin) getArguments().getSerializable("character");

        setCharacterInformation();

        return root;
    }

    private void setCharacterInformation() {
        findElement();
        tvCharacterName.setText(character.getName());
        tvCharacterDescripcion.setText(character.getDescription());
        loadImage();
        loadStars();

    }

    private void loadStars() {
        for (int i=0; i < character.getRarity(); i++ ){
            stars[i].playAnimation();
        }
    }

    private void findElement() {
        int color=1;
        androidx.appcompat.widget.Toolbar toolbar = Singleton.getInstance().getToolbar();

        switch (character.getVision()){
            case "Anemo":
                color = Color.rgb(0, 211, 200);
                ivIconElements.setImageResource(R.drawable.icon_anemo);
                tvCharacterElement.setText(character.getVision());
                break;
            case "Cryo":
                color = Color.rgb(101, 197, 243);
                ivIconElements.setImageResource(R.drawable.icon_cryo);
                tvCharacterElement.setText(character.getVision());
                break;
            case "Dentro":
                color = Color.rgb(31, 180, 0);
                ivIconElements.setImageResource(R.drawable.icon_dendro);
                tvCharacterElement.setText(character.getVision());
                break;
            case "Electro":
                color = Color.rgb(123, 0, 137);
                ivIconElements.setImageResource(R.drawable.icon_electro);
                tvCharacterElement.setText(character.getVision());
                break;
            case "Geo":
                color = Color.rgb(229, 169, 0);
                ivIconElements.setImageResource(R.drawable.icon_geo);
                tvCharacterElement.setText(character.getVision());
                break;
            case "Hydro":
                color = Color.rgb( 0, 80, 255);
                ivIconElements.setImageResource(R.drawable.icon_hydro);
                tvCharacterElement.setText(character.getVision());
                break;
            case "Pyro":
                color = Color.rgb(207, 0, 0);
                ivIconElements.setImageResource(R.drawable.icon_pyro);
                tvCharacterElement.setText(character.getVision());
                break;
            default:
                color = Color.rgb(13, 13, 13 );
                ivIconElements.setImageResource(R.drawable.icon_placeholder);
                tvCharacterElement.setText(character.getVision());
                break;
        }

        colorToolOriginal = toolbar.getDrawingCacheBackgroundColor();
        toolbar.setBackgroundColor(color);
    }

    private void loadImage(){
        RequestListener<Drawable> request = new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                loadProgress.pauseAnimation();
                loadProgress.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                loadProgress.setVisibility(View.GONE);
                return false;
            }
        };
        loadProgress.setVisibility(View.VISIBLE);
        loadProgress.playAnimation();

        Glide.with(getActivity()).
                load(character.getGachaSplash()).
                error(character.getPortrait()).
                centerCrop().
                addListener(request).
                into(ivCharacterImage);
    }

    @Override
    public void onPause() {
        super.onPause();
        Singleton.getInstance().getToolbar().setBackgroundColor(colorToolOriginal);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}