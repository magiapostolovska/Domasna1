package com.example.twittersearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwitterSearchApp()
        }
    }
}

@Composable
fun TwitterSearchApp() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var tagQuery by remember { mutableStateOf(TextFieldValue("")) }

    var taggedSearches by remember {
        mutableStateOf(
            listOf(
                "AndroidFP",
                "Deitel",
                "Google",
                "iPhoneFP",
                "JavaFP",
                "JavaHTP"
            )
        )
    }

    val filteredTags = if (searchQuery.text.isEmpty()) {
        taggedSearches
    } else {
        taggedSearches.filter { it.contains(searchQuery.text, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA726))
            .padding(16.dp)
    ) {

        Text(
            text = "Favorite Twitter Searches",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Enter Twitter search query here") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = tagQuery,
                onValueChange = { tagQuery = it },
                label = { Text("Tag your query") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (tagQuery.text.isNotEmpty()) {
                        taggedSearches = taggedSearches + tagQuery.text
                        tagQuery = TextFieldValue("")
                    }
                }
            ) {
                Text("Save")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tagged Searches",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        filteredTags.forEach { tag ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(Color.White)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(tag, fontSize = 16.sp)
                Button(
                    onClick = {
                        println("Edit clicked for $tag")
                    },
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Edit")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                taggedSearches = emptyList()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Clear Tags")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwitterSearchApp() {
    TwitterSearchApp()
}
