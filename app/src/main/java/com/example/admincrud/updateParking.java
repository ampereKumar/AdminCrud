package com.example.admincrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
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

import java.util.HashMap;
import java.util.Map;

public class updateParking extends AppCompatActivity {
    private TextInputEditText pname, avail, fee, contact, img;
    private Button updateP, deletep;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String parkingid;
    private parkingModel parkingModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parking);
        firebaseDatabase = FirebaseDatabase.getInstance();
        pname = findViewById(R.id.pname);
        avail = findViewById(R.id.avalibility);
        contact = findViewById(R.id.upcontact);
        fee = findViewById(R.id.upfee);
//        img = findViewById(R.id.upimg);
        updateP = findViewById(R.id.updatepark);
        deletep = findViewById(R.id.deletepa);
        parkingModel = getIntent().getParcelableExtra("Locations");
        if(parkingModel!=null) {
            pname.setText(parkingModel.getPname());
            avail.setText(parkingModel.getAvail());
            contact.setText(parkingModel.getContact());
            fee.setText(parkingModel.getFees());
//            img.setText(parkingModel.getImage());
            parkingid = parkingModel.getParkingid();
        }

        databaseReference = firebaseDatabase.getReference("Locations").child(parkingid);
        updateP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String parkingl= pname.getText().toString();
                String availp= avail.getText().toString();
                String updcontact = contact.getText().toString();
                String updfees = fee.getText().toString();
//                String imag = img.getText().toString();


                Map<String,Object> map = new HashMap<>();
                map.put("pname",parkingl);
                map.put("avail", availp);
                map.put("parkingid", parkingid);
                map.put("contact",updcontact);
                map.put("fees",updfees);
//                map.put("image",imag);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(updateParking.this, "Parking updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(updateParking.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(updateParking.this, "Failed to update", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deletep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteparking();
            }
        });
    }

    private void deleteparking() {
        databaseReference.removeValue();
        Toast.makeText(this, "Parking is removed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(updateParking.this,MainActivity.class));
    }
}