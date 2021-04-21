package com.example.mydatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.icu.lang.UProperty.AGE;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Hostel.db";
    private static final String TABLE_NAME = "hostel_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String SEATS = "Seats";
    private static final String ADDRESS = "Address";
    private static final String PHONE = "Phone";

    private static final String WIFI = "Wifi";
    private static final String WATER = "Water";
    private static final String TABLEINFO = "Table_";
    private static final String CHAIR = "Chair";
    private static final String BED = "Bed";
    private static final String FAN = "Fan";
    private static final String ELECTRICITY_BILL = "ElectricityBill";
    private static final String FEMALE = "Female";

    private static final String LATTITUDE = "Lattitude";
    private static final String LONGITUDE = "Longitude";

    private static final String SINGLE_ROOM_FARE = "Single_Seat_Fare";
    private static final String DOUBLE_ROOM_FARE = "Double_Seat_Fare";
    private static final String TRIPPLE_ROOM_FARE = "Tripple_Seat_Fare";

    private static final int VERSION_NUMBER = 9;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255), "+SEATS+" INTEGER, "+ADDRESS+" VARCHAR(255), "+PHONE+" INTEGER, "+WIFI+" BOOLEAN, "+WATER+" BOOLEAN, "+TABLEINFO+" BOOLEAN, "+CHAIR+" BOOLEAN, "+BED+" BOOLEAN, "+FAN+" BOOLEAN, "+ELECTRICITY_BILL+" BOOLEAN, "+SINGLE_ROOM_FARE+" INTEGER, "+DOUBLE_ROOM_FARE+" INTRGER, "+TRIPPLE_ROOM_FARE+" INTEGER, "+LATTITUDE+" VARCHAR(20),"+LONGITUDE+" VARCHAR(20), "+FEMALE+" BOOLEAN)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM  " +TABLE_NAME;


    private Context context;
    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME,null, VERSION_NUMBER );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{

            Toast.makeText(context,"onCreate is called",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);

        }catch (Exception e){

            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate( sqLiteDatabase );

        }catch ( Exception e){

            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }




    }
    public long insertdata(String name, String seats, String address, String phone, boolean wifi, boolean water, boolean table, boolean chair, boolean bed, boolean fan, boolean electricityBill, String singleSeatFare, String doubleSeatFare, String trippleSeatFare, String lattitude, String longitude, boolean female)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( NAME,name );
        contentValues.put( SEATS,seats );
        contentValues.put( ADDRESS,address );
        contentValues.put( PHONE,phone );
        contentValues.put( WIFI,wifi );
        contentValues.put( WATER,water );
        contentValues.put( TABLEINFO,table );
        contentValues.put( CHAIR,chair );
        contentValues.put( BED,bed );
        contentValues.put( FAN,fan );
        contentValues.put( ELECTRICITY_BILL,electricityBill);
        contentValues.put( SINGLE_ROOM_FARE,singleSeatFare);
        contentValues.put( DOUBLE_ROOM_FARE,doubleSeatFare);
        contentValues.put( TRIPPLE_ROOM_FARE,trippleSeatFare);
        contentValues.put( LATTITUDE,lattitude );
        contentValues.put( LONGITUDE,longitude );
        contentValues.put( FEMALE,female );
        long rowId = sqLiteDatabase.insert( TABLE_NAME,null,contentValues );
        return rowId;
    }

    public Cursor displayAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery( SELECT_ALL,null);
        return cursor;

    }

}
