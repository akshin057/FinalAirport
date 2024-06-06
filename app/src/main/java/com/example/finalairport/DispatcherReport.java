package com.example.finalairport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalairport.usersData.DispatcherInfo;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DispatcherReport extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference myRef;
    TextView dateTV;
    Button returnBTN;
    GraphView graphView;
    ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher_report);

        init();

        returnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispatcherReport.this, DirectorMain.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void init() {
        main = findViewById(R.id.main);
        graphView = findViewById(R.id.graphGV);

        graphView.setTitle("График полетов по часам");

        graphView.setTitleColor(R.color.white);
        graphView.setTitleTextSize(18);

        dateTV = findViewById(R.id.dateTV);
        returnBTN = findViewById(R.id.returnBTN);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String newTV = dateTV.getText().toString() + " " + date;
        dateTV.setText(newTV);

        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("Dispatcher").child(date);

        if (!(myRef != null)) {
            Toast.makeText(getApplicationContext(), "Нет отчета за данную дату", Toast.LENGTH_LONG).show();
        }

        DispatcherInfo dispatcherInfo = new DispatcherInfo(new HashMap<String, Integer>());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<DataPoint> dataPoints = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        dispatcherInfo.addInfo(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
                    }

                    String[] strings = dispatcherInfo.getStringInfo();

                    for (int i = 0; i < 24; i++) {
                        dataPoints.add(new DataPoint(i, Integer.parseInt(strings[i])));
                    }

                    DataPoint[] dataPointArray = dataPoints.toArray(new DataPoint[0]);

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPointArray);

                    graphView.addSeries(series);

                } else {

                    Snackbar.make(main, "Ошибка чтения данных", Snackbar.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Snackbar.make(main, error.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


    }
}