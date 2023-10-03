package br.com.rcp.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookstore.presentation.main.BookStoreScreen
import br.com.rcp.bookstore.presentation.main.BookStoreViewModel
import br.com.rcp.bookstore.presentation.view.BookDetails
import br.com.rcp.bookstore.ui.theme.BookStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewmodel = hiltViewModel<BookStoreViewModel>()
                    val books = viewmodel.bookPagingFlow.collectAsLazyPagingItems()
                    MainNavigationHost(books)
                }
            }
        }
    }


    @Composable
    fun MainNavigationHost(
        books: LazyPagingItems<Book>,
        modifier: Modifier = Modifier,
        controller: NavHostController = rememberNavController(),
        start: String = "bookStoreScreen"
    ) {
        NavHost(modifier = modifier, navController = controller, startDestination = start) {
            composable("bookStoreScreen") {
                BookStoreScreen(books, controller)
            }

            composable("bookDetails/{id}", listOf(navArgument("id") { type = NavType.StringType })) {
                BookDetails(id = it.arguments?.getString("id"))
            }
        }
    }
}
