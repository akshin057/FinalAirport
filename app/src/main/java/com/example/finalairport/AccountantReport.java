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

import com.example.finalairport.usersData.AccountInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountantReport extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseUser user;
    TextView dateTV;
    TextView sum1TV;
    TextView sum2TV;
    TextView sum3TV;
    TextView sum4TV;
    Button returnBTN;
    ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountant_report);
        init();

        returnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountantReport.this, DirectorMain.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void init() {
        main = findViewById(R.id.main);
        dateTV = findViewById(R.id.dateTV);
        sum1TV = findViewById(R.id.sum1TV);
        sum2TV = findViewById(R.id.sum2TV);
        sum3TV = findViewById(R.id.sum3TV);
        sum4TV = findViewById(R.id.sum4TV);

        returnBTN = findViewById(R.id.returnBTN);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String newTV = dateTV.getText().toString() + " " + date;
        dateTV.setText(newTV);

        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("Accounter").child(date);

        if (!(myRef != null)){
            Snackbar.make(main, "Нет отчета за данную дату", Snackbar.LENGTH_SHORT).show();
        }

        AccountInfo accountInfo = new AccountInfo(new HashMap<String, Integer>());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        accountInfo.addInfo(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
                    }
                    String[] strings = accountInfo.getStringInfo();
                    sum1TV.setText(strings[0]);
                    sum2TV.setText(strings[1]);
                    sum3TV.setText(strings[2]);
                    sum4TV.setText(strings[3]);
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