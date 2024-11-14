import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab04.ui.theme.Lab04Theme
import com.google.accompanist.flowlayout.FlowColumn

@Composable
fun FlowColumnExample() {
    FlowColumn(
        modifier = Modifier.padding(16.dp),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp
    ) {
        for (i in 1..10) {
            Text(text = "Item $i", modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlowColumnExamplePreview() {
    Lab04Theme {
        FlowColumnExample()
    }
}