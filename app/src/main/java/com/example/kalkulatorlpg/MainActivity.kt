package com.example.kalkulatorlpg

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val COMBUSTION = 8.0 // 8l LPG / 100km

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etDistance = findViewById<TextInputEditText>(R.id.etDistance)
        val etLpgPrice = findViewById<TextInputEditText>(R.id.etLpgPrice)
        val etFare = findViewById<TextInputEditText>(R.id.etFare)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        val tvFuelUsed = findViewById<TextView>(R.id.tvFuelUsed)
        val tvFuelCost = findViewById<TextView>(R.id.tvFuelCost)
        val tvNetProfit = findViewById<TextView>(R.id.tvNetProfit)

        btnCalculate.setOnClickListener {
            val distanceStr = etDistance.text.toString()
            val lpgPriceStr = etLpgPrice.text.toString()
            val fareStr = etFare.text.toString()

            val distance = distanceStr.toDoubleOrNull() ?: 0.0
            val lpgPrice = lpgPriceStr.toDoubleOrNull() ?: 0.0
            val fare = fareStr.toDoubleOrNull() ?: 0.0

            val fuelUsed = (distance * COMBUSTION) / 100.0
            val fuelCost = fuelUsed * lpgPrice
            val netProfit = fare - fuelCost

            tvFuelUsed.text = String.format(Locale.getDefault(), "Zużycie LPG: %.2f l", fuelUsed)
            tvFuelCost.text = String.format(Locale.getDefault(), "Koszt przejazdu: %.2f zł", fuelCost)
            tvNetProfit.text = String.format(Locale.getDefault(), "Zysk netto: %.2f zł", netProfit)
        }
    }
}
