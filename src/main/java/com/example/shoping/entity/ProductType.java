package com.example.shoping.entity;

public enum ProductType {
    OUTER, TOP, PANTS, ETC;

    public static ProductType randomType(){
        return values()[(int)(Math.random()*values().length)];
    }
}
