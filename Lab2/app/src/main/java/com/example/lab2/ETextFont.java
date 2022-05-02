package com.example.lab2;

import android.graphics.Typeface;

import java.util.Arrays;

public enum ETextFont {

    MONOSPACE(Typeface.create("monospace", Typeface.NORMAL), "monospace"),
    CURSIVE(Typeface.create("cursive", Typeface.NORMAL), "cursive"),
    CASUAL(Typeface.create("casual", Typeface.NORMAL), "casual"),
    SANS_SERIF(Typeface.create("sans-serif", Typeface.NORMAL), "sans-serif"),
    SANS_SERIF_BLACK(Typeface.create("sans-serif-black", Typeface.NORMAL), "sans-serif-black");

    private final Typeface typeface;
    private final String name;

    ETextFont(Typeface typeface, String name) {
        this.typeface = typeface;
        this.name = name;
    }

    public static ETextFont getTextFontByTypeface(Typeface typeface) {
        return Arrays.stream(ETextFont.values())
                .filter(eTextFont -> eTextFont.getTypeface().equals(typeface))
                .findAny()
                .orElse(null);
    }

    public static ETextFont getTextFontByName(String name) {
        return Arrays.stream(ETextFont.values())
                .filter(eTextFont -> eTextFont.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public String getName() {
        return name;
    }
}
