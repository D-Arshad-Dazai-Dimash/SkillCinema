import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.project_modile_application.data.PosterData


@Composable
fun MovieTab(movie: PosterData) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = movie.image.takeIf { it.isNotEmpty() } ?: "default_image_url",
            contentDescription = movie.title,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )
        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = if (movie.countries.isNotEmpty()) movie.countries.first() else "null",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
