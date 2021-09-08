package com.example.covidtracer;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class recyclerViewadapter  extends RecyclerView.Adapter<recyclerViewadapter.myviewholder> {
    ArrayList<model>arrayList;
    Context context;

    public recyclerViewadapter(ArrayList<model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemist,parent,false);

        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        model model=arrayList.get(position);
        holder.countryname.setText(model.getCounrtyName());
        Glide.with(context).load(model.getFlag()).apply(new RequestOptions().override(100,100)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
   public  void  filters(ArrayList<model>filteredlist){
        arrayList=filteredlist;
        notifyDataSetChanged();
   }

    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView countryname;
          public myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_flag);
            countryname=itemView.findViewById(R.id.country_name);
        }
    }
}
