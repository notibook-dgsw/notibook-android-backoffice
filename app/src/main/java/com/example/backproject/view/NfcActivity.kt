package com.example.backproject.view

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NfcF
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.backproject.R
import com.example.backproject.databinding.ActivityNfcBinding
import com.example.backproject.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NfcActivity : AppCompatActivity() {

    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pending : PendingIntent
    @Inject lateinit var vm : MainViewModel
    private val binding : ActivityNfcBinding by lazy { ActivityNfcBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.vm = vm
        binding.lifecycleOwner = this


        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val launchIntent = Intent(this, this.javaClass)
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pending = PendingIntent.getActivity(this, 0, launchIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    override fun onStart() {
        super.onStart()
        nfcAdapter.enableReaderMode(this, NfcAdapter.ReaderCallback { tag: Tag? ->
            val message = NdefMessage(NdefRecord.createUri(vm.getNfcData()))
            val size = message.toByteArray().size

            try {
                val ndef = Ndef.get(tag)
                if (ndef != null) {
                    ndef.connect()
                    if (!ndef.isWritable) {
                        Log.e("enter", "cannot write")
                    }
                    if (ndef.maxSize < size) {
                        Log.e("enter", "cannot size exception")
                    }
                    ndef.writeNdefMessage(message)
                    vm.saveUrlToServer()
                    vm.nfcSuccess()
                }
            } catch (e: Exception) {
                Log.i("writeError", e.message.toString());
            }},
            NfcAdapter.FLAG_READER_NFC_A
                    or NfcAdapter.FLAG_READER_NFC_B
                    or NfcAdapter.FLAG_READER_NFC_F
                    or NfcAdapter.FLAG_READER_NFC_V
                    or NfcAdapter.FLAG_READER_NFC_BARCODE,
            null)


    }

    override fun onStop() {
        super.onStop()
        nfcAdapter.disableReaderMode(this);
        vm.nfcInitialize()
    }
}