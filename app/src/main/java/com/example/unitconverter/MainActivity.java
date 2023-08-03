package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner inputUnitSpinner;
    private Spinner outputUnitSpinner;
    private TextView resultText;

    private String[] units = {
            "Centimeters", "Meters", "Kilometers", "Millimeters",
            "Inches", "Feet", "Yards", "Miles"
    };

    private double[][] conversionMatrix = {
            // cm,     m,        km,      mm,        inches,    feet,    yards,     miles
            {1,        0.01,     0.00001, 10,        0.393701,  0.0328084,  0.0109361, 0.00000621371},
            {100,      1,        0.001,   1000,      39.3701,   3.28084,    1.09361,   0.000621371},
            {100000,   1000,     1,       1000000,   39370.1,   3280.84,    1093.61,   0.621371},
            {0.1,      0.001,    0.000001, 1,         0.0393701,  0.00328084, 0.00109361, 0.000000621371},
            {2.54,     0.0254,   0.0000254, 25.4,      1,         0.0833333,  0.0277778, 0.0000157828},
            {30.48,    0.3048,   0.0003048, 304.8,     12,        1,          0.333333,  0.000189394},
            {91.44,    0.9144,   0.0009144, 914.4,     36,        3,          1,         0.000568182},
            {160934,   1609.34,  1.60934,  1609340,   63360,     5280,       1760,      1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        inputUnitSpinner = findViewById(R.id.inputUnitSpinner);
        outputUnitSpinner = findViewById(R.id.outputUnitSpinner);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputUnitSpinner.setAdapter(adapter);
        outputUnitSpinner.setAdapter(adapter);

        inputUnitSpinner.setSelection(0);
        outputUnitSpinner.setSelection(1);
    }

    public void onConvertButtonClick(View view) {
        int inputIndex = inputUnitSpinner.getSelectedItemPosition();
        int outputIndex = outputUnitSpinner.getSelectedItemPosition();

        double inputValue = Double.parseDouble(this.inputValue.getText().toString());
        double result = inputValue * conversionMatrix[inputIndex][outputIndex];

        resultText.setText(String.format("%.2f %s = %.2f %s", inputValue, units[inputIndex], result, units[outputIndex]));
    }
}
