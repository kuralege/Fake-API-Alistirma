package com.example.apialistirma.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apialistirma.model.Post
import com.example.apialistirma.viewModel.PostViewModel

@Composable
fun UpdatePostScreen(postId: Int, postViewModel: PostViewModel = viewModel()){
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(22.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            val updatedPost = Post(userId = 1, id = postId, title = title, body = body)
            postViewModel.updatePost(postId, updatedPost) {response ->
                if (response.isSuccessful){
                    Toast.makeText(context, "Post Güncellendi!", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context, "Hata: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Update Post")
        }
    }
}