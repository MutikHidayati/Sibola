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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stacktips.view.CalendarListener;
import com.stacktips.view.CustomCalendarView;
import com.stacktips.view.DayDecorator;
import com.stacktips.view.DayView;
import com.stacktips.view.utils.CalendarUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private User mUser;
    private CustomCalendarView calendarView;

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

            //Initialize CustomCalendarView from layout

            calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

            //Initialize calendar with date
            Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

            //Show Monday as first date of week
            calendarView.setFirstDayOfWeek(Calendar.MONDAY);

            //Show/hide overflow days of a month
            calendarView.setShowOverflowDate(false);

            //call refreshCalendar to update calendar the view
            calendarView.refreshCalendar(currentCalendar);

            calendarView.setCalendarListener(new CalendarListener() {
                @Override
                public void onDateSelected(Date date) {
                    if (!CalendarUtils.isPastDay(date)) {
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyy");
                        Toast.makeText(HomeActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                        //selectedDateTv.setText(df.format(date));

                        Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(HomeActivity.this, "Pilih Hari Lain", Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onMonthChanged(Date date) {
                    SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                    Toast.makeText(HomeActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                }
            });

            //adding calendar day decorators
            List<DayDecorator> decorators = new ArrayList<>();
            decorators.add(new DisabledColorDecorator());
            calendarView.setDecorators(decorators);
            calendarView.refreshCalendar(currentCalendar);
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

    private class DisabledColorDecorator implements DayDecorator {
        public void decorate(DayView dayView) {
            if (CalendarUtils.isPastDay(dayView.getDate())) {
                int color = Color.parseColor("#a9afb9");
                dayView.setTextColor(color);
            }
        }
    }
}