package com.example.apialistirma.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apialistirma.model.Post
import com.example.apialistirma.repo.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel: ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    fun fetchPosts(){
        viewModelScope.launch {
            _posts.value = repository.getPosts()
        }
    }
    fun createPost(post: Post, onResult: (Response<Post>) -> Unit){
        viewModelScope.launch {
            val response = repository.createPost(post)
            onResult(response)
        }
    }
    fun updatePost(id: Int, post: Post, onResult: (Response<Post>) -> Unit){
        viewModelScope.launch {
            val response = repository.updatePost(id, post)
            onResult(response)
        }
    }
    fun patchPost(id: Int, post: Post, onResult: (Response<Post>) -> Unit) {
        viewModelScope.launch {
            val response = repository.patchPost(id, post)
            onResult(response)
        }
    }
    fun deletePost(id: Int, onResult: (Response<Unit>) -> Unit) {
        viewModelScope.launch {
            val response = repository.deletePost(id)
            onResult(response)
        }
    }
}