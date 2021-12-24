package com.example.guidegenshinimpact.ui.home.components;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.models.CharacterGenshin;

import java.util.ArrayList;

public class CharacterViewAdapter extends RecyclerView.Adapter<CharacterViewHolder>{
    private ArrayList<CharacterGenshin> characterList = new ArrayList<>();

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.character_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.setIfo(characterList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }


    public void setData(ArrayList<CharacterGenshin> characterList){
        this.characterList=characterList;
        notifyDataSetChanged();
    }

}
