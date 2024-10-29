package com.example.apialistirma.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apialistirma.model.Comment
import com.example.apialistirma.repo.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommentViewModel: ViewModel() {
    private val repository = PostRepository()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments

    fun fetchComments(){
        viewModelScope.launch {
            _comments.value = repository.getComments()
        }
    }
}