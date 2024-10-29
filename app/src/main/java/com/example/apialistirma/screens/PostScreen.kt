package com.example.apialistirma.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.apialistirma.model.Post
import com.example.apialistirma.viewModel.PostViewModel
import retrofit2.Response

@Composable
fun PostScreen(postViewModel: PostViewModel = viewModel(), navController: NavController) {
    val posts by postViewModel.posts.collectAsState()

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        postViewModel.fetchPosts()
    }

    Column(modifier = Modifier.fillMaxSize().padding(26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        TextField(
            value = title,
            onValueChange = {title = it},
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = body,
            onValueChange = {body = it},
            label = { Text("Body") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(onClick = {
            val newPost = Post(userId = 1, id = 0, title = title, body = body)
            postViewModel.createPost(newPost) {response: Response<Post> ->
                if (response.isSuccessful){
                    Toast.makeText(context, "Post gönderildi!", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context, "Hata: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Create Post")
        }

        // Buton: Comments ekranına yönlendirme
        Button(onClick = { navController.navigate("comments") }) {
            Text("View Comments")
        }

        Spacer(modifier = Modifier.height(2.dp))


        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(posts) { post ->
                PostItem(post, navController, postViewModel)
            }
        }
    }
}

@Composable
fun PostItem(post: Post, navController: NavController, postViewModel: PostViewModel) {

    val context = LocalContext.current

    Card(modifier = Modifier.padding(bottom = 16.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)

            Row {

                Button(onClick = {
                    navController.navigate("update/${post.id}")
                }) {
                    Text("Update Post")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(onClick = {
                    navController.navigate("patch/${post.id}")
                }) {
                    Text("Patch Post")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(onClick = {
                    postViewModel.deletePost(post.id) { response ->
                        if (response.isSuccessful) {
                            Toast.makeText(context, "Post silindi!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Hata: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) {
                    Text("Delete Post")
                }
            }
        }

    }
    /*Column(modifier = Modifier.padding(8.dp)) {
        Text(text = post.title, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
    }*/
}
