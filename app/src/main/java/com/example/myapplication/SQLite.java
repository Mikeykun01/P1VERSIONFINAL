package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLite extends AppCompatActivity {
    //Iniciando Variables
    EditText et_Product, etQty, etPrice;
    Button btAdd;
    Spinner spinner;
    ListView listView1,listView2,listView3;

    DatabaseHelper databaseHelper;

    ArrayList arrayList1,arrayList2,arrayList3;
    ArrayAdapter arrayAdapter1,arrayAdapter2, arrayAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        et_Product=findViewById(R.id.et_product);
        etQty=findViewById(R.id.et_qty);
        etPrice=findViewById(R.id.et_price);
        btAdd=findViewById(R.id.bt_add);
        spinner=findViewById(R.id.spinner);
        listView1=findViewById(R.id.list_view1);
        listView2=findViewById(R.id.list_view2);
        listView3=findViewById(R.id.list_view3);

        databaseHelper=new DatabaseHelper(this);

        arrayList1=databaseHelper.getProduct();
        arrayList2=databaseHelper.getQTY();
        arrayList3=databaseHelper.getPrice();

        arrayAdapter1=new ArrayAdapter(SQLite.this, android.R.layout.simple_list_item_1
                ,arrayList1);
        listView1.setAdapter(arrayAdapter1);

        arrayAdapter2=new ArrayAdapter(SQLite.this, android.R.layout.simple_list_item_1
                ,arrayList2);
        listView2.setAdapter(arrayAdapter2);

        arrayAdapter3=new ArrayAdapter(SQLite.this, android.R.layout.simple_list_item_1
                ,arrayList3);
        listView3.setAdapter(arrayAdapter3);



        String[] spinnerList=new String[]{"Product","Qty","Price"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if(position == 0){
                    listView1.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.GONE);
                    listView3.setVisibility(View.GONE);
                }

                if(position == 1){
                    listView1.setVisibility(View.GONE);
                    listView2.setVisibility(View.VISIBLE);
                    listView3.setVisibility(View.GONE);
                }

                if(position == 2){
                    listView1.setVisibility(View.GONE);
                    listView2.setVisibility(View.GONE);
                    listView3.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product=et_Product.getText().toString();
                String qty=etQty.getText().toString();
                String price=etPrice.getText().toString();

                if (!product.isEmpty() && !qty.isEmpty() && !price.isEmpty()){
                    if (databaseHelper.insert(product,qty,price)){

                        Toast.makeText(getApplicationContext(), "Registro exitoso..."
                                , Toast.LENGTH_SHORT).show();

                        et_Product.setText("");
                        etQty.setText("");
                        etPrice.setText("");

                        arrayList1.clear();
                        arrayList2.clear();
                        arrayList3.clear();

                        arrayList1.addAll(databaseHelper.getProduct());
                        arrayList2.addAll(databaseHelper.getQTY());
                        arrayList3.addAll(databaseHelper.getPrice());

                        arrayAdapter1.notifyDataSetChanged();
                        arrayAdapter2.notifyDataSetChanged();
                        arrayAdapter3.notifyDataSetChanged();

                        listView1.invalidateViews();
                        listView1.refreshDrawableState();
                    }
                }
            }
        });


    }
}