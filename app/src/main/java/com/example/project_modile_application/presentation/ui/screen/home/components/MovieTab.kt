import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.viewModels.SharedViewModel




@Composable
fun MovieTab(movie: Movie,
             navController: NavController,
             sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = movie.image.takeIf { it.isNotEmpty().or(it == "") } ?: movie.posterUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .height(180.dp)
                .width(111.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Text(
            text = movie.title.takeIf { it.isNotEmpty().or(it == "") } ?: movie.nameRu,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(111.dp)
                .clickable {
                    sharedViewModel.selectMovie(movie)
                    navController.navigate("movieData/${movie.kinopoiskId}")
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontFamily = com.example.project_modile_application.presentation.ui.font.GraphicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color(0xFF272727)
        )
        Text(
            text = if (movie.countries.isNotEmpty()) movie.countries.first().country else "Unknown",
            fontFamily = com.example.project_modile_application.presentation.ui.font.GraphicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF838390)
        )

    }
}
