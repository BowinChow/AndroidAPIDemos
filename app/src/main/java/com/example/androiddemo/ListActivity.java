package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddemo.ListView.Fruit;
import com.example.androiddemo.ListView.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private final List<Fruit> fruitLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(ListActivity.this, R.layout.fruit_item, fruitLists);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(ListActivity.this, "fruit item clicked!", Toast.LENGTH_LONG).show();
        });
    }

    public void initFruits() {
        for (int i = 0; i < 15; i++) {
            Fruit fruit = new Fruit("apple", R.mipmap.fruit);
            fruitLists.add(fruit);
        }
    }
}