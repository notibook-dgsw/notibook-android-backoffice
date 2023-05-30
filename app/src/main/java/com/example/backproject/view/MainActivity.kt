package com.example.backproject.view

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.backproject.databinding.ActivityMainBinding
import com.example.backproject.vm.MainViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @Inject lateinit var vm : MainViewModel

    private val result = registerForActivityResult(ScanContract()) { result : ScanIntentResult ->
        if (result.contents == null) Toast.makeText(this, "스캔에 실패했습니다.", Toast.LENGTH_SHORT).show()
        else {
            Toast.makeText(this, "스캔 성공", Toast.LENGTH_SHORT).show()
            vm.saveNfcData(result.contents)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.vm = vm
        binding.lifecycleOwner = this

        val option = ScanOptions()
        option.setOrientationLocked(false)
        option.setPrompt("책 뒷면에 있는 바코드를 빨간 줄에 맞게 찍어주세요!")
        option.setBeepEnabled(true)

        binding.scan.setOnClickListener { result.launch(option) }
        binding.uploadBtn.setOnClickListener { startActivity(Intent(this,NfcActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)) }
    }
}