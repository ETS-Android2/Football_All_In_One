package com.example.football_all_in_one.model.players_response;

public class Player {
    private int id;
    private String name;
    private int age;
    private String photo;
    private String nationality;
    private String height;
    private String weight;
    private boolean injured;
    private Birth birth;

    public Player(int id, String name, int age, String photo, String nationality, String height, String weight, boolean injured, Birth birth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.photo = photo;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.injured = injured;
        this.birth = birth;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public String getPhoto() { return photo; }

    public String getNationality() { return nationality; }

    public String getHeight() { return height; }

    public String getWeight() { return weight; }

    public boolean isInjured() {
        return injured;
    }

    public Birth getBirth() { return birth; }
}




