package com.example.firebasestoreretrivedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText name,age;
    Button save, loadData;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("person");

        name = (EditText) findViewById(R.id.nameEditTextId);
        age = (EditText) findViewById(R.id.ageEditTextId);
        save = (Button) findViewById(R.id.saveButtonId);
        loadData = (Button) findViewById(R.id.loadButtonId);
        //unique Key for dataBase each child
        listView = (ListView) findViewById(R.id.listViewId);





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String personName = name.getText().toString().trim();
                String personAge = age.getText().toString().trim();



                if(personName.isEmpty()){
                    name.setError("Field must not be empty");
                    name.requestFocus();
                    return;
                }
                if(personAge.isEmpty()){
                    age.setError("Age must not be empty");
                    age.requestFocus();
                    return;
                }

                String key = databaseReference.push().getKey();
                //Creating the Object of Person Class for Data Handler
                Person person = new Person(personName, personAge);
                databaseReference.child(key).setValue(person);

                Toast.makeText(MainActivity.this, "Data has inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Information.class);
                startActivity(intent);

            }
        });




    }
}
