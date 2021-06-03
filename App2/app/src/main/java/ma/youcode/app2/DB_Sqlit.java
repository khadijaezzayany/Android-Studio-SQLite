package ma.youcode.app2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Sqlit extends SQLiteOpenHelper {
    public static final String BDname = "data.db";
    public DB_Sqlit( Context context) {
        super(context, BDname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean insertData(String name,  String email){

        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("email", email);

        long result = db.insert("users", null, contentValues);
        if (result == -1)
            return  false;
        else
            return  true;
    }


    public ArrayList getAllUsers(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from users order by id desc", null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            arrayList.add(t1 + " - "+ t2 +" - "+ t3);
            res.moveToNext();
        }
        return arrayList;
    }


    public Boolean update(String id, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("email", email);

        db.update("users", contentValues, "id= ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users", "id = ?", new String[]{id});
    }
}
