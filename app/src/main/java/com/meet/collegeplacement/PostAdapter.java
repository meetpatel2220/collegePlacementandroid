package com.meet.collegeplacement;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Postviewholder> {

   private Context context;
   private List<Hero> hero;
    private SharedPreferences sharedPreferences;
    private static final String mypreference = "mypreference";

    public PostAdapter(Context context, List<Hero> hero) {
        this.context = context;
        this.hero = hero;
    }

    @NonNull
    @Override
    public Postviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.custom_row,parent,false);


        return new Postviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Postviewholder holder, int position) {

        holder.company.setText(hero.get(position).getCompany());
        holder.salary.setText(hero.get(position).getSalary());
        holder.time.setText(hero.get(position).getTime());

        holder.post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(ApiProfile.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiProfile api=retrofit.create(ApiProfile.class);

                sharedPreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);


//                Toast.makeText(context,hero.get(position).getCompany()+ hero.get(position).getSalary()
//                        +hero.get(position).getTime()+hero.get(position).get_id(), Toast.LENGTH_SHORT).show();

                JsonObject object=new JsonObject();
                object.addProperty("company",hero.get(position).getCompany());
                object.addProperty("salary",hero.get(position).getSalary());
                object.addProperty("time",hero.get(position).getTime());
                object.addProperty("companyid",hero.get(position).get_id());

                Call<ProfileModel> call=api.getRequestDetails(sharedPreferences.getString("token",""),object);

       call.enqueue(new Callback<ProfileModel>() {
           @Override
           public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {

               if (response.body().getSuccess().equals(true)) {
                   Toast.makeText(context, "company send done", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<ProfileModel> call, Throwable t) {


           }
       });


            }
        });


    }


    @Override
    public int getItemCount() {
        return hero.size();
    }

    public class Postviewholder extends RecyclerView.ViewHolder {

             TextView company;
        TextView salary;
        TextView time;
        Button post;

        public Postviewholder(@NonNull View itemView) {
            super(itemView);

            company=itemView.findViewById(R.id.company);

            salary=itemView.findViewById(R.id.salary);
            time=itemView.findViewById(R.id.time);

            post=itemView.findViewById(R.id.post);


        }
    }



}
