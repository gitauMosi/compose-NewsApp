package com.example.newsapp.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.newsList
import com.example.newsapp.models.News



@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "News App")
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notification")
                        }
                    }
                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp) // Adds spacing between items
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    headerWidget(news = newsList[0])
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    val categories = listOf("Technology", "Sports", "Business", "Health", "Entertainment")
                    NewsCategoryChips(categories = categories) { selectedCategory ->
                        println("Selected category: $selectedCategory")
                    }
                }
                items(newsList.size) { index ->
                    NewsTileCard(news = newsList[index])
                }
            }
        }
    )
}

@Composable
fun CustomCorousel(){

}
@Composable
fun  NewsTileCard(news: News){
    Box(
        Modifier
            .padding(start = 2.dp, end = 2.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
    ) {
       Row {
           Box(
               Modifier
                   .padding(8.dp)
                   .width(100.dp)
                   .height(100.dp)
                   .background(Color.DarkGray)
                   .clip(RoundedCornerShape(16.dp))
           ){
               AsyncImage(
                   model = news.urlToImage,
                   modifier =  Modifier.fillMaxSize(),
                   contentScale = ContentScale.Crop,
                   contentDescription = null,
               )
//               Image(painter = painterResource(id = R.drawable.news), contentDescription = "image news",
//                   modifier = Modifier.fillMaxSize(),
//                   contentScale = ContentScale.Crop
//               )

           }
          Column(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.SpaceEvenly
          ) {
              Text(
                  text = news.title,
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(8.dp),
                  style = MaterialTheme.typography.bodyLarge
              )
              Text(
                  text = "Author: ${news.author}",
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(horizontal = 8.dp),
                  style = MaterialTheme.typography.bodySmall
              )
              Text(
                  text = "Published: ${news.publishedAt}",
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(horizontal = 8.dp),
                  style = MaterialTheme.typography.bodySmall
              )
          }
       }
    }
}


@Composable
fun NewsCategoryChips(categories: List<String>, onChipClick: (String) -> Unit){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
       items(categories) {category ->
           Chip(
               text = category,
               onClick = {onChipClick(category)}
           )
       }

    }
}

@Composable
fun Chip(text:String, onClick: () -> Unit){
    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),

            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun headerWidget(news: News) {
    Box(
        Modifier
            .padding(top = 110.dp, start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
            .height(250.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = news.urlToImage,
            modifier =  Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}
