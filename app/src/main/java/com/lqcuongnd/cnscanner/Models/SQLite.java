package com.lqcuongnd.cnscanner.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLite extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myPetDB.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NGUOIDUNG = "tblUser";
    public static final String COL_ND_TENDN = "colTenDN";
    public static final String COL_ND_MATKHAU = "colMatkhau";
    public static final String COL_ND_TEN = "colTen";
    public static final String COL_ND_GIOITINH = "colGioiTinh";
    public static final String COL_ND_ID = "colId";
    public static final String COL_ND_LOAI = "colLoai";
    public static final String COL_ND_KICHHOAT = "colKichHoat";

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        ArrayList<String> queries = new ArrayList<String>();
        queries.add("create table " + TABLE_NGUOIDUNG + "(" + COL_ND_TENDN + " TEXT PRIMARY KEY, " + COL_ND_MATKHAU + " TEXT, " + COL_ND_TEN
                + " TEXT, " + COL_ND_GIOITINH + " INTEGER, " + COL_ND_ID + " TEXT, " + COL_ND_LOAI + " INTEGER, " + COL_ND_KICHHOAT + " INTEGER)");

        /*arr[0] = String.format("create table %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT)", PETS_TABLE_NAME, PETS_COLUMN_CODE, PETS_COLUMN_NAME, PETS_COLUMN_BREED);
        arr[1] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Vàng", "Chó cỏ");
        arr[2] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Milu", "Chó đốm");
        arr[3] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Mỡ", "Chó Husky");
        arr[4] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Quàng Thượng", "Mèo mướp");
        arr[5] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Bụn", "Chó Shiba");
        arr[6] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Kyhuahua", "Chó bull");
        arr[7] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Bốp", "Chó cỏ");
        arr[8] = String.format("insert into %s(%s, %s) values('%s', '%s')", PETS_TABLE_NAME, PETS_COLUMN_NAME, PETS_COLUMN_BREED, "Méo", "Mèo tam hoàng");
*/

        for (String s : queries)
            db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NGUOIDUNG);
        onCreate(db);
    }

    public void logIn(NguoiDung user) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ND_TENDN, user.getTenDN());
        values.put(COL_ND_MATKHAU, user.getMatKhau());
        values.put(COL_ND_TEN, user.getTen());
        values.put(COL_ND_GIOITINH, user.getGioiTinh());
        values.put(COL_ND_ID, user.getId());
        values.put(COL_ND_LOAI, user.getLoai());
        values.put(COL_ND_KICHHOAT, user.getKichHoat());

        db.insert(TABLE_NGUOIDUNG, null, values);
        db.close();
    }

    public NguoiDung getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        NguoiDung user;
        Cursor res = db.rawQuery("select * from " + TABLE_NGUOIDUNG, null);
        boolean b = res.moveToNext();
        user = new NguoiDung(res.getString(res.getColumnIndex(COL_ND_TENDN)), res.getString(res.getColumnIndex(COL_ND_MATKHAU)), res.getString(res.getColumnIndex(COL_ND_TEN)),
                res.getInt(res.getColumnIndex(COL_ND_GIOITINH)) == 1 ? true : false, res.getString(res.getColumnIndex(COL_ND_ID)), res.getInt(res.getColumnIndex(COL_ND_LOAI)),
                res.getInt(res.getColumnIndex(COL_ND_KICHHOAT)) == 1 ? true : false);

        db.close();
        return user;
    }

    public void logOut() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NGUOIDUNG, null, null);
        db.close();
    }

    public Boolean isLogIn() {
        SQLiteDatabase db = this.getReadableDatabase();
        NguoiDung user;
        Cursor res = db.rawQuery("select * from " + TABLE_NGUOIDUNG, null);
        if(res.moveToFirst())
            return  true;
        return false;
    }

}
