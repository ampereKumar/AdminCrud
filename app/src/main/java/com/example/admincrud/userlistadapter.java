package com.example.admincrud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userlistadapter extends RecyclerView.Adapter<userlistadapter.ViewHolder>{

    int pos = -1;
    private ArrayList<userModel> userModelArrayList;
    private Context context;
    private UserClickInterface userClickInterface;

    public userlistadapter(ArrayList<userModel> userModelArrayList, Context context, UserClickInterface userClickInterface) {
        this.userModelArrayList = userModelArrayList;
        this.context = context;
        this.userClickInterface = userClickInterface;
    }

    @NonNull
    @Override
    public userlistadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userlistadapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        userModel userModel =userModelArrayList.get(position);
        holder.location.setText("Location: "+userModel.getLocation());
        holder.Name.setText("Name: "+userModel.getName());
        holder.contact.setText("Contact: "+userModel.getContact());
        holder.vno.setText("Vehicle no: "+userModel.getVnumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickInterface.onUserClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView location, Name, contact, vno;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.uslocation);
            Name = itemView.findViewById(R.id.usname);
            contact = itemView.findViewById(R.id.uscontact);
            vno = itemView.findViewById(R.id.usvehicle);
        }
    }

    public interface UserClickInterface {
        void onUserClick(int postion);
    }
}
