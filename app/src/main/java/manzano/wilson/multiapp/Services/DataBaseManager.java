package manzano.wilson.multiapp.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Vector;

/**
 * Created by HP on 22/03/2017.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    public static String dataBaseName="CECDatabase";
    public static int version=3;
    public static String SCRIPTS_FOR_DEFINE_TABLES[]
            ={
            "CREATE TABLE IF NOT EXISTS " +
                    "users(" +
                    "user_code integer primary key autoincrement," +
                    "user_name text," +
                    "user_password text" +
                    ")",

            "INSERT INTO  users (user_name,user_password) values ('admin','123')",

            "CREATE TABLE IF NOT EXISTS " +
                    "places(" +
                    "place_code integer primary key autoincrement," +
                    "place_name text," +
                    "place_description text," +
                    "place_rating text," +
                    "place_longitude text," +
                    "place_latitude text," +
                    "place_image_uri text" +
                    ")"

    };

    public static String SCRIPTS_FOR_DELETE_TABLES[]
            ={"DROP TABLE IF EXISTS users","DROP TABLE IF EXISTS places"};

    private SQLiteDatabase sqLiteDatabase;

    public DataBaseManager(Context context) {
        super(context, dataBaseName, null, version);
        initializeDataBase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //android.os.Debug.waitForDebugger();
        for (String currentScript:SCRIPTS_FOR_DEFINE_TABLES){
            db.execSQL(currentScript);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            for (String currentScript:SCRIPTS_FOR_DELETE_TABLES){
                db.execSQL(currentScript);
            }
            this.onCreate(db);
        }
    }

    public void initializeDataBase(){
        try {
            sqLiteDatabase = this.getWritableDatabase();
        }catch (Exception error){
            Log.e("DataBaseManager","initializeDataBase: error - "+error.toString());
        }
    }

    public void closeDataBaseConnection(){
        if(sqLiteDatabase!=null){
            if(sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
            }
        }
    }
    //Content Value is the object that you will insert
    public  String insertIntoTable(String tableName,ContentValues contentValues){
        String result="";
        try{
            result="Rows affected: "+sqLiteDatabase.insertOrThrow(tableName, null, contentValues );
        }catch(Exception error){
            result=error.toString();
        }
        return result;
    }

    /*WhereClause is the query condition, content value is the object that you will insert, example:
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_order", orderObject.getOrder());
        contentValues.put("order_pager", orderObject.getPager());
        mDataBaseManager.insertIntoTable("orders", contentValues);
    */

    public  String updateTable(String tableName,ContentValues contentValues,String whereClause){
        String result="";
        try{
            result="Rows affected: "+sqLiteDatabase.update(tableName,  contentValues,whereClause,null );
        }catch(Exception error){
            result=error.toString();
        }
        return result;
    }
   /*WhereClause is the query condition, example:
            mDataBaseManager.deleteFromTable("orders", "order_pager = " + mArrayList.get(position).getPager());
    */
    public  String deleteFromTable(String tableName,String whereClause){
        String result="";
        try{
            result="Rows affected: "+sqLiteDatabase.delete(tableName,  whereClause,null );
        }catch(Exception error){
            result=error.toString();
        }
        return result;
    }

    public  String executeThisSQL(String sql){
        String result="";
        try{
            sqLiteDatabase.execSQL(sql);
        }catch(Exception error){
            result=error.toString();
        }
        return result;
    }

    public void test(){
        this.executeThisSQL("insert into users (user_name,user_password) values('dummy','123')");
    }


    /*queryClause is the query for select, and this return the query:

        String queryString = "select order_order,order_pager from orders";
        Vector<Object[]> result = mDataBaseManager.executeQuery(queryString);
    */

    public Vector<Object[]> executeQuery(String queryClause){
        Vector<Object[]> result=new Vector<Object[]>();
        Cursor cursor=null;
        try{
            cursor=sqLiteDatabase.rawQuery(queryClause,null);
            String[] columnNames=cursor.getColumnNames();
            result.add(columnNames);
            while (cursor.moveToNext()) {
                Object[] row = new Object[columnNames.length];
                for (int columnIndex = 0; columnIndex < cursor.getColumnCount(); columnIndex++) {
                    int type=cursor.getType (columnIndex);
                    if(Cursor.FIELD_TYPE_INTEGER==type){
                        row[columnIndex]=cursor.getInt(columnIndex);
                    }
                    if(Cursor.FIELD_TYPE_FLOAT==type){
                        row[columnIndex]=cursor.getFloat(columnIndex);
                    }
                    if(Cursor.FIELD_TYPE_STRING==type){
                        row[columnIndex]=cursor.getString(columnIndex);
                    }
                    if(Cursor.FIELD_TYPE_BLOB==type){
                        row[columnIndex]=cursor.getBlob(columnIndex);
                    }
                }
                result.add(row);
            }
        }catch(Exception error){
            result=null;
        }
        finally{
            if(cursor!=null){
                if(!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return result;
    }
}