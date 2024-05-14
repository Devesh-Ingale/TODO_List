package dev.devlopment.to_dolist.UiandView

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.devlopment.to_dolist.DataBase.Wish
import dev.devlopment.to_dolist.MVVM.WishViewModel
import dev.devlopment.to_dolist.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
){

    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val completedCheckboxState = remember { mutableStateOf(false) }
    val partiallyCompletedCheckboxState = remember { mutableStateOf(false) }
    if (id != 0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }
    val colors = listOf(Color(0xFFffe53b), Color(0xFFff2525))
    val context = LocalContext.current.applicationContext
    val paddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    val widthFraction = 0.68f

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            AppBarView(title =
            if(id != 0L) stringResource(id = R.string.update_wish)
            else stringResource(id = R.string.add_wish)
            ){
                navController.navigateUp()
            }
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            if (id != 0L) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = completedCheckboxState.value,
                        onCheckedChange = {
                            completedCheckboxState.value = it
                            partiallyCompletedCheckboxState.value = false
                            // Update wish completion state
                            viewModel.updateWishCompletionState(id, it, false)
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Completed", modifier = Modifier.clickable { completedCheckboxState.value = !completedCheckboxState.value })

                    Checkbox(
                        checked = partiallyCompletedCheckboxState.value,
                        onCheckedChange = {
                            partiallyCompletedCheckboxState.value = it
                            completedCheckboxState.value = false
                            // Update wish completion state
                            viewModel.updateWishCompletionState(id, false, it)
                        },
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text("Partially Completed", modifier = Modifier.clickable { partiallyCompletedCheckboxState.value = !partiallyCompletedCheckboxState.value })
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            GradientBorderButtonRound(
                colors = colors,
                context = context,
                paddingValues = paddingValues,
                widthFraction = widthFraction,
                id = id,
                viewModel = viewModel,
                navController = navController,
                completedCheckboxState = completedCheckboxState,
                partiallyCompletedCheckboxState = partiallyCompletedCheckboxState,
                snackMessage = snackMessage,
                scope = scope,
                scaffoldState = scaffoldState
            )
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label:String,
    value:String,
    onValueChanged: (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier.padding(top = 20.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = label, color = Color(0xFFF86B3F)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Create,
                contentDescription ="create",
                // tint = myColor5
            )
        },
        colors =TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Black,
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
        shape = RoundedCornerShape(25.dp)
    )

}
@Composable
fun GradientBorderButtonRound(
    colors: List<Color>,
    context: Context,
    paddingValues: PaddingValues,
    widthFraction: Float,
    id:Long,
    viewModel: WishViewModel,
    navController: NavController,
    completedCheckboxState: MutableState<Boolean>,
    partiallyCompletedCheckboxState: MutableState<Boolean>,
    snackMessage: MutableState<String>,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = widthFraction)
            .border(
                width = 4.dp,
                brush = Brush.horizontalGradient(colors = colors),
                shape = RoundedCornerShape(percent = 50)
            )
            // To make the ripple round
            .clip(shape = RoundedCornerShape(percent = 50))
            .clickable {
                if (viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()
                ) {
                    if (id != 0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim(),
                                completed = completedCheckboxState.value,
                                partiallyCompleted = partiallyCompletedCheckboxState.value
                            )
                        )
                        snackMessage.value = "Wish has been updated"
                    } else {
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim(),
                                completed = completedCheckboxState.value,
                                partiallyCompleted = partiallyCompletedCheckboxState.value
                            )
                        )
                        snackMessage.value = "Wish has been created"
                    }
                } else {
                    snackMessage.value = "Enter fields to create a wish"
                }
                scope.launch {
                    navController.navigateUp()
                    Toast
                        .makeText(context, snackMessage.value, Toast.LENGTH_SHORT)
                        .show()
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (id != 0L) stringResource(id = R.string.update_wish)
            else stringResource(id = R.string.add_wish),
            fontSize = 26.sp,
            modifier = Modifier.padding(paddingValues),
            fontWeight = FontWeight.Medium
        )
    }
}
