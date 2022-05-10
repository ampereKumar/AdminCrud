package com.example.admincrud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements parkinglistadapter.parkingclickinterface{

    private RecyclerView lists;
    private FloatingActionButton addFab, user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<parkingModel> parkingModelArrayList;
    private RelativeLayout bottomsheetRL;
    private parkinglistadapter parkinglistadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lists = findViewById(R.id.List);
        addFab= findViewById(R.id.addFab);
        user = findViewById(R.id.ulist);
        firebaseDatabase= firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Locations");
        parkingModelArrayList = new ArrayList<>();
        bottomsheetRL = findViewById(R.id.idRlBSheet);
        parkinglistadapter = new parkinglistadapter(parkingModelArrayList, this,this);
        lists.setLayoutManager(new LinearLayoutManager(this));
        lists.setAdapter(parkinglistadapter);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddParking.class));
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,userlist.class));
            }
        });

        getallparking();
    }

    private void getallparking() {
        parkingModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                parkingModelArrayList.add(snapshot.getValue(parkingModel.class));
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onParkingClick(int position) {
        displayBottomSheet(parkingModelArrayList.get(position));
    }
    private void displayBottomSheet(parkingModel parkingModel) {
        final BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomsheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView placename = layout.findViewById(R.id.placename);
        TextView availibility = layout.findViewById(R.id.avalibility);
        TextView contact = layout.findViewById(R.id.bcontact);
//        ImageView img = layout.findViewById(R.id.idimg);
        Button editp = layout.findViewById(R.id.updatepar);
        //Button delete = layout.findViewById(R.id.deletepa);

        placename.setText("Location: "+parkingModel.getPname());
        availibility.setText("Avalibility: "+parkingModel.getAvail());
        contact.setText("Contact: "+parkingModel.getContact());

        editp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,updateParking.class);
                i.putExtra("Locations", parkingModel);
                startActivity(i);
            }
        });
    }
}