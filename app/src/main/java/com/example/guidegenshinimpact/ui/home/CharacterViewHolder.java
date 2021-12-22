package com.example.guidegenshinimpact.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.utils.StringFormatter;

public class CharacterViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivCharacterImage;
    private TextView  tvCharacterName;
    private View itemView;
    private CardView cvCharacter;
    private AutoCompleteTextView autoCompleteTextView;

    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        ivCharacterImage = itemView.findViewById(R.id.ivCharacterImage);
        tvCharacterName = itemView.findViewById(R.id.tvCharacterName);
        cvCharacter = itemView.findViewById(R.id.cvCharacter);
        this.itemView = itemView;
    }

    public void  setIfo(String characterName){
        String aux = "";
       if(characterName.contains("traveler")){
           int random = (int) Math.floor(Math.random()*2+1);
           switch (random){
               case 1:
                   Glide.with(itemView).load("https://api.genshin.dev/characters/"+characterName+"/icon-big-aether.png").into(ivCharacterImage);
                   aux = "traveler";
                   aux += "\n("+StringFormatter.upperCaseFirst(characterName.substring(aux.length()+1))+")";
                   aux = StringFormatter.upperCaseFirst(aux);
                   tvCharacterName.setText(aux);
                   break;
               case 2:
                   Glide.with(itemView).load("https://api.genshin.dev/characters/"+characterName+"/icon-big-lumine.png").into(ivCharacterImage);
                   aux = "traveler";
                   aux += "\n("+StringFormatter.upperCaseFirst(characterName.substring(aux.length()+1))+")";
                   aux = StringFormatter.upperCaseFirst(aux);
                   tvCharacterName.setText(aux);
                   break;
           }
       }else{
           Glide.with(itemView).load("https://api.genshin.dev/characters/"+characterName+"/icon-big.png").error("https://api.genshin.dev/characters/"+characterName+"/icon.png").placeholder(R.drawable.ic_menu_camera).into(ivCharacterImage);
           aux = StringFormatter.upperCaseFirst(characterName);
           tvCharacterName.setText(aux);
       }
       cvCharacter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle = new Bundle();
               bundle.putString("name",characterName);
               Log.d("Nombres:", characterName);
               Navigation.findNavController(view).navigate(R.id.nav_character,bundle);
           }
       });

    }




}
