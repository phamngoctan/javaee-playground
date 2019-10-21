package com.javaee.cdi.beanmanager;

public class SmileyGreeting extends SimpleGreeting {

    @Override
    public String greet(String name) {
        return super.greet(name) + " :-)";
    }

}