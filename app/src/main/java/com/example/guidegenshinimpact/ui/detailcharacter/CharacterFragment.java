package com.example.guidegenshinimpact.ui.detailcharacter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.databinding.FragmentCharacterBinding;
import com.example.guidegenshinimpact.models.character.CharacterGenshin;
import com.example.guidegenshinimpact.models.element.Element;
import com.example.guidegenshinimpact.utils.Singleton;
import com.example.guidegenshinimpact.utils.StringFormatter;

public class CharacterFragment extends Fragment {

    private final String withOutDescription = "No tiene una descripción oficial.";
    private FragmentCharacterBinding binding;
    private CharacterGenshin character;
    private ImageView ivCharacterImage;
    private ImageView ivIconElements;
    private ImageView ivIconNation;
    private TextView tvCharacterNation;
    private TextView tvCharacterAfiliacion;
    private TextView tvCharacterBirthday;
    private TextView tvCharacterDescripcion;
    private TextView tvCharacterElement;
    private TextView tvCharacterName;
    private TextView tvTypeWeapon;
    private LottieAnimationView loadProgress;
    private int colorToolOriginal;
    private LottieAnimationView stars[];

    public static CharacterFragment newInstance() {
        return new CharacterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        stars = new LottieAnimationView[5];
        stars[0] = binding.start1;
        stars[1] = binding.start2;
        stars[2] = binding.start3;
        stars[3] = binding.start4;
        stars[4] = binding.start5;
        tvCharacterElement = binding.tvCharacterElementDetail;
        ivIconElements = binding.ivElelemntIconDetail;
        ivCharacterImage = binding.ivDetailCharacterImage;
        tvCharacterName = binding.tvCharacterNameDetail;
        tvTypeWeapon = binding.tvCharacterWeaponDetail;
        loadProgress = binding.loadProgressbar;
        ivIconNation = binding.ivNationIcon;
        tvCharacterNation = binding.tvCharacterNationDetail;
        tvCharacterAfiliacion = binding.tvCharacterAffiliationDetail;
        tvCharacterBirthday = binding.tvCharacterBirthdayDetail;

        tvCharacterDescripcion = binding.tvCharacterDescriptionDetail;

        character = (CharacterGenshin) getArguments().getSerializable("character");

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        setCharacterInformation();
    }

    private void setCharacterInformation() {
        String description = character.getDescription().isEmpty() ? withOutDescription : character.getDescription();
        String birthday = StringFormatter.formattingDate(character.getBirthday());

        findElement();
        tvCharacterName.setText(character.getName());
        tvCharacterNation.setText(character.getNation());
        tvTypeWeapon.setText(character.getWeapon());
        tvCharacterAfiliacion.setText(character.getAffiliation());
        tvCharacterDescripcion.setText(description);
        tvCharacterBirthday.setText(birthday);
        loadImage();
        loadStars();

    }

    private void loadStars() {
        for (int i = 0; i < character.getRarity(); i++) {
            stars[i].playAnimation();
        }
    }

    private void findElement() {
        int color = 1;
        androidx.appcompat.widget.Toolbar toolbar = Singleton.getInstance().getToolbar();

        switch (character.getVision()) {
            case "Anemo":
                color = Color.parseColor("#00938B");
                tvCharacterName.setBackground(getResources().getDrawable(R.drawable.card_name_character_anemo));
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
            case "Cryo":
                color = Color.parseColor("#5297B8");
                tvCharacterName.setBackground(getResources().getDrawable(R.drawable.card_name_character_cryo));
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
            case "Dentro":
                color = Color.parseColor("#136E00");
                tvCharacterName.setBackground(getResources().getDrawable(R.drawable.card_name_character_dendro));
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
            case "Electro":
                color = Color.parseColor("#4B0053");
                tvCharacterName.setBackground(getResources().getDrawable(R.drawable.card_name_character_electro));
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
            case "Geo":
                color = Color.parseColor("#8F6A01");
                tvCharacterName.setBackground(getResources().getDrawable(R.drawable.card_name_character_geo));
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
            case "Hydro":
                color = Color.rgb(0, 80, 255);
                tvCharacterName.setBackground(getResources().getDrawable(R.drawable.card_name_character_hydro));
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
            case "Pyro":
                color = Color.parseColor("#8C0000");
                tvCharacterName.setBackground(getResources().getDrawable(R.drawable.card_name_character_pyro));
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
            default:
                color = Color.rgb(13, 13, 13);
                for (Element element : Singleton.getInstance().getListElements()) {
                    if(element.getName().equalsIgnoreCase(character.getVision())){
                        Glide.with(getActivity()).
                                load(element.getIcon()).
                                error(R.drawable.icon_anemo).
                                centerCrop().
                                into(ivIconElements);
                    }
                }
                tvCharacterElement.setText(character.getVision());
                break;
        }

        colorToolOriginal = toolbar.getDrawingCacheBackgroundColor();
        toolbar.setBackgroundColor(color);
    }

    private void loadImage() {
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
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Singleton.getInstance().getToolbar().setBackgroundColor(colorToolOriginal);
    }
}