package com.example.Scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Scaffold.ui.theme.GiraffTheme
import com.example.giraff.R
import kotlinx.coroutines.launch

var variable_global :Int = -1

//MssCoderCalix
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiraffTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldExample()
                    //  SmallTopAppBarExample()
                    //  ListTopAppBar(onFilterClick = {it->it+it }, onSearchClick = { variable_global = 200})
                }
            }
        }
    }
}
//MssCoderCalix
//MAIN
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    //var presses by remember { mutableIntStateOf(0) }
    var content by remember {mutableStateOf("")}

    Scaffold(
        topBar = {
            ListTopAppBar(onFilterClick = {it -> it+it }, onSearchClick = { variable_global = 200})
            /*TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("MscCoderCalix ${content}")
                }
            )*/
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar ${content}",
                    //onTextLayout = { content = it.size.toString()}
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { content = "floatingActionButton_Click"}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """                                                            
                    It also contains some basic inner content, such as this text.
                    You have pressed the floating action button times.
                    $content
                """.trimIndent()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarExample() {

    var entero: Int = 0

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor= Color.Blue,
                    scrolledContainerColor=Color.Red,
                    navigationIconContentColor=Color.Gray,
                    titleContentColor=Color.Red,
                    actionIconContentColor=Color.Magenta
                ),
                title = {
                    Text("Small Top App Bar")
                },
                actions = {

                    IconButton(onClick =  {
                        scope.launch {
                            snackbarHostState.showSnackbar("Buscar $entero")
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar $entero")
                    }

                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Filtrar ${entero}")
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_airport_shuttle_24),
                            contentDescription = "Filtrar ${entero}"
                        )
                    }

                    IconButton(onClick = {
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                    message = "Snackbar Mesage ${entero}",
                                    actionLabel = "Action",
                                    // Defaults to SnackbarDuration.Short
                                    duration = SnackbarDuration.Indefinite
                                )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    /* Handle snackbar action performed */
                                    entero = 100;
                                }

                                SnackbarResult.Dismissed -> {
                                    /* Handle snackbar dismissed */
                                    entero = 200;
                                }
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Ver más")
                    }
                }
            )
        }
    )  {padding->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)) {
            Text(
                text = "Contenido ",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListTopAppBar(
    onSearchClick: () -> Unit = {},
    onFilterClick: (Int) -> Int = {-1},
) {
    var content by remember {mutableStateOf(1)}

    TopAppBar(
        title = { Text(text = "MscCoderCalix Items ${content + variable_global}") },
        navigationIcon = {
            IconButton(onClick = { content = 0 }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú")
            }
        },
        actions = {

            IconButton(onClick = { content += 20 }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
            }

            IconButton(onClick = { content += onFilterClick(10) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_airport_shuttle_24),
                    contentDescription = "Filtrar $variable_global"
                )
            }

            IconButton(onClick = { onSearchClick() }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Ver más")
            }
        }
    )
}

//PREVIEW

@Preview(showBackground = true)
@Composable
fun ScaffoldExample_Preview() {
    GiraffTheme {
        ScaffoldExample()
    }
}

@Preview(showBackground = true)
@Composable
fun SmallTopAppBarExample_Preview() {
    GiraffTheme {
        SmallTopAppBarExample()
    }
}

@Composable
@Preview(showBackground = true)
internal fun ListTopAppBar_Preview(
) {
    ListTopAppBar(onFilterClick = {it->it+it }, onSearchClick = { variable_global = 200})
}

