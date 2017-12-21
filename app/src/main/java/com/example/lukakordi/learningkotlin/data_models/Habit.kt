package com.example.lukakordi.learningkotlin.data_models

import android.content.Context
import com.example.lukakordi.learningkotlin.RealmSingle
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.where

open class Habit(@PrimaryKey @Index var title: String = "", @Index var description: String = "", @Index var imagePath: String) : RealmObject() {
    constructor() : this("", "", "")
}

fun getHabitsFromRealm(context: Context): List<Habit> {
    val realm = RealmSingle.getRealmInstance(context)
    return realm.copyFromRealm(realm.where<Habit>().findAll())
}