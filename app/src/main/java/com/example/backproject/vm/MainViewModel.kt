package com.example.backproject.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {

    private val nfcInit = "핸드폰을 NFC 태그에 가까이 해주세요!"
    private val nfcSuc = "NFC 등록에 성공했습니다!"
    private val url = "https://server.notibook.kro.kr/books/"

    private val _text = MutableStateFlow("")
    val text get() = _text

    private val _nfc = MutableStateFlow(nfcInit)
    val nfc get() = _nfc

    private val _isbn = MutableStateFlow("바코드를 스캔 해주세요!")
    val isbn get() = _isbn

    fun nfcInitialize() {
        nfc.value = nfcInit
    }

    fun nfcSuccess() {
        nfc.value = nfcSuc
    }

    fun saveNfcData(data : String) {
        text.value = url + data
        setIsbn(data)
    }

    fun getNfcData() : String {
        return text.value
    }

    private fun setIsbn(data : String){
        isbn.value = data
    }


}