package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList user;
    private final ArrayList mail;
    private final ArrayList pass;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(v);
    }

    public MyAdapter(Context context, ArrayList user, ArrayList email, ArrayList pass) {
        this.context = context;
        this.user = user;
        this.mail = email;
        this.pass = pass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.user.setText(String.valueOf(user.get(position)));
        holder.mail.setText(String.valueOf(mail.get(position)));
        holder.pass.setText(String.valueOf(pass.get(position)));
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user,mail,pass;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user);
            mail = itemView.findViewById(R.id.mail);
            pass = itemView.findViewById(R.id.pass);
        }
    }
}
