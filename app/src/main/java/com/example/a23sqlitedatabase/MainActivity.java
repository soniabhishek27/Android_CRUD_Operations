package com.example.a23sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText editTextId, editTextName, editemail, editCC;
    Button buttonAdd, buttonView, buttonUpdate, buttonDelete, buttonViewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);
        //setting edit text
        editTextId = findViewById(R.id.editText_id);
        editTextName = findViewById(R.id.editText_name);
        editemail = findViewById(R.id.editText_email);
        editemail = findViewById(R.id.editText_email);
        // setting button
        buttonAdd = findViewById(R.id.button_add);
        buttonView = findViewById(R.id.button_view);
        buttonDelete = findViewById(R.id.button_delete);
        buttonUpdate = findViewById(R.id.button_update);
        buttonViewAll = findViewById(R.id.button_viewAll);

        //  showmessage("test","Testing done");

        AddData();
        getData();
        ViewAll();
        UpdateData();
    }

    public void AddData() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydb.insetData(editTextName.getText().toString(), editemail.getText().toString(), editCC.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    void getData() {
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();

                if (id.equals(String.valueOf(""))) {
                    editTextId.setError("Enter Id");
                    return;
                }

                Cursor cursor = mydb.getData(id);
                String data = null;
                if (cursor.moveToNext()) {
                    data = "ID: " + cursor.getString(0) + "\n" +
                            "Name: " + cursor.getString(1) + "\n" +
                            "Email: " + cursor.getString(2) + "\n" +
                            "CourseCount: " + cursor.getString(3) + "\n";
                }
                showmessage("Data", data);

            }
        });
    }

    public void ViewAll() {
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.getAllData();
                //to check what the cursor is returning
                if (cursor.getCount() == 0) {
                    showmessage("Error", "Nothing found in the DataBase");
                    return;
                }
                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext()) {
                    buffer.append("ID:" + cursor.getString(0) + "\n");
                    buffer.append("NAME:" + cursor.getString(1) + "\n");
                    buffer.append("EMAIL" + cursor.getString(2) + "\n");
                    buffer.append("COURSE" + cursor.getString(3) + "\n");

                }
                showmessage("All data", buffer.toString());
            }
        });
    }

    public void UpdateData() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdate = mydb.updateData(editTextId.getText().toString(),
                        editTextName.getText().toString(),
                        editemail.getText().toString(),
                        editCC.getText().toString());

                if (isUpdate == true) {
                    Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "OOps", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    public void DeleteData()
    {
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editTextId.getText().toString();
                if(id.equals(String.valueOf("")));
                {
                    editTextId.setError("Please Enter a Id First");
                }
                Integer isDeleted=mydb.DeletData(editTextId.getText().toString());
                if(isDeleted > 0)
                {
                    Toast.makeText(MainActivity.this,"Deleted Sucessfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"OOPS",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }







    public void showmessage(String title,String message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }

}
