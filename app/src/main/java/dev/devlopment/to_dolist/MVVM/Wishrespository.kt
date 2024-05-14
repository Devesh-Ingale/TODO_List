package dev.devlopment.to_dolist.MVVM

import android.content.ContentValues
import android.util.Log
import dev.devlopment.to_dolist.DataBase.Wish
import dev.devlopment.to_dolist.DataBase.WishDao
import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {

    suspend fun addAWish(wish: Wish){
        wishDao.addAWish(wish)
    }

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWishById(id:Long) :Flow<Wish> {
        return wishDao.getAWishById(id)
    }

    suspend fun updateAWish(wish:Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }

    suspend fun updateCompletionStatus(id: Long, completed: Boolean, partiallyCompleted: Boolean) {
        Log.d(ContentValues.TAG, "Updating completion status for wish with id $id. completed: $completed, partiallyCompleted: $partiallyCompleted")
        wishDao.updateCompletionStatus(id, completed, partiallyCompleted)
        Log.d(ContentValues.TAG, "Completion status updated successfully")
    }

}