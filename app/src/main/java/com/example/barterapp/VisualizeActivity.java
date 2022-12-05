package com.example.barterapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class VisualizeActivity extends AppCompatActivity{

    // make an object of TextView and PieChart class
    TextView tvTech;
    TextView tvClothing;
    TextView tvFurnish;
    TextView tvSport;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization);

        // link objects by id from xml file
        tvTech = findViewById(R.id.tvTech);
        tvClothing = findViewById(R.id.tvClothing);
        tvFurnish = findViewById(R.id.tvFurnish);
        tvSport = findViewById(R.id.tvSport);
        pieChart = findViewById(R.id.piechart);

        // Creating a method setData() to set text in text view and pie chart
        setData();
    }

    private void setData()
    {

        // Set the percentage of trades in each category
        tvTech.setText(Integer.toString(70));
        tvClothing.setText(Integer.toString(10));
        tvFurnish.setText(Integer.toString(5));
        tvSport.setText(Integer.toString(15));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Technology",
                        Integer.parseInt(tvTech.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Clothing",
                        Integer.parseInt(tvClothing.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Furnishings",
                        Integer.parseInt(tvFurnish.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Sporting Goods",
                        Integer.parseInt(tvSport.getText().toString()),
                        Color.parseColor("#29B6F6")));

    }

}
