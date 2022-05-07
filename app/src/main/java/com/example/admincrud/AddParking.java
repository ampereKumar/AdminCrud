package com.example.admincrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddParking extends AppCompatActivity {

    private TextInputEditText pname, avail, fee, contact, img;
    private Button addP;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String parkingid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);
        pname = findViewById(R.id.pname);
        avail = findViewById(R.id.avalibility);
        fee = findViewById(R.id.adfee);
        contact = findViewById(R.id.adcontact);
//        img = findViewById(R.id.addimg);
        addP = findViewById(R.id.addparking);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Locations");

        addP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parkingl= pname.getText().toString();
                String availp= avail.getText().toString();
                String fees = fee.getText().toString();
                String cont = contact.getText().toString();
//                String image = img.getText().toString();
                parkingid = parkingl;
                parkingModel parkingModel= new parkingModel (parkingl,availp,parkingid, cont, fees);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(parkingl).setValue(parkingModel);
                        Toast.makeText(AddParking.this,"Location Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddParking.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddParking.this,"Error while adding location", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}