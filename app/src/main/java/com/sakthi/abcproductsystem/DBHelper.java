package com.sakthi.abcproductsystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "abc_product_system.db";
    public static final String PRODUCTS_TABLE_NAME = "TBL_Product";
    public static final String PRODUCTS_COLUMN_ID = "id";
    public static final String PRODUCTS_COLUMN_PNAME = "product_name";
    public static final String PRODUCTS_COLUMN_DESC = "description";
    public static final String PRODUCTS_COLUMN_TYPE = "type";
    public static final String PRODUCTS_COLUMN_QUANTITY = "quantity";
    public static final String PRODUCTS_COLUMN_UNIT_PRICE = "unit_price";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table TBL_Product " +
                        "(id integer primary key, product_name text,description text,type text, quantity int,unit_price int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS TBL_Product");
        onCreate(db);
    }

    public boolean insertProduct (String product_name, String description, String type, int quantity, int unit_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_name", product_name);
        contentValues.put("description", description);
        contentValues.put("type", type);
        contentValues.put("quantity", quantity);
        contentValues.put("unit_price", unit_price);
        db.insert("TBL_Product", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from TBL_Product where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PRODUCTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String product_name, String description, String type, int quantity, int unit_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_name", product_name);
        contentValues.put("description", description);
        contentValues.put("type", type);
        contentValues.put("quantity", quantity);
        contentValues.put("unit_price", unit_price);
        db.update("TBL_Product", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("TBL_Product",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from TBL_Product", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PRODUCTS_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
