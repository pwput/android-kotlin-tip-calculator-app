package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Views binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//button listener
        binding.button.setOnClickListener { calculateTip() }


    }

    private fun calculateTip() {
//        getting input
        val input = binding.costInput.text.toString().toDoubleOrNull()
        if (input == null) {
            binding.tipResult.text = ""
            return
        }
//        getting percentage
        val tipPercetage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioAmazing -> 0.20
            R.id.radioGood -> 0.18
            else -> 0.15
        }
        //checking round up and calculating tip amount
        var tip = input.times(tipPercetage)
        if (binding.switchRound.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
//        formating output
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount) + formattedTip.toString()
    }

}