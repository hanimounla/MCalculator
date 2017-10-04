package com.mounla.hani.mcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.rey.material.widget.Slider;

import java.util.ArrayList;
import java.util.List;

public class Plotting extends AppCompatActivity {

    LineChart lineChart;
    Slider slider;
    String inputEquation;
    MathHelper mathDude;
    TextView sliderValueLBL;
    int sliderValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plotting);
        mathDude = new MathHelper();

        inputEquation = getIntent().getStringExtra("equation");

        lineChart = (LineChart)findViewById(R.id.lineChart);

        slider = (Slider)findViewById(R.id.slider);
        sliderValueLBL = (TextView)findViewById(R.id.sliderValue);

        slider.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                sliderValueLBL.setText(newValue + "");
                sliderValue = newValue;
                draw();
                }
        });
    }
    private void draw() {
        List<Entry> LineEntries = new ArrayList<>();
        for (int i = 0; i < sliderValue; i++) {
            String temp = inputEquation.replace("X", i + "");
            try {
                double result = mathDude.evaluate(temp);
                float y = Float.parseFloat(result +"");
                LineEntries.add(new Entry(i, y));
            } catch (Exception exp) {
                Toast.makeText(Plotting.this, exp.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        try {
            LineDataSet lineDataSet = new LineDataSet(LineEntries, inputEquation);
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
            lineChart.invalidate();
            lineChart.animateX(500);
            lineChart.animateY(500);

        }
        catch (Exception c)
        {

        }
    }
}
