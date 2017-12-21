package com.example.lukakordi.learningkotlin.ui

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lukakordi.learningkotlin.R
import com.example.lukakordi.learningkotlin.data_models.Habit
import kotlinx.android.synthetic.main.single_card.view.*

class HabitsAdapter(private val habits: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    class HabitViewHolder(val card: View) : RecyclerView.ViewHolder(card)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)

        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder?, position: Int) {
        if (holder != null) {
            val habit = habits[position]
            holder.card.habitTitle.text = habit.title
            holder.card.description.text = habit.description
            holder.card.icon.setImageURI(Uri.parse(habit.imagePath))
        }
    }

    override fun getItemCount() = habits.size
}
