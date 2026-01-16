package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    String selectedCity = null;
    EditText cityInput;
    Button confirmAddButton;
    Button addCityButton;
    Button deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        confirmAddButton = findViewById(R.id.confirm_add_button);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);


        String []cities = {"Edmonton","Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
        });

        addCityButton.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
            confirmAddButton.setVisibility(View.VISIBLE);
        });

        confirmAddButton.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                cityInput.setVisibility(View.GONE);
                confirmAddButton.setVisibility(View.GONE);
            }
        });

        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = null;
            }
        });
    }
}