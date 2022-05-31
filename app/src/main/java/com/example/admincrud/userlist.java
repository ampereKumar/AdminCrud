package com.example.admincrud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class userlist extends AppCompatActivity implements userlistadapter.UserClickInterface{

    private RecyclerView lists;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<userModel> userModelArrayList;
    private RelativeLayout bottomsheetuRL;
    private userlistadapter userlistadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        lists = findViewById(R.id.uList);
        firebaseDatabase= firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");
        userModelArrayList = new ArrayList<>();
        bottomsheetuRL = findViewById(R.id.userbottomsheet);
        userlistadapter = new userlistadapter(userModelArrayList, this,this);
        lists.setLayoutManager(new LinearLayoutManager(this));
        lists.setAdapter(userlistadapter);

        getalluser();
    }

    private void getalluser() {
        userModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                userModelArrayList.add(snapshot.getValue(userModel.class));
                userlistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                userlistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                userlistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                userlistadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onUserClick(int postion) {
        displayBottomSheet(userModelArrayList.get(postion));
    }
    private void displayBottomSheet(userModel userModel) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottomsheet_user_dialog,bottomsheetuRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView name = layout.findViewById(R.id.list_name);
        TextView contact = layout.findViewById(R.id.list_contact);
//        Button del = layout.findViewById(R.id.userdelete);
        name.setText("Name: "+userModel.getName());
        contact.setText("Contact: "+userModel.getContact());

//        del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteuser();
//            }
//        });
    }

    private void deleteuser() {
        databaseReference.removeValue();
        Toast.makeText(this, "User is removed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(userlist.this,MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.user:
                Intent i = new Intent(userlist.this,userlist.class);
                startActivity(i);
                this.finish();
                break;
            case R.id.privlist:
                i = new Intent(userlist.this,privacylist.class);
                startActivity(i);
                this.finish();
                break;
            case R.id.logOut:
                Toast.makeText(this, "Log out successfull", Toast.LENGTH_SHORT).show();
                i = new Intent(userlist.this,LoginActivity.class);
                startActivity(i);
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}