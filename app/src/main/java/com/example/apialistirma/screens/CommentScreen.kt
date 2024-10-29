package com.example.apialistirma.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apialistirma.model.Comment
import com.example.apialistirma.viewModel.CommentViewModel

@Composable
fun CommentScreen(commentViewModel: CommentViewModel = viewModel()){
    val comments by commentViewModel.comments.collectAsState()

    LaunchedEffect(Unit) {
        commentViewModel.fetchComments()
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(comments) {comment ->
            CommentItem(comment)
        }
    }
}

@Composable
fun CommentItem(comment: Comment){
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "From: ${comment.name}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Email: ${comment.email}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = comment.body, style = MaterialTheme.typography.bodySmall)
    }
}