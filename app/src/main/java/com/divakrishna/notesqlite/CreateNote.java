package com.divakrishna.notesqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class CreateNote extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private EditText etNote;
    private DBSource dbSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        btnSubmit = findViewById(R.id.buttom_submit);
        etNote = findViewById(R.id.isi_catatan);

        btnSubmit.setOnClickListener(this);

        dbSource = new DBSource(this);

        dbSource.open();
    }

    @Override
    public void onClick(View v) {
        String isiCatatan = null;
        Date tanggalCatatan = Calendar.getInstance().getTime();

        Note note;

        if(etNote.getText() != null){
            isiCatatan = etNote.getText().toString();
        }

        switch (v.getId()){
            case R.id.buttom_submit:
                note = dbSource.createNote(isiCatatan, tanggalCatatan.toString());
                Toast.makeText(this, "Catatan berhasil dicatat.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
