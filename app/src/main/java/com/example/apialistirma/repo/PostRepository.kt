package com.example.apialistirma.repo

import com.example.apialistirma.api.RetrofitInstance
import com.example.apialistirma.model.Comment
import com.example.apialistirma.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostRepository {
    private val api = RetrofitInstance.api

    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO){
        api.getPosts()
    }

    suspend fun getComments(): List<Comment> = withContext(Dispatchers.IO){
        api.getComments()
    }

    suspend fun createPost(post: Post): Response<Post> = withContext(Dispatchers.IO){
        api.createPost(post)
    }

    suspend fun updatePost(id: Int, post: Post): Response<Post> = withContext(Dispatchers.IO){
        api.updatePost(id, post)
    }
    suspend fun patchPost(id: Int, post: Post): Response<Post> = withContext(Dispatchers.IO) {
        api.patchPost(id, post)
    }
    suspend fun deletePost(id: Int): Response<Unit> = withContext(Dispatchers.IO) {
        api.deletePost(id)
    }
}