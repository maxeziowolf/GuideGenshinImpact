package com.example.guidegenshinimpact.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;

public class CharacterGenshin implements Serializable {

    private String name;
    private String vision;
    private String weapon;
    private String nation;
    private String affiliation;
    private int rarity;
    private String constellation;
    private String birthday;
    private String description;
    private ArrayList<SkillTalent> skillTalents;
    private ArrayList<PassiveTalent> passiveTalents;
    private ArrayList<Constellation> constellations;
    private String vision_key;
    private String weapon_type;
    private String bigIcon;
    private String icon;
    private String gachaSplash;
    private String portrait;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<SkillTalent> getSkillTalents() {
        return skillTalents;
    }

    public void setSkillTalents(ArrayList<SkillTalent> skillTalents) {
        this.skillTalents = skillTalents;
    }

    public ArrayList<PassiveTalent> getPassiveTalents() {
        return passiveTalents;
    }

    public void setPassiveTalents(ArrayList<PassiveTalent> passiveTalents) {
        this.passiveTalents = passiveTalents;
    }

    public ArrayList<Constellation> getConstellations() {
        return constellations;
    }

    public void setConstellations(ArrayList<Constellation> constellations) {
        this.constellations = constellations;
    }

    public String getVision_key() {
        return vision_key;
    }

    public void setVision_key(String vision_key) {
        this.vision_key = vision_key;
    }

    public String getWeapon_type() {
        return weapon_type;
    }

    public void setWeapon_type(String weapon_type) {
        this.weapon_type = weapon_type;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGachaSplash() {
        return gachaSplash;
    }

    public void setGachaSplash(String gachaSplash) {
        this.gachaSplash = gachaSplash;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
