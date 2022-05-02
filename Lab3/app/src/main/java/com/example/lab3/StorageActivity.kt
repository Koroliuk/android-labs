package com.example.lab3

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import com.example.lab3.storage.DbContract
import com.example.lab3.storage.DbHelper

class StorageActivity : AppCompatActivity() {

    private val mDbHelper = DbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        val backToMainButton = findViewById<ImageButton>(R.id.back_to_main_button)
        backToMainButton.setOnClickListener { finish() }

        val linearLayout = findViewById<LinearLayout>(R.id.info)
        val notes = getNotesFromDatabase()
        if (notes.isEmpty()) {
            val message = createMessageForEmptyDb(this)
            linearLayout.addView(message)
        } else {
            val table = createTableForDb(notes, this)
            linearLayout.addView(table)
        }
    }

    private fun createTableForDb(notes: List<Pair<String, String>>, context: Context): ScrollView {
        val scrollView = ScrollView(context)
        scrollView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val tableLayout = TableLayout(context)
        tableLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val header = createTableHeader(context)
        tableLayout.addView(header)

        var count = 1
        notes.forEach { pair ->
            val (name, fontName) = pair
            val tableRow = createTableRow(count, name, fontName, context)
            tableLayout.addView(tableRow)
            count++
        }

        scrollView.addView(tableLayout)
        return scrollView
    }

    private fun createTableRow(
        num: Int,
        text: String,
        fontName: String,
        context: Context
    ): TableRow {
        val tableRow = TableRow(context)
        tableRow.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val backgroundColor: Int = if (num % 2 == 0) {
            Color.parseColor("#E0E4EC")
        } else {
            Color.WHITE
        }

        arrayListOf(num.toString(), text, fontName)
            .forEach { s ->
                val cell = createCell(s, Color.BLACK, backgroundColor, null, context)
                tableRow.addView(cell)
            }

        return tableRow
    }

    private fun createTableHeader(context: Context): TableRow {
        val header = TableRow(context)
        header.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        arrayListOf("No.", "Text", "Font name")
            .forEach { s ->
                val cell = createCell(
                    s, Color.WHITE, Color.parseColor("#789FFA"),
                    Typeface.DEFAULT_BOLD, context
                )
                header.addView(cell)
            }
        return header
    }

    private fun createCell(
        text: String, textColor: Int, backgroundColor: Int, typeface: Typeface?,
        context: Context
    ): TextView {
        val textView = TextView(context)
        textView.layoutParams = TableRow.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )
        textView.gravity = Gravity.CENTER
        textView.setPadding(30)

        textView.text = text
        textView.textSize = 18F
        typeface.let {
            textView.typeface = typeface
        }
        textView.setBackgroundColor(backgroundColor)
        textView.setTextColor(textColor)

        return textView
    }

    private fun createMessageForEmptyDb(context: Context): View {
        val textView = TextView(context)
        textView.text = resources.getString(R.string.empty_storage)
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setTextColor(Color.BLACK)
        textView.gravity = Gravity.CENTER
        textView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return textView
    }

    private fun getNotesFromDatabase(): List<Pair<String, String>> {
        val db = mDbHelper.readableDatabase
        val projection = arrayOf(
            DbContract.NoteEntry.COLUMN_NAME_TEXT,
            DbContract.NoteEntry.COLUMN_NAME_FONT_NAME
        )

        val cursor = db.query(
            DbContract.NoteEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            BaseColumns._ID
        )

        val notes = mutableListOf<Pair<String, String>>()
        while (cursor.moveToNext()) {
            val textIndex = cursor.getColumnIndexOrThrow(DbContract.NoteEntry.COLUMN_NAME_TEXT)
            val fonNameIndex =
                cursor.getColumnIndexOrThrow(DbContract.NoteEntry.COLUMN_NAME_FONT_NAME)
            val text = cursor.getString(textIndex)
            val fontName = cursor.getString(fonNameIndex)
            val note = Pair(text, fontName)
            notes.add(note)
        }

        cursor.close()
        return notes
    }

}





