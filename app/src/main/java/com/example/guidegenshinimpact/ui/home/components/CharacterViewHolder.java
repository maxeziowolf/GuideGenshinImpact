package com.example.guidegenshinimpact.ui.home.components;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.models.character.CharacterGenshin;

public class CharacterViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivCharacterImage;
    private TextView  tvCharacterName;
    private View itemView;
    private CardView cvCharacter;

    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        ivCharacterImage = itemView.findViewById(R.id.ivCharacterImage);
        tvCharacterName = itemView.findViewById(R.id.tvCharacterName);
        cvCharacter = itemView.findViewById(R.id.cvCharacter);
        this.itemView = itemView;
    }

    public void  setIfo(CharacterGenshin characterGenshin){

        //cvCharacter.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.transparent));
        cvCharacter.setRadius(20.0f);
        cvCharacter.setElevation(2.0f);
        tvCharacterName.setText(characterGenshin.getName());
        setImage(characterGenshin.getBigIcon(),characterGenshin.getIcon());
        cvCharacter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle = new Bundle();
               bundle.putSerializable("character",characterGenshin);
               Navigation.findNavController(view).navigate(R.id.nav_character,bundle);
           }
       });

    }

    private void setImage(String url,String urlError){
        Glide.with(itemView).
                load(url).
                error(urlError).
                placeholder(R.drawable.icon_placeholder).
                centerCrop().
                into(ivCharacterImage);
    }




}
