package com.sibola.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aizen on 10 Mei 2017.
 */

public class BookingActivity extends AppCompatActivity {

    private List<Booking> bookingList = new ArrayList<>();
    private RecyclerView rView;
    private SlotJamAdapter mAdapter;
    //private RecyclerView.LayoutManager layoutManager;
    private String thisDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        rView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new SlotJamAdapter(bookingList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        // add line decoration
        rView.addItemDecoration(new DividerItemDecoration(this));

        // set adapter
        rView.setAdapter(mAdapter);

        rView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Booking booking = bookingList.get(position);
                Toast.makeText(getApplicationContext(), booking.getTanggal() + " pukul "
                        + booking.getSlotJam(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //add to activity you want to pull variables from
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.thisDate = extras.getString("thisDate");
        }

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(thisDate);

        initBookingList();

        /*bookingButton = (Button) findViewById(R.id.bt_booking);
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });*/

    }

    /*private void showDialog() {
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
    }*/

    private void initBookingList(){

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        Booking booking = new Booking(thisDate, "9:00 - 10:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "10:00 - 11:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "11:00 - 12:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "12:00 - 13:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "13:00 - 14:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "14:00 - 15:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "15:00 - 16:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "16:00 - 17:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "17:00 - 18:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "18:00 - 19:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "19:00 - 20:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "20:00 - 21:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "21:00 - 22:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "22:00 - 23:00");
        bookingList.add(booking);

        booking = new Booking(thisDate, "23:00 - 24:00");
        bookingList.add(booking);

        mAdapter.notifyDataSetChanged();
    }
}
