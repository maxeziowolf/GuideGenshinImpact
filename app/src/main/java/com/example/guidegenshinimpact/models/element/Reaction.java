package com.example.guidegenshinimpact.models.element;

import java.io.Serializable;
import java.util.ArrayList;

public class Reaction implements Serializable {

    private String name;
    private ArrayList<String> elements;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getElements() {
        return elements;
    }

    public void setElements(ArrayList<String> elements) {
        this.elements = elements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
