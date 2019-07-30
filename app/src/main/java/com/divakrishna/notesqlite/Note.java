package com.divakrishna.notesqlite;

public class Note {
    private long id;
    private String isi_catatan;
    private String tanggal_catatan;

    public Note(){

    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getIsiCatatan(){
        return isi_catatan;
    }

    public void setIsiCatatan(String isi_catatan){
        this.isi_catatan = isi_catatan;
    }

    public String getTanggalCatatan(){
        return tanggal_catatan;
    }

    public void setTanggalCatatan(String tanggal_catatan){
        this.tanggal_catatan = tanggal_catatan;
    }

    public String toString(){
        return "Catatan "+isi_catatan+" "+tanggal_catatan;
    }
}
