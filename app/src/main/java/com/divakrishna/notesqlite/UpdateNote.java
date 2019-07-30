package com.divakrishna.notesqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateNote extends AppCompatActivity implements View.OnClickListener {

    private DBSource dbSource;

    private long id;
    private String isiCatatan;

    private EditText edIsiCatatan;

    private TextView tvId;

    private Button btnSimpan;
    private Button btnBatal;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        edIsiCatatan = findViewById(R.id.isi_catatan_update);
        tvId = findViewById(R.id.id_note);

        dbSource = new DBSource(this);
        dbSource.open();

        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getLong("id");
        isiCatatan = bundle.getString("isi_catatan");

        tvId.append(String.valueOf(id));
        edIsiCatatan.setText(isiCatatan);

        btnSimpan = findViewById(R.id.button_update);
        btnBatal = findViewById(R.id.button_cancel);

        btnSimpan.setOnClickListener(this);
        btnBatal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_update:
                note = new Note();
                note.setIsiCatatan(edIsiCatatan.getText().toString());
                note.setId(id);

                dbSource.updateNote(note);

                Intent intent = new Intent(this, ReadNote.class);
                startActivity(intent);
                UpdateNote.this.finish();
                dbSource.close();
                break;

            case R.id.button_cancel:
                finish();
                dbSource.close();
                break;
        }
    }
}
