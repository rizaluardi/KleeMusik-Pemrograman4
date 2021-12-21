package com.appsmor.kleemusik.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appsmor.kleemusik.model.Musik;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_musik"; // NAMA DATABASE
    private static final String TABLE_MUSIK = "table_musik"; // NAMA TABEL
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MUSIK = "musik";
    private static final String COLUMN_ARTIS = "tempat_lahir";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // FUNGSI UNTUK MEMBUAT DATABASENYA
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_MUSIK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_MUSIK + " TEXT,"
                + COLUMN_ARTIS + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSIK);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA MAHASISWA
    public void tambahMusik(Musik musik){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MUSIK, musik.getMusik());
        values.put(COLUMN_ARTIS, musik.getArtis());

        db.insert(TABLE_MUSIK, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA MAHASISWA
    public Musik getMusik(int id_musik){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MUSIK, new String[]{COLUMN_ID, COLUMN_MUSIK, COLUMN_ARTIS},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_musik)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Musik musik = new Musik(cursor.getString(1), cursor.getString(2));
        return musik;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA MAHASISWA
    public List<Musik> getSemuaMusik(){
        List<Musik> musikList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MUSIK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Musik musik = new Musik(cursor.getString(1), cursor.getString(2));
                musikList.add(musik);
            } while (cursor.moveToNext());
        }
        return musikList;
    }

    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
    public int getMusikCount(){
        String countQuery = "SELECT * FROM " + TABLE_MUSIK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // FUNGSI UPDATE DATA MUSIK
    public int updateDataMusik(Musik musik) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MUSIK, musik.getMusik());
        values.put(COLUMN_ARTIS, musik.getArtis());
        return db.update(TABLE_MUSIK, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(musik.getId())});
    }

    // FUNGSI HAPUS DATA 1 MAHASISWA
    public void hapusDataMusik(Musik musik) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MUSIK, COLUMN_ID + " = ?",
                new String[]{String.valueOf(musik.getId())});
        db.close();
    }

    // FUNGSI UNTUK MENGHAPUS SEMUA DATA MAHASISWA
    public void hapusSemuaDataMusik(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_MUSIK);
    }
}
