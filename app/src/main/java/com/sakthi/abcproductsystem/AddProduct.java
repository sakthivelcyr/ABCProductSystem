package com.sakthi.abcproductsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        DBHelper db = new DBHelper(this);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button registerBtn = (Button) findViewById(R.id.registerBtn);

        EditText product_name = (EditText) findViewById(R.id.product_name);
        EditText desc = (EditText) findViewById(R.id.desc);
        EditText quantity = (EditText) findViewById(R.id.quantity);
        EditText unit_price = (EditText) findViewById(R.id.unit_price);

        TextView product_nameErr = (TextView) findViewById(R.id.product_nameErr);
        TextView quantityErr = (TextView) findViewById(R.id.quantityErr);
        TextView typeErr = (TextView) findViewById(R.id.typeErr);
        TextView unit_priceErr = (TextView) findViewById(R.id.unit_priceErr);

        //get the spinner from the xml.
        Spinner typeDropdown = findViewById(R.id.type);
        //create a list of items for the spinner.
        String[] items = new String[]{"Select","Food", "Grocery"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        typeDropdown.setAdapter(adapter);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),""+typeDropdown.getSelectedItem(), Toast.LENGTH_LONG ).show();
                product_nameErr.setText("");
                quantityErr.setText("");
                typeErr.setText("");
                unit_priceErr.setText("");
                if(typeDropdown.getSelectedItem().toString().equals("Select") || product_name.getText().toString().isEmpty() || quantity.getText().toString().isEmpty() || unit_price.getText().toString().isEmpty()){
                    if(product_name.getText().toString().isEmpty())
                        product_nameErr.setText("Product Name is mandatory");
                    if(quantity.getText().toString().isEmpty())
                        quantityErr.setText("Quantity is mandatory");
                    if(unit_price.getText().toString().isEmpty())
                        unit_priceErr.setText("Unit Price mandatory");
                    if(typeDropdown.getSelectedItem().toString().equals("Select"))
                        typeErr.setText("Type is mandatory");
                }
                else{
                    Toast.makeText(getApplicationContext(),"started", Toast.LENGTH_LONG ).show();
                    String pName = product_name.getText().toString();
                    String type = typeDropdown.getSelectedItem().toString();
                    String description = desc.getText().toString();
                    int quantityI = Integer.parseInt(quantity.getText().toString());
                    int unitPrice = Integer.parseInt(unit_price.getText().toString());

                    Toast.makeText(getApplicationContext(),pName+type+description+quantityI+unitPrice, Toast.LENGTH_LONG ).show();

                    if(db.insertProduct(pName, type, description, quantityI, unitPrice))
                        Toast.makeText(getApplicationContext(),"done", Toast.LENGTH_LONG ).show();

//                    db.execSQL("INSERT INTO TBL_Product(ProductId, ProductName,Type, Description, Quantity, UnitPrice) VALUES(1,pName, type, description, quantityI, unitPrice);");
//                    Toast.makeText(getApplicationContext(),"stored", Toast.LENGTH_LONG ).show();
//                    Cursor resultSet = db.rawQuery("Select * from TBL_Product",null);
//                    resultSet.moveToFirst();
//                    String username = resultSet.getString(0);
//                    String password = resultSet.getString(1);
//                    Toast.makeText(getApplicationContext(),""+username+"  "+password, Toast.LENGTH_LONG ).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}