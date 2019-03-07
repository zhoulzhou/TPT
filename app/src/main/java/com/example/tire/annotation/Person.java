package com.example.tire.annotation;

public class Person {

    @MyAnnotation
    @Deprecated
    public void empty(){
        System.out.println("Person empty()");
    }

    @MyAnnotation(value = {"boy", "girl"})
    public void someBody(String name, int age){
        System.out.println("Person someBody()  name= " + name + " age= " + age);
    }
}
