package com.example.admincrud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class parkinglistadapter extends RecyclerView.Adapter<parkinglistadapter.Viewholder> {
    int lastpos= -1;
    private ArrayList<parkingModel> parkingModelArrayList;
    private Context context;
    private parkingclickinterface parkingclickinterface;

    public parkinglistadapter(ArrayList<parkingModel> parkingModelArrayList, Context context, parkinglistadapter.parkingclickinterface parkingclickinterface) {
        this.parkingModelArrayList = parkingModelArrayList;
        this.context = context;
        this.parkingclickinterface = parkingclickinterface;
    }

    @NonNull
    @Override
    public parkinglistadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.parkinglist,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull parkinglistadapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
        parkingModel parkingModel= parkingModelArrayList.get(position);
        holder.placename.setText("Location: "+parkingModel.getPname());
        holder.avalibility.setText("Availibility: "+parkingModel.getAvail());
//        Picasso.get().load(parkingModel.getImage().into(holder.img));
//        holder.contact.setText("Contact Person: "+parkingModel.getContact());
        holder.fee.setText("Parking fee per hr: Rs "+parkingModel.getFees());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parkingclickinterface.onParkingClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parkingModelArrayList.size();
    }

    public interface parkingclickinterface{
        void onParkingClick(int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView placename, avalibility, fee;
        private ImageView img;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            placename= itemView.findViewById(R.id.placenam);
            avalibility= itemView.findViewById(R.id.avalibilit);
//            img = itemView.findViewById(R.id.imgivparking);
//            contact = itemView.findViewById(R.id.adcontact);
            fee = itemView.findViewById(R.id.sfee);
        }
    }
}
