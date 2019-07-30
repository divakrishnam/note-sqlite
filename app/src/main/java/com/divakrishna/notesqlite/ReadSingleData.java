package com.divakrishna.notesqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadSingleData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_single_data);

        TextView tvIsiCatatan = findViewById(R.id.tv_isi_catatan);
        TextView tvTanggalCatatan = findViewById(R.id.tv_tanggal_catatan);

        tvIsiCatatan.setText(getIntent().getExtras().getString("isi_catatan"));
        tvTanggalCatatan.setText(getIntent().getExtras().getString("tanggal_catatan"));

        Button btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
