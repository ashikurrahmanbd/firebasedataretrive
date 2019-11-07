package com.example.firebasestoreretrivedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Information extends AppCompatActivity {

    private ListView listView;

    DatabaseReference databaseReference;
    List<Person> personList;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        databaseReference = FirebaseDatabase.getInstance().getReference("person");
        personList = new ArrayList<>();

        customAdapter = new CustomAdapter(this, personList);



    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                personList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Person person = dataSnapshot1.getValue(Person.class);
                    personList.add(person);

                }
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
