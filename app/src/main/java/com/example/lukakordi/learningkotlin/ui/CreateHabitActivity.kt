package com.example.lukakordi.learningkotlin.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import com.example.lukakordi.learningkotlin.R
import com.example.lukakordi.learningkotlin.RealmSingle
import com.example.lukakordi.learningkotlin.data_models.Habit
import kotlinx.android.synthetic.main.activity_create_habit.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {

    private val CHOOSE_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)

        chooseImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"

            val chooser = Intent.createChooser(intent, "Choose image for habit")
            startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)
        }

        saveHabbitBtn.setOnClickListener {
            if (!newHabitTitle.isBlank() && !newHabitDescription.isBlank() && newHabitIcon.drawable != null) {
                val realmInstance = RealmSingle.getRealmInstance(this)
                realmInstance.executeTransaction {
                    realmInstance.copyToRealmOrUpdate(Habit(newHabitTitle.text.toString(),
                            newHabitDescription.text.toString(), imageUri.toString()))
                }
                startActivity(MainActivity.getLaunchIntent(this))
            } else if (newHabitTitle.isBlank() || newHabitDescription.isBlank())
                displayErrorMessage("Your habit needs an engaging title and description")
            else
                displayErrorMessage("Add a motivating picture to your habit")
        }
    }

    private fun displayErrorMessage(message: String) {
        error.text = message
        error.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val imageUri = data.data
            imageUri?.let {
                this.imageUri = imageUri
                newHabitIcon.setImageURI(this.imageUri)
            }
        }
    }

    companion object {
        fun getLaunchIntent(from: Context): Intent {
            return Intent(from, CreateHabitActivity::class.java)
        }
    }
}

fun EditText.isBlank() = this.text.toString().isBlank()

