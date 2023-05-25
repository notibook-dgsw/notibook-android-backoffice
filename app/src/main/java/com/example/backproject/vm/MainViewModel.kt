package com.example.backproject.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {

    val text = MutableStateFlow("")

}