package com.example.firebasestoreretrivedata;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Person> {

    private Activity context;
    private List<Person> personList;



    public CustomAdapter(Activity context, List<Person> personList) {


        super(context,R.layout.sample_layout, personList);

        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

       View view =  inflater.inflate(R.layout.sample_layout, null, true);

        Person person = personList.get(position);

        TextView nameTextView = view.findViewById(R.id.nameTextViewId);
        TextView ageTextView = view.findViewById(R.id.ageTextViewId);


        nameTextView.setText(person.getName());
        ageTextView.setText(person.getAge());



        return view;
    }
}
