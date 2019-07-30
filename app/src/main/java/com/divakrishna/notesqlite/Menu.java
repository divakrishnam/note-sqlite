package com.divakrishna.notesqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    private Button btnTambah;
    private Button btnLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnTambah = findViewById(R.id.button_tambah);
        btnTambah.setOnClickListener(this);

        btnLihat = findViewById(R.id.button_lihat);
        btnLihat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_tambah:
                Intent c = new Intent(this, CreateNote.class);
                startActivity(c);
                break;
            case R.id.button_lihat:
                Intent r = new Intent(this, ReadNote.class);
                startActivity(r);
        }
    }
}
