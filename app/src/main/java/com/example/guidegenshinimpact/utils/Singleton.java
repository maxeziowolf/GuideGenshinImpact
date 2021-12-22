package com.example.guidegenshinimpact.utils;

import com.example.guidegenshinimpact.models.CharacterGenshin;

import java.util.ArrayList;

public class Singleton {
    private static Singleton instance;
    private ArrayList<String> listNames;
    private ArrayList<CharacterGenshin> listCharacters;

    public static Singleton getInstance(){
        if(instance == null){
            return instance = new Singleton();
        }else{
            return instance;
        }
    }

    public ArrayList<String> getListNames() {
        return listNames;
    }

    public void setListNames(ArrayList<String> listNames) {
        this.listNames = listNames;
    }

    public ArrayList<CharacterGenshin> getListCharacters() {
        return listCharacters;
    }

    public void setListCharacters(ArrayList<CharacterGenshin> listCharacters) {
        this.listCharacters = listCharacters;
    }
}
