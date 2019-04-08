package com.example.tire.reflect;

public class User {
    private String name;
    public int id;

    public User(){
        name = "default";
        id = -1;
    }

    public User(int id){
        name = "default";
        this.id= id;
    }

    public User(String name){
        this.name = name;
        id = -111;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String outInfo(){
        return "id= " + id + " name= " + name;
    }

    public void output(){
        System.out.println("id = -111 name= default000");
    }
}
