package com.example.lab3

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3.fragment.InputFragment
import com.example.lab3.fragment.OutputFragment
import com.example.lab3.storage.DbContract
import com.example.lab3.storage.DbHelper

class MainActivity : AppCompatActivity(), InputFragment.FormSubmitListener {

    private val mDbHelper = DbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storageButton = findViewById<ImageButton>(R.id.storage_button)
        storageButton.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    StorageActivity::class.java
                )
            )
        }
    }

    override fun onFormSubmit(text: String, font: String) {
        if (text != "") {
            val result = saveToDatabase(text, font)
            if (result) {
                showToast(resources.getString(R.string.db_success_message))
            } else {
                showToast(resources.getString(R.string.db_failure_message))
            }
        }
        val outputFragment = OutputFragment.newInstance(text, font)
        supportFragmentManager.beginTransaction()
            .replace(R.id.output_fragment, outputFragment)
            .commit()
    }

    private fun saveToDatabase(text: String, font: String): Boolean {
        val db = mDbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DbContract.NoteEntry.COLUMN_NAME_TEXT, text)
            put(DbContract.NoteEntry.COLUMN_NAME_FONT_NAME, font)
        }

        val newId = db?.insert(DbContract.NoteEntry.TABLE_NAME, null, values)
        return newId != null
    }

    override fun onDestroy() {
        mDbHelper.close()
        super.onDestroy()
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 200)
        toast.show()
    }

}