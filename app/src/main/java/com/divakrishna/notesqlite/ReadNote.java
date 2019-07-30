package com.divakrishna.notesqlite;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ReadNote extends ListActivity implements AdapterView.OnItemLongClickListener {

    private DBSource dbSource;

    private ArrayList<Note> values;

    private Button ubahButton;
    private Button hapusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        dbSource = new DBSource(this);
        dbSource.open();

        values = dbSource.getAllNote();

        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView listView = findViewById(android.R.id.list);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, int position, final long id) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();

        final Note note = (Note) getListAdapter().getItem(position);
        ubahButton = dialog.findViewById(R.id.button_ubah_data);
        hapusButton = dialog.findViewById(R.id.button_hapus_data);

        ubahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUbah(note.getId());
                dialog.dismiss();
            }
        });
        return true;
    }

    public void switchToUbah(long id){
        Note note = dbSource.getNote(id);
        Intent intent = new Intent(this, UpdateNote.class);
        Bundle bundle = new Bundle();
        bundle.putLong("id", note.getId());
        bundle.putString("isi_catatan", note.getIsiCatatan());
        bundle.putString("tanggal_catatan", note.getTanggalCatatan());

        intent.putExtras(bundle);
        finale();
        startActivity(intent);
    }

    public void finale(){
        ReadNote.this.finish();
        dbSource.close();
    }

    @Override
    protected void onResume() {
        dbSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dbSource.close();
        super.onPause();
    }
}
