package com.divakrishna.notesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class DBSource {
    private SQLiteDatabase database;

    private DBHelper dbHelper;

    private String[] allCollumns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_ISI_CATATAN, DBHelper.COLUMN_TANGGAL_CATATAN};

    public DBSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Note createNote(String isi_catatan, String tanggal_catatan){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ISI_CATATAN, isi_catatan);
        values.put(DBHelper.COLUMN_TANGGAL_CATATAN, tanggal_catatan);

        long insertId = database.insert(DBHelper.TABLE_NAME, null, values);

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allCollumns, DBHelper.COLUMN_ID +"="+insertId, null, null, null, null);
        cursor.moveToFirst();
        Note newNote = cursorToNote(cursor);
        cursor.close();

        return newNote;
    }

    private Note cursorToNote(Cursor cursor){
        Note note = new Note();

        note.setId(cursor.getLong(0));
        note.setIsiCatatan(cursor.getString(1));
        note.setTanggalCatatan(cursor.getString(2));

        return note;
    }

    public ArrayList<Note> getAllNote(){
        ArrayList<Note> daftarNote = new ArrayList<Note>();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allCollumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Note note = cursorToNote(cursor);
            daftarNote.add(note);
            cursor.moveToNext();
        }

        cursor.close();
        return daftarNote;
    }

    public Note getNote(long id){
        Note note = new Note();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allCollumns, "_id = "+id, null, null, null, null);
        cursor.moveToFirst();
        note = cursorToNote(cursor);
        cursor.close();
        return note;
    }

    public void updateNote(Note note){
        String strFilter = "_id = "+note.getId();
        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_ISI_CATATAN, note.getIsiCatatan());

        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }
}
