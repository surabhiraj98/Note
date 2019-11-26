package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class noteActivity extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("NoteID",-1);


        //Todo add new Note
        if(noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        }else{
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
        }




        //Todo change the Edit tet after editor
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //new edit
                MainActivity.notes.set(noteId,String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                //share prefer
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.noteapplication", Context.MODE_PRIVATE);
                HashSet<String> setNote = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("NOOtes", setNote).apply();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
