package com.example.lukakordi.learningkotlin

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

object RealmSingle {

    fun getRealmInstance(context: Context): Realm{
        Realm.init(context)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
        return Realm.getDefaultInstance()
    }
}