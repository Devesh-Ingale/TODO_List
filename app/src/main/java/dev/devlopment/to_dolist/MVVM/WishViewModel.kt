package dev.devlopment.to_dolist.MVVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.devlopment.to_dolist.DataBase.Wish
import dev.devlopment.to_dolist.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
):ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")


    fun onWishTitleChanged(newString:String){
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString: String){
        wishDescriptionState = newString
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addAWish(wish= wish)
        }
    }

    fun getAWishById(id:Long):Flow<Wish> {
        return wishRepository.getAWishById(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish= wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteAWish(wish= wish)
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun updateWishCompletionState(id: Long, completed: Boolean, partiallyCompleted: Boolean) {
        viewModelScope.launch {
            val wish = wishRepository.getAWishById(id).first()// Assuming you are using Flow
            val updatedWish = wish.copy(completed = completed, partiallyCompleted = partiallyCompleted)
            wishRepository.updateCompletionStatus(id, completed, partiallyCompleted)
            wishRepository.updateAWish(updatedWish) // Update the wish in the database
        }
    }
}