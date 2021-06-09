package com.test.mypincaculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.test.mypincaculator.R
import com.test.mypincaculator.extension.toHex
import com.test.mypincaculator.model.Formatter
import com.test.mypincaculator.model.ISO3Formatter

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val output: MutableLiveData<String> = MutableLiveData()
    private val minLength by lazy { application.resources.getInteger(R.integer.pin_min_length) }
    private val maxLength by lazy { application.resources.getInteger(R.integer.pin_max_length) }
    private val formatter = ISO3Formatter()

    fun enterPin(pin: String)  {
        if (pin.length < minLength) {
            with(output) { postValue(this@MainViewModel.getApplication<Application>().resources.getString(R.string.pin_too_short)) }
            return
        }
        if (pin.length > maxLength) {
            with(output) { postValue(this@MainViewModel.getApplication<Application>().resources.getString(R.string.pin_too_long)) }
            return
        }
        val pinBlock = formatter.format(pin, Formatter.DEFAULT_PAN)
        output.postValue(pinBlock.toHex())
    }
}