
package com.example.kalkulatorlpg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDistance, editTextGasPrice, editTextCourseAmount;
    private TextView textViewCostResult, textViewProfitResult;
    private Button buttonCalculate;

    // LPG consumption: 8 liters per 100 km
    private static final double LPG_CONSUMPTION_PER_100KM = 8.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDistance = findViewById(R.id.editTextDistance);
        editTextGasPrice = findViewById(R.id.editTextGasPrice);
        editTextCourseAmount = findViewById(R.id.editTextCourseAmount);
        textViewCostResult = findViewById(R.id.textViewCostResult);
        textViewProfitResult = findViewById(R.id.textViewProfitResult);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTrip();
            }
        });
    }

    private void calculateTrip() {
        String distanceStr = editTextDistance.getText().toString();
        String gasPriceStr = editTextGasPrice.getText().toString();
        String courseAmountStr = editTextCourseAmount.getText().toString();

        if (distanceStr.isEmpty() || gasPriceStr.isEmpty() || courseAmountStr.isEmpty()) {
            textViewCostResult.setText("Wprowadź wszystkie dane.");
            textViewProfitResult.setText("");
            return;
        }

        try {
            double distance = Double.parseDouble(distanceStr);
            double gasPrice = Double.parseDouble(gasPriceStr);
            double courseAmount = Double.parseDouble(courseAmountStr);

            // Calculate LPG cost
            double lpgUsed = (distance / 100.0) * LPG_CONSUMPTION_PER_100KM;
            double tripCost = lpgUsed * gasPrice;

            // Calculate net profit
            double netProfit = courseAmount - tripCost;

            DecimalFormat df = new DecimalFormat("#.##");

            textViewCostResult.setText("Koszt przejazdu: " + df.format(tripCost) + " PLN");
            textViewProfitResult.setText("Zysk netto: " + df.format(netProfit) + " PLN");

        } catch (NumberFormatException e) {
            textViewCostResult.setText("Wprowadź poprawne liczby.");
            textViewProfitResult.setText("");
        }
    }
}
