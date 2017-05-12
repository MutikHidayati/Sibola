package com.sibola.app;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Aizen on 10 Mei 2017.
 */

public class BookingActivity extends AppCompatActivity {
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        dataSet = new ArrayList<>();
        initDataset();

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);

        /**
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(dataSet);
        rvView.setAdapter(adapter);

    }

    private void initDataset(){

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("09.00 - 10.00");
        dataSet.add("10.00 - 11.00");
        dataSet.add("11.00 - 12.00");
        dataSet.add("12.00 - 13.00");
        dataSet.add("13.00 - 14.00");
        dataSet.add("14.00 - 15.00");
        dataSet.add("15.00 - 16.00");
        dataSet.add("16.00 - 17.00");
        dataSet.add("17.00 - 18.00");
        dataSet.add("18.00 - 19.00");
        dataSet.add("19.00 - 20.00");
        dataSet.add("20.00 - 21.00");
        dataSet.add("21.00 - 22.00");
        dataSet.add("22.00 - 23.00");

    }
}
