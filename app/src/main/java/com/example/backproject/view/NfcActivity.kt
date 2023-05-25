package com.example.backproject.view

import android.nfc.NfcAdapter
import android.nfc.tech.MifareUltralight
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.backproject.R
import com.example.backproject.vm.MainViewModel
import java.nio.charset.Charset
import javax.inject.Inject

class NfcActivity : AppCompatActivity() {

    @Inject lateinit var vm : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

        var tagFromIntent: Parcelable? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
    }

    fun writeTag(tag: Tag, tagText: String) {
        MifareUltralight.get(tag)?.use { ultralight ->
            ultralight.connect()
            Charset.forName("US-ASCII").also { usAscii ->
                ultralight.writePage(4, vm.text.value.toByteArray(usAscii))
            }
        }
    }
}