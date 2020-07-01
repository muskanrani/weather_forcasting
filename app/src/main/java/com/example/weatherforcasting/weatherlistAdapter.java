package com.example.weatherforcasting;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class weatherlistAdapter extends RecyclerView.Adapter<weatherlistAdapter.weatherViewholder> {
    private static final String TAG= "weatherlistAdapter";
    private ArrayList<String> time= new ArrayList<>();
    private ArrayList<String> temperature= new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private Context mcontext;
    public weatherlistAdapter(Context context,ArrayList<String> arrayList1,ArrayList<String> arrayList2, ArrayList<String> arrayList3){
        time=arrayList1;
        temperature=arrayList2;
        description=arrayList3;
        mcontext=context;
    }
    @NonNull
    @Override
    public weatherViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new  weatherViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull weatherViewholder holder,int position) {
        Log.d(TAG,"onBindViewHolder: called.");
        holder.text_title.setText(time.get(position));
        holder.temp.setText(temperature.get(position));
        holder.des.setText(description.get(position));
    }

    @Override
    public int getItemCount() {
        return time.size();
    }

    public class weatherViewholder extends RecyclerView.ViewHolder{
        TextView text_title,temp,des;

        public weatherViewholder(@NonNull View itemView) {

            super(itemView);
            text_title=(TextView) itemView.findViewById(R.id.text_title);
            temp=(TextView) itemView.findViewById(R.id.temp);
            des=(TextView) itemView.findViewById(R.id.des);
        }
    }
}
