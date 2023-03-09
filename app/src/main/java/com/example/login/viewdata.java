package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class viewdata extends AppCompatActivity {
    RecyclerView recyclerView;
    Button delete,add;
    MyAdapter adapter;
        ArrayList<String> user,mail,pass;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
        db = new DatabaseHelper(this);
        user = new ArrayList<>();
        mail = new ArrayList<>();
        pass = new ArrayList<>();
        add = (Button) findViewById(R.id.add);
        recyclerView=findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this,user,mail,pass);


        MyAdapter adapter = new MyAdapter(this,user,mail,pass);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),add.class);
                startActivity(i);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

    }


    String deleteduser = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            switch (direction){
            case ItemTouchHelper.LEFT:
                    deleteduser = user.get(position);
                    user.remove(position);
                    adapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView , deleteduser, Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                user.add(position,deleteduser);
                                adapter.notifyItemInserted(position);
                            }
                        });

                break;
            case  ItemTouchHelper.RIGHT:

                break;
        }
        }
    };

    private void displaydata() {
        Cursor cursor = db.getdata();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data Inserted", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                user.add(cursor.getString(0));
                mail.add(cursor.getString(1));
                pass.add(cursor.getString(2));
            }
        }

    }
    public void removedata(){

        Cursor cursor = db.getdata();
        if(cursor.getCount() == 0){
            int position = cursor.getPosition();
            Toast.makeText(this, "No data Inserted", Toast.LENGTH_SHORT).show();
            db.remove(user.get(position));
            user.remove(position);
            adapter.notifyDataSetChanged();
        }

    }


}

