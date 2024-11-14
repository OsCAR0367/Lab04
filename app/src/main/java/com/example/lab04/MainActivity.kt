package com.example.lab04

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab04.ui.theme.Lab04Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab04Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Mi Top App Bar") },
                                actions = {
                                    IconButton(onClick = { /* Do something */ }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_sample_image),
                                            contentDescription = "Sample Icon"
                                        )
                                    }
                                }
                            )
                        }
                    ) {
                        CardExample()
                    }
                }
            }
        }
    }
}

@Composable
fun CardExample() {
    var showDialog by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedOption by remember { mutableStateOf("Option 1") }
    var sliderPosition by remember { mutableStateOf(0f) }
    var switchState by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Alert Dialog Title") },
            text = { Text("This is an example of an AlertDialog in Jetpack Compose.") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Card Title", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "This is an example of a card in Jetpack Compose.")
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_sample_image),
                    contentDescription = "Sample Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Check me")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == "Option 1",
                        onClick = { selectedOption = "Option 1" }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Option 1")
                }
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == "Option 2",
                        onClick = { selectedOption = "Option 2" }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Option 2")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Slider Value: ${sliderPosition.toInt()}")
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    valueRange = 0f..100f,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(text = "Switch")
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = switchState,
                        onCheckedChange = { switchState = it }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showDialog = true }) {
                    Text("Show AlertDialog")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (isLoading) {
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_fab_icon),
                contentDescription = "FAB Icon"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab04Theme {
        CardExample()
    }
}