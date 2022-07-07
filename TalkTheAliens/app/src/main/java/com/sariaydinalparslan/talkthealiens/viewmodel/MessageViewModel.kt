package com.sariaydinalparslan.talkthealiens.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sariaydinalparslan.talkthealiens.Entity.Messages
import com.sariaydinalparslan.talkthealiens.repository.MessageRepository
import com.sariaydinalparslan.talkthealiens.state.TextToSpeechState
import java.util.*

class MessageViewModel: ViewModel()  {

    var messagelist = MutableLiveData<List<Messages>>()
    var messagerepo = MessageRepository()

    private val _state = mutableStateOf(TextToSpeechState())
    val state: State<TextToSpeechState> = _state
    private  var  textToSpeech: TextToSpeech? = null

//message
    init {
        uploadmessages()
        messagelist = messagerepo.mesajlarıGetir()
    }

    fun uploadmessages(){

        messagerepo.tummesajlarıAl()
    }
// real message
fun realMessage(text: String){
    _state.value= state.value.copy(
        text = text
    )
}
    fun textToSpeech(context: Context){
        _state.value= state.value.copy(
            isButtonEnabled = false
        )
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _state.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _state.value = state.value.copy(
                isButtonEnabled = true
            )
        }


    }



}