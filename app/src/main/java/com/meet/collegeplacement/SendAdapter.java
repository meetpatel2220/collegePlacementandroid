package com.meet.collegeplacement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SendAdapter extends RecyclerView.Adapter<SendAdapter.Postviewholder> {

   private Context context;
   private List<MeetModel> list;

    public SendAdapter(Context context, List<MeetModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Postviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.custom_row1,parent,false);


        return new Postviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Postviewholder holder, int position) {
        MeetModel model = list.get(position);

        holder.company.setText(model.getRequest().get(position).getCompany());
        holder.salary.setText(model.getRequest().get(position).getSalary());
        holder.time.setText(model.getRequest().get(position).getTime());



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Postviewholder extends RecyclerView.ViewHolder {

             TextView company;
        TextView salary;
        TextView time;

        public Postviewholder(@NonNull View itemView) {
            super(itemView);

            company=itemView.findViewById(R.id.company);

            salary=itemView.findViewById(R.id.salary);
            time=itemView.findViewById(R.id.time);



        }
    }



}
