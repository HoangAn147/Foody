package hcmute.spkt.Foody_18.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hcmute.spkt.Foody_18.model.RestaurantFavorite;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SQLiteFavoriteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorite.db";
    private static final int DATABSE_VERSION = 1;

    public SQLiteFavoriteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        String sql = "create table favorite(" +
                "id integer primary key autoincrement," +
                "uid text," +
                "restaurantid integer" +
                ")";
        db.execSQL(sql);
    }

    public long addFavorite(RestaurantFavorite o) {
        ContentValues c = new ContentValues();
        c.put("uid", o.getUid());
        c.put("restaurantid", o.getRestaurantid());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("favorite", null, c);
    }

    public List<RestaurantFavorite> getAllByUid(String uid) {
        String whereClause = "uid like ?";
        String[] whereArgs = {"%" + uid + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("favorite", null, whereClause, whereArgs, null, null, null);
        List<RestaurantFavorite> list = new ArrayList<>();
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            String uid1 = rs.getString(1);
            int restaurantid = rs.getInt(2);
            list.add(new RestaurantFavorite(id, uid1, restaurantid));
        }
        return list;
    }

    public int update(RestaurantFavorite o) {
        ContentValues c = new ContentValues();
        c.put("uid", o.getUid());
        c.put("restaurantid", o.getRestaurantid());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(o.getId())};
        return st.update("favorite", c, whereClause, whereArgs);
    }

    public int delete(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("favorite", whereClause, whereArgs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
