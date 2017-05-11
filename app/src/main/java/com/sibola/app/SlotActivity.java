package com.sibola.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SlotActivity extends AppCompatActivity {
    private ListView lvItem;
    private String[] listArray={"09:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00", "18:00-19:00", "19:00-20:00", "20:00-21:00", "21:00-22:00", "22:00-23:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot);

        lvItem = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SlotActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, listArray);

        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(SlotActivity.this, "Memilih : " + listArray[position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SlotActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });


    }
}
