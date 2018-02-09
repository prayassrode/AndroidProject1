package com.example.praya.bmicalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        final EditText age = findViewById(R.id.ageField);
        final EditText weight = findViewById(R.id.weightField);
        final EditText heightFeet = findViewById(R.id.heightFieldFeet);
        final EditText heightInches = findViewById(R.id.heightInchesField);
        final Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int a = Integer.parseInt(age.getText().toString());
                    double w = Double.parseDouble(weight.getText().toString());
                    int hfeet = Integer.parseInt(heightFeet.getText().toString());
                    int hinches = Integer.parseInt(heightInches.getText().toString());

                    if (a < 18){
                        Toast toast = Toast.makeText(getApplicationContext(), "Age should be at least 18 !!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    else {
                        int h = hfeet*12+hinches;
                        double bmi = 703*w/(h*h);
                        Log.d("bmiCalulator", bmi+"");

                        TextView result = findViewById(R.id.resultText);
                        TextView bmiText = findViewById(R.id.bmiText);
                        TextView bmiRating = findViewById(R.id.bmiRating);
                        TextView normalBmiRange = findViewById(R.id.normalBmiRangeText);
                        TextView bmiMessage = findViewById(R.id.bmiMessage);

                        result.setVisibility(View.VISIBLE);

                        bmiText.setText(String.format("BMI = %.2f", bmi));
                        bmiText.setVisibility(View.VISIBLE);

                        if (bmi < 18.5){
                            bmiRating.setText("(Underweight)");
                            bmiRating.setTextColor(Color.RED);
                            double extraWeight = (18.5*h*h/703) - w;
                            bmiMessage.setText(String.format("You will need to gain %.1f lbs to reach a BMI of 18.5", extraWeight));

                        }
                        else if (bmi <25){
                            bmiRating.setText("(Normal)");
                            bmiRating.setTextColor(Color.GREEN);
                            bmiMessage.setText("Keep up the good work !!");
                        }
                        else if (bmi < 30){
                            bmiRating.setText("(Overweight)");
                            bmiRating.setTextColor(Color.YELLOW);
                            double looseWeight = (-1.0)*(25.0*h*h/703)+w;
                            bmiMessage.setText(String.format("You will need to lose %.1f lbs to reach a BMI of 25", looseWeight));

                        }
                        else {
                            bmiRating.setText("(Obese)");
                            bmiRating.setTextColor(Color.YELLOW);
                            double looseWeight = (-1)*(25.0*h*h/703)+w;
                            bmiMessage.setText(String.format("You will need to lose %.1f lbs to reach a BMI of 25", looseWeight));

                        }

                        bmiRating.setVisibility(View.VISIBLE);
                        normalBmiRange.setVisibility(View.VISIBLE);
                        bmiMessage.setVisibility(View.VISIBLE);

                    }

                }catch (NumberFormatException e){
                    Toast toast = Toast.makeText(getApplicationContext(), "All the fields are required!!!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });


    }
}
