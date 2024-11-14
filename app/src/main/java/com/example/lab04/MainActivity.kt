package com.example.lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.lab04.ui.theme.Lab04Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab04Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar()
                        },
                        content = { innerPadding ->
                            Row(modifier = Modifier.padding(innerPadding)) {
                                NavigationRailBar()
                                Greeting("Android", modifier = Modifier.padding(16.dp))
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Search", "Profile")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Composable
fun NavigationRailBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Search", "Profile")
    val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Person)

    NavigationRail {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Option 1", "Option 2", "Option 3")
    var selectedItem by remember { mutableStateOf(items[0]) }
    var text by remember { mutableStateOf("") }
    val pagerState = rememberPagerState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3")

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 8.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Dialog Title")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "This is an example of a custom Dialog in Jetpack Compose.")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }

    Column(modifier = modifier) {
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.h4, // Modified style
            color = MaterialTheme.colorScheme.primary, // Modified color
            modifier = Modifier.padding(16.dp) // Modified padding
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Button(onClick = { showDialog = true }) {
            Text("Show Dialog")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedItem)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedItem = item
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showSnackbar = true }) {
            Text("Show Snackbar")
        }
        if (showSnackbar) {
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar("This is a Snackbar")
                showSnackbar = false
            }
        }
        SnackbarHost(hostState = snackbarHostState)
        Spacer(modifier = Modifier.height(16.dp))
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabTitles.forEachIndexed { index, title ->
                CustomTooltip(
                    tooltipText = "Tooltip for $title",
                    content = {
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title) }
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPager(
            count = 5,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Page #$page",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
@Composable
fun CustomTooltip(
    tooltipText: String,
    content: @Composable () -> Unit
) {
    var isTooltipVisible by remember { mutableStateOf(false) }

    Box {
        content()
        if (isTooltipVisible) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 4.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = tooltipText,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab04Theme {
        Greeting("Android")
    }
}