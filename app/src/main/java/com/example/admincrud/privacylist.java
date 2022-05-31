package com.example.admincrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class privacylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacylist);
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
                Intent i = new Intent(privacylist.this,userlist.class);
                startActivity(i);
                this.finish();
                break;
            case R.id.privlist:
                i = new Intent(privacylist.this,privacylist.class);
                startActivity(i);
                this.finish();
                break;
            case R.id.logOut:
                Toast.makeText(this, "Log out successfull", Toast.LENGTH_SHORT).show();
                i = new Intent(privacylist.this,LoginActivity.class);
                startActivity(i);
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}