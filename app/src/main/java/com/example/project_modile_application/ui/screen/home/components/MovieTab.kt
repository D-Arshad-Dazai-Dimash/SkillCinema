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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project_modile_application.data.PosterData
import com.example.project_modile_application.ui.font.GraphicFontFamily


@Composable
fun MovieTab(movie: PosterData) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        AsyncImage(
            model = movie.image.takeIf { it.isNotEmpty() } ?: "default_image_url",
            contentDescription = movie.title,
            modifier = Modifier
                .height(180.dp)
                .width(111.dp)
                .clip(RoundedCornerShape(4.dp))
//                .fillMaxWidth()
        )
        Text(
            text = movie.title,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(111.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontFamily = GraphicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color(0xFF272727)
        )
        Text(
            text = if (movie.countries.isNotEmpty()) movie.countries.first() else "null",
            fontFamily = GraphicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF838390)
        )
    }
}
