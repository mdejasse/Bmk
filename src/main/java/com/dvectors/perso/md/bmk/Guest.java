package com.dvectors.perso.md.bmk;

public class Guest {
    private String name;
    private String title;
    private Gender gender;
    private boolean toPrint;

    public Guest(String name, String title, Gender gender, boolean toPrint) {
        this.name = name;
        this.title = title;
        this.gender = gender;
        this.toPrint = toPrint;
    }

    public String getName() {
        if (gender == Gender.M) {
            return name.toUpperCase();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isToPrint() {
        return toPrint;
    }

    public void setToPrint(boolean toPrint) {
        this.toPrint = toPrint;
    }
}
