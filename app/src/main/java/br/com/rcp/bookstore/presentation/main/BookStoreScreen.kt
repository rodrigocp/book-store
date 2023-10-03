package br.com.rcp.bookstore.presentation.main

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookstore.R
import br.com.rcp.bookstore.extensions.items
import br.com.rcp.bookstore.presentation.view.BookVolumeItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookStoreScreen(
    books: LazyPagingItems<Book>,
    controller: NavController
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = books.loadState) {
        if(books.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                context.getString(R.string.error).plus((books.loadState.refresh as LoadState.Error).error.message),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    if (books.loadState.refresh is LoadState.Loading) {
        LoadingBooks()
    } else {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(3)) {
            items(books) { book ->
                book?.let {
                    BookVolumeItem(it, controller)
                }
            }

            item {
                if(books.loadState.append is LoadState.Loading) {
                    AppendingBooks()
                }
            }
        }
    }
}

@Composable
fun LoadingBooks() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun AppendingBooks() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}
