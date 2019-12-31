package ru.bmstu.asynctask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.math.sqrt

class ViewModel : ViewModel() {
    var rawInput: String = ""

    val lblStatus: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun launchCalculation() {
        GlobalScope.launch {
            calculationTask()
        }
    }

    private suspend fun calculationTask() = withContext(Dispatchers.Default) {
        lblStatus.postValue("Calculating...")
        delay(1000) // Simulate intense calculations
        var number: Float = 0.0f
        try {
            number = rawInput.toFloat()
        } catch (e: Exception) {
            lblStatus.postValue("Invalid input")
        }
        lblStatus.postValue("Result = " + sqrt(number).toString())
    }
}
