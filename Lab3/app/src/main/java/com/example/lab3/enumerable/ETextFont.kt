package com.example.lab3.enumerable

import android.graphics.Typeface
import java.util.*

enum class ETextFont(val typeface: Typeface, val fontName: String) {

    MONOSPACE(Typeface.create("monospace", Typeface.NORMAL), "monospace"),
    CURSIVE(Typeface.create("cursive", Typeface.NORMAL), "cursive"),
    CASUAL(Typeface.create("casual", Typeface.NORMAL), "casual"),
    SANS_SERIF(Typeface.create("sans-serif", Typeface.NORMAL), "sans-serif"),
    SANS_SERIF_BLACK(Typeface.create("sans-serif-black", Typeface.NORMAL), "sans-serif-black");

    companion object {

        fun getTextFontByTypeface(typeface: Typeface): ETextFont = Arrays.stream(values())
            .filter { e -> e.typeface == typeface }
            .findAny()
            .orElse(null)

        fun getTextFontByName(fontName: String): ETextFont = Arrays.stream(values())
            .filter { e -> e.fontName == fontName }
            .findAny()
            .orElse(null)
    }

}