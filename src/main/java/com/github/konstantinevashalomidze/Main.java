package com.github.konstantinevashalomidze;

import com.github.konstantinevashalomidze.ansi.Ansi;
import com.github.konstantinevashalomidze.ansi.Style;

public class Main {
    public static void main(String[] args) {
        System.out.println(Ansi.foreground(Style.Color.RED).foreground(Style.Color.RED).apply("text"));
    }
}