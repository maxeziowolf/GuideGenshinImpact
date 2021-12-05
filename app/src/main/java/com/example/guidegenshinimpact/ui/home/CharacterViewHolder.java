package com.example.guidegenshinimpact.ui.home;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guidegenshinimpact.R;

public class CharacterViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivCharacterImage;
    private TextView  tvCharacterName;
    private View itemView;

    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        ivCharacterImage = itemView.findViewById(R.id.ivCharacterImage);
        tvCharacterName = itemView.findViewById(R.id.tvCharacterName);
        this.itemView = itemView;
    }

    public void  setIfo(String characterName){
        String aux = "";
       if(characterName.contains("traveler")){
           Glide.with(itemView).load("https://api.genshin.dev/characters/"+characterName+"/icon-big-aether.png").into(ivCharacterImage);
           aux = "traveler";
           aux += "\n("+upperCaseFirst(characterName.substring(aux.length()+1))+")";
           aux = upperCaseFirst(aux);
           tvCharacterName.setText(aux);
       }else{
           Glide.with(itemView).load("https://api.genshin.dev/characters/"+characterName+"/icon-big.png").into(ivCharacterImage);
           aux = upperCaseFirst(characterName);
           tvCharacterName.setText(aux);
       }
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }
}
