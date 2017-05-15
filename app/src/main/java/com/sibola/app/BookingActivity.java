package com.sibola.app;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Aizen on 10 Mei 2017.
 */

public class BookingActivity extends AppCompatActivity {
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;
    private String thisDate = null;
    //private Button bookingAlert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        dataSet = new ArrayList<>();
        initDataset();

        //add to activity you want to pull variables from
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            thisDate = extras.getString("thisDate");
        }

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

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(thisDate);
        /*
        bookingAlert = (Button) findViewById(R.id.bt_booking);
        bookingAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        }); */

    }
    /*
    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //set title dialog
        alertDialogBuilder.setTitle("Konfirmasi Booking");

        //set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik YA untuk booking!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        BookingActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });

        //membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        //menampilkan alert dialog
        alertDialog.show();
    } */

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
