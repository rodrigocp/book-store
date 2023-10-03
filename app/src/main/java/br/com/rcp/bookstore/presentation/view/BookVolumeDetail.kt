package br.com.rcp.bookstore.presentation.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookstore.presentation.main.BookStoreViewModel
import br.com.rcp.bookstore.ui.theme.BookStoreTheme
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers

@Composable
fun BookImage(thumbnail: String?, modifier: Modifier) {
    AsyncImage(
        model = thumbnail,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .graphicsLayer { alpha = 0.99f }
            .drawWithContent {
                val colors = listOf(Color.Black, Color.Transparent)
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(colors),
                    blendMode = BlendMode.DstIn
                )
            }
    )
}

@Composable
fun BookDetailsData(book: Book) {
    Box(Modifier.fillMaxSize()) {
        BookImage(book.thumbnail, modifier = Modifier.matchParentSize())
        Column(Modifier.fillMaxHeight()) {
            Box(Modifier.weight(1f)) {

            }
            Box(Modifier.weight(1f)) {
                Column(Modifier.padding(16.dp).fillMaxWidth()) {
                    Text(
                        text = book.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = book.subtitle ?: "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(16.dp).fillMaxWidth())

                    Text(
                        text = book.description ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (book.informationLink != null) {
                    val context = LocalContext.current
                    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(book.informationLink)) }

                    Button(
                        modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(16.dp),
                        onClick = { context.startActivity(intent) }
                    ) {
                        Text("See more")
                    }
                }
            }
        }
    }
}

@Composable
fun BookDetails(id: String?) {
    if (id != null) {
        val viewmodel = hiltViewModel<BookStoreViewModel>()
        val book = viewmodel.getBookByRemoteId(id).collectAsState(null, Dispatchers.IO)

        if (book.value != null) {
            BookDetailsData(book.value!!)
        }
    }
}

@Preview
@Composable
fun BookVolumeDetailPreview() {
    BookStoreTheme {
        BookDetailsData(
            Book(
                id = "D8A5AE",
                title = "Hexadecimais",
                authors = listOf(),
                subtitle = "Hexadecimais Sistemas de Informação",
                thumbnail = "",
                description = "Lorem Ipsum Informação Teste ABCDEFG"
            )
        )
    }
}
