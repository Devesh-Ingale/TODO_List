package dev.devlopment.to_dolist.UiandView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.devlopment.to_dolist.DataBase.Wish
import dev.devlopment.to_dolist.MVVM.WishViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: TextFieldValue,
    onSearchTextChanged: (TextFieldValue) -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier.padding(all = 20.dp).fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { androidx.compose.material3.Text(text = "Search", color = Color(0xFFF86B3F)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription ="create",
            )
        },

        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFF86B3F),
            unfocusedLabelColor = Color(0xFFF86B3F),
            focusedLabelColor = Color(0xFFF86B3F),
            cursorColor = Color(0xFFF86B3F),
            focusedLeadingIconColor = Color(0xFFF86B3F),
            unfocusedLeadingIconColor = Color(0xFFF86B3F),
            focusedTrailingIconColor = Color(0xFFF86B3F),
            unfocusedTrailingIconColor = Color(0xFFF86B3F),
            containerColor = Color(0xFFF86B3F).copy(
                .2f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        singleLine = true,
        shape = RoundedCornerShape(25.dp)
    )
}
@Composable
fun getFilteredVideos(viewModel: WishViewModel, searchText: String): Flow<List<Wish>> {
    return viewModel.getAllWishes.map { wishes ->
        if (searchText.isNotBlank()) {
            wishes.filter { wish ->
                wish.title.contains(searchText, ignoreCase = true) ||
                        wish.description.contains(searchText, ignoreCase = true)
            }
        } else {
            wishes
        }
    }
}