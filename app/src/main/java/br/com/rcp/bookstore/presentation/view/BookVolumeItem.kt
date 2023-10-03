package br.com.rcp.bookstore.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import br.com.rcp.bookserviceabstract.domain.Book
import coil.compose.AsyncImage

@Composable
fun BookVolumeItem(book: Book, controller: NavController) {
    Column {
        AsyncImage(
            model = book.thumbnail,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().clickable {
                controller.navigate("bookDetails/${book.id}")
            }
        )
    }
}
