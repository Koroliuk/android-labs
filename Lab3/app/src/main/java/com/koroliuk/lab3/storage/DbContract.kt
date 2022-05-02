package com.koroliuk.lab3.storage

import android.provider.BaseColumns

object DbContract {

    object NoteEntry : BaseColumns {

        const val TABLE_NAME = "notes"
        const val COLUMN_NAME_TEXT = "text"
        const val COLUMN_NAME_FONT_NAME = "font_name"

    }

}
