package com.aydinabiar.rubikscubeprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Creation and Attribution of the list of rotations
        ListView rotationsDoneListView = (ListView) findViewById(R.id.rotationsDoneListView);
        ArrayAdapter<String> rotationsDoneArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.historicOfRotations);
        rotationsDoneListView.setAdapter(rotationsDoneArrayAdapter);

        // Back to main page after reading the solutions
        rotationsDoneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==0) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }

            }
        });

    }
}
