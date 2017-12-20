package com.example.lukakordi.learningkotlin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.lukakordi.learningkotlin.R
import com.example.lukakordi.learningkotlin.data_models.getHabitsFromRealm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView.setHasFixedSize(true)
        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = HabitsAdapter(getHabitsFromRealm(this))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.new_habit) {
            startActivity(CreateHabitActivity.getLaunchIntent(this))
        }
        return true
    }

    companion object {
        fun getLaunchIntent(from: Context): Intent {
            return Intent(from, MainActivity::class.java)
        }
    }
}
