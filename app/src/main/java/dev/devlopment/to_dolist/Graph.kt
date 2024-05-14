package dev.devlopment.to_dolist

import android.content.Context
import androidx.room.Room
import dev.devlopment.to_dolist.DataBase.WishDatabase
import dev.devlopment.to_dolist.MVVM.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy{
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }

}