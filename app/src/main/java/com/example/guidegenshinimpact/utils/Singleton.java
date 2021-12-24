package com.example.guidegenshinimpact.utils;

import androidx.appcompat.widget.Toolbar;

import com.example.guidegenshinimpact.models.CharacterGenshin;

import java.util.ArrayList;
import java.util.Comparator;

public class Singleton {
    private static Singleton instance;
    private ArrayList<String> listNames;
    private ArrayList<CharacterGenshin> listCharacters;
    private ArrayList<String> listElements;
    private androidx.appcompat.widget.Toolbar toolbar;

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
        listNames.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareToIgnoreCase(t1);
            }
        });
        this.listNames = listNames;
    }

    public ArrayList<CharacterGenshin> getListCharacters() {
        return listCharacters;
    }

    public void setListCharacters(ArrayList<CharacterGenshin> listCharacters) {
        listCharacters.sort(new Comparator<CharacterGenshin>() {
            @Override
            public int compare(CharacterGenshin characterGenshin, CharacterGenshin t1) {
                return characterGenshin.getName().compareToIgnoreCase(t1.getName());
            }
        });
        this.listCharacters = listCharacters;
    }

    public ArrayList<String> getListElements() {
        return listElements;
    }

    public void setListElements(ArrayList<String> listElements) {
        this.listElements = listElements;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }
}
