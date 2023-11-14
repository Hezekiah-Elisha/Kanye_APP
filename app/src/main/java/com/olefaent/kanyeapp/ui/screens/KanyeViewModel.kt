package com.olefaent.kanyeapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.olefaent.kanyeapp.KanyeApplication
import com.olefaent.kanyeapp.data.KanyeRepository
import com.olefaent.kanyeapp.model.Kanye
import kotlinx.coroutines.launch
import retrofit2.HttpException

sealed interface KanyeState {
    object Loading : KanyeState
    data class Success(val kanye: Kanye) : KanyeState
    data class Error(val message: String) : KanyeState
}

class KanyeViewModel(private val kanyeRepository: KanyeRepository) : ViewModel() {
    var uiState: KanyeState by mutableStateOf(KanyeState.Loading)
        private set

    init{
        getQuote()
    }


    fun getQuote(){
        viewModelScope.launch{
//            uiState = try{
//                KanyeState.Success(kanyeRepository.getQuote())
//            } catch (e: Exception){
//                KanyeState.Error(e.message ?: "Unknown error")
//            } catch (e: HttpException){
//                KanyeState.Error(e.message ?: "Unknown error")
//            }
            try {
                uiState = KanyeState.Success(kanyeRepository.getQuote())
            } catch (e: Exception){
                uiState = KanyeState.Error(e.message ?: "Unknown error - Exception")
                Log.d("kanye_log", "getQuote: ${e.message} - Exception")
            } catch (e: HttpException){
                uiState = KanyeState.Error(e.message ?: "Unknown error")
                Log.d("kanye_log", "getQuote: ${e.message} - HttpsException")
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as KanyeApplication)
                /**
                 * The initializer block is called immediately after the ViewModel is created, and
                 * before it is returned to the caller. This is where you can setup any initial
                 * values or state that you want the ViewModel to have.
                 */
                 val repository = application.container.kanyeRepository
                KanyeViewModel(repository)

            }
        }
    }
}