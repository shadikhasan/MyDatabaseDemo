package com.example.mydatabasedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, seatsEditText, addressEditText, phoneEditText, lattEditText, longEditText, singleRoomEditText, doubleRoomEditText, trippleRoomEditText;
    private CheckBox wifiBox, waterBox, tableBox, chairBox, bedBox, fanBox, electricityBillBox, femaleBox;
    private Button addButton,displayButton;

    boolean hostelType = false;

    boolean isAllFieldChecked = false;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        databaseHelper = new DatabaseHelper(this );
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        nameEditText = findViewById( R.id.nameEditTextId );
        seatsEditText = findViewById( R.id.seatsEditTextId);
        addressEditText = findViewById( R.id.addressEditTextId );
        phoneEditText = findViewById( R.id.phoneEditTextId );
        lattEditText = findViewById( R.id.lattitudeEditTextId );
        longEditText = findViewById( R.id.longitudeEditTextId );
        singleRoomEditText = findViewById( R.id.singleRoomEditTextId );
        doubleRoomEditText = findViewById( R.id.doubleRoomEditTextId );
        trippleRoomEditText = findViewById( R.id.tripleRoomEditTextId );

        wifiBox = findViewById(R.id.wifiId);
        waterBox = findViewById(R.id.waterId);
        tableBox = findViewById(R.id.tableId);
        chairBox = findViewById(R.id.chairId);
        bedBox = findViewById(R.id.bedId);
        fanBox = findViewById(R.id.fanId);
        electricityBillBox = findViewById(R.id.electricityBillId);

        femaleBox = findViewById(R.id.femaleCheckBoxId);

        addButton = findViewById( R.id.addButtonId );
        displayButton = findViewById( R.id.displayButtonId );

        addButton.setOnClickListener( this );
        displayButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {

        String name = nameEditText.getText().toString();
        String seats = seatsEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String singleSeatFare = singleRoomEditText.getText().toString();
        String doubleSeatFare = doubleRoomEditText.getText().toString();
        String trippleSeatFare = trippleRoomEditText.getText().toString();

        String lattitude = lattEditText.getText().toString();
        String longitude = longEditText.getText().toString();

        boolean wifi = wifiBox.isChecked();
        boolean water = waterBox.isChecked();
        boolean table = tableBox.isChecked();
        boolean chair = chairBox.isChecked();
        boolean bed = bedBox.isChecked();
        boolean fan = fanBox.isChecked();
        boolean electricityBill = electricityBillBox.isChecked();
        boolean female = femaleBox.isChecked();

        if(v.getId() == R.id.addButtonId){

          isAllFieldChecked = CheckAllFields();
          if(isAllFieldChecked)
          {
              long rowId = databaseHelper.insertdata( name, seats, address, phone, wifi, water, table, chair, bed, fan, electricityBill, singleSeatFare, doubleSeatFare, trippleSeatFare, lattitude, longitude,female);

              if(rowId == -1)
              {
                  Toast.makeText(getApplicationContext(),"unsuccessfully",Toast.LENGTH_LONG).show();
              }
              else {
                  Toast.makeText(getApplicationContext(),"Row "+rowId+"is successfully inserted",Toast.LENGTH_LONG).show();
              }
          }

        }
        if(v.getId() == R.id.displayButtonId){

           Cursor cursor = databaseHelper.displayAllData();

           if(cursor.getCount() == 0)
           {
               //there is no data we will display message

               showData( "Error","No data found" );
               return;
           }
           StringBuffer stringBuffer = new StringBuffer(  );
           while(cursor.moveToNext())
           {
               stringBuffer.append( "ID :"+cursor.getString( 0 )+"\n" );
               stringBuffer.append( "Name :"+cursor.getString( 1 )+"\n" );
               stringBuffer.append( "Seats :"+cursor.getString( 2 )+"\n" );
               stringBuffer.append( "Address :"+cursor.getString( 3 )+" \n" );
               stringBuffer.append( "Phone :"+cursor.getString( 4 )+"\n\n" );
           }
           showData("ResultSet",stringBuffer.toString());
        }
    }
    public void showData(String title,String data)

    {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( title );
        builder.setMessage(data );
        builder.setCancelable( true );
        builder.show();

    }

    private boolean CheckAllFields()
    {
        if(nameEditText.length() == 0)
        {
            nameEditText.setError("Enter the Mess name");
            return false;
        }
        if(seatsEditText.length() == 0)
        {
            seatsEditText.setError("This field is required");
            return false;
        }
        if(addressEditText.length() == 0)
        {
            addressEditText.setError("This field is required");
            return false;
        }
        if(phoneEditText.length() == 0 )
        {
            phoneEditText.setError("This field is required");
            return false;
        }
        else if(phoneEditText.length() < 10 )
        {
            phoneEditText.setError("Invalid Phone Number");
            return false;
        }

        if(lattEditText.length() == 0)
        {
            lattEditText.setError("This field is required");
            return false;
        }
        else if(lattEditText.length() < 9)
        {
            lattEditText.setError("Enter a valid value");
            return false;
        }
        if(longEditText.length() == 0)
        {
            longEditText.setError("This field is required");
            return false;
        }
        else if(longEditText.length() < 9)
        {
            longEditText.setError("Enter a valid value");
            return false;
        }

        // after all validation return true.
        return true;
    }

}
