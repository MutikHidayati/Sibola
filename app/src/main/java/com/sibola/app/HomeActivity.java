package com.sibola.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import java.text.SimpleDateFormat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    private java.text.SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private User mUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadSignInView();
        } else {

            mUserId = mFirebaseUser.getUid();
            mDatabase.child("users").child(mUserId).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    mUser = dataSnapshot.getValue(User.class);
                    TextView haiUsernameText = (TextView) findViewById(R.id.hai_username);
                    TextView depositText = (TextView) findViewById(R.id.depositText);
                    haiUsernameText.setText("Hai " + mUser.getUsername() + "! Deposit kamu sebanyak:");
                    depositText.setText("Rp " + mUser.getDeposit());

                    // ...
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    TextView haiUsernameText = (TextView) findViewById(R.id.hai_username);
                    haiUsernameText.setText("The read failed: " + databaseError.getCode());
                    // ...
                }
            });

            final ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
            //actionBar.setTitle(null);
            compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
            compactCalendar.setUseThreeLetterAbbreviation(true);

            //add event
            Event ev1 = new Event(Color.RED, 1492732800000L, "Hari Kartini");
            compactCalendar.addEvent(ev1);

            compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
                    Context context = getApplicationContext();


                    if (dateClicked.toString().compareTo("Fri 21 Apr 07:00:00 GMT 2017") == 0) {
                        Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
                        startActivity(intent);

                        Toast.makeText(context, "Hari Kartini", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
                        startActivity(intent);


                        Toast.makeText(context, "No Event", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {

                    actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
                }
            });

        }

    }

    private void loadSignInView() {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {
            mFirebaseAuth.signOut();
            loadSignInView();
        }

        return super.onOptionsItemSelected(item);
    }
}