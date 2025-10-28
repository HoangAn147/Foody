package hcmute.spkt.Foody_18.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import hcmute.spkt.Foody_18.model.Cart;
import hcmute.spkt.Foody_18.model.User;

public class SQLiteUser extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABSE_VERSION = 1;

    public SQLiteUser (@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        String sql = "create table if not exists userTable(" +
                "id integer primary key autoincrement," +
                "username text," +
                "email text not null unique," +
                "password text" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addUser(User u) {
        ContentValues c = new ContentValues();
//        c.put("id", u.getId());
        c.put("email", u.getEmail());
        c.put("username", u.getUsername());
        c.put("password", u.getPassword());
        SQLiteDatabase st = getWritableDatabase();
        long rs = st.insert("userTable", null, c);
        return rs != -1;
    }

    public User getUserById(int id){
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("userTable", null, whereClause,
                whereArgs, null, null, null);
        if (rs.moveToNext()) {
            int idUser = rs.getInt(0);
            String username = rs.getString(1);
            String email = rs.getString(2);
            String password = rs.getString(3);
            User u = new User(idUser, email, password, username);
            return u;
        }
        return null;
    }

    //False if email not exist
    public boolean checkEmailExist(String email){
        String whereClause = "email=?";
        String[] whereArgs = {email};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("userTable", null, whereClause,
                whereArgs, null, null, null);
        return rs.moveToNext();
    }

    public boolean login (String email, String password){
        String whereClause = "email=?";
        String[] whereArgs = {email};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("userTable", null, whereClause,
                whereArgs, null, null, null);
        Log.e("LogVip", email);
        Log.e("LogVip", password);
        if (rs.moveToNext()){
            return password.equals(rs.getString(3));
        }
        return false;
    }

    public int getUserIdByEmail(String email){
        String whereClause = "email=?";
        String[] whereArgs = {email};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("userTable", null, whereClause,
                whereArgs, null, null, null);
        if (rs.moveToNext()){
            return rs.getInt(0);
        }
        return -1;
    }

    public boolean updateUser(User user, String newName){
        ContentValues u = new ContentValues();
        u.put("id", user.getId());
        u.put("email", user.getEmail());
        u.put("username", newName);
        u.put("password", user.getPassword());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(user.getId())};
        int affected = st.update("userTable", u, whereClause, whereArgs);
        return affected != 0;
    }

    public boolean updateEmail(User user, String newEmail){
        ContentValues u = new ContentValues();
        u.put("id", user.getId());
        u.put("email", newEmail);
        u.put("username", user.getUsername());
        u.put("password", user.getPassword());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(user.getId())};
        int affected = st.update("userTable", u, whereClause, whereArgs);
        return affected != 0;
    }

    public boolean updatePass(User user, String newPass) {
        ContentValues u = new ContentValues();
        u.put("id", user.getId());
        u.put("email", user.getEmail());
        u.put("username", user.getUsername());
        u.put("password", newPass);
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(user.getId())};
        int affected = st.update("userTable", u, whereClause, whereArgs);
        return affected != 0;
    }
}
