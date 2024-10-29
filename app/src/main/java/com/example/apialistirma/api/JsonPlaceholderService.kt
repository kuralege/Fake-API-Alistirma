package com.example.apialistirma.api

import com.example.apialistirma.model.Comment
import com.example.apialistirma.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path


interface JsonPlaceholderService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("comments")
    suspend fun getComments(): List<Comment>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Response<Post>

    @PATCH("posts/{id}")
    suspend fun patchPost(@Path("id") id: Int, @Body post: Post): Response<Post>

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>
}