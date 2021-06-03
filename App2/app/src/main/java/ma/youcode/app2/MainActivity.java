package ma.youcode.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DB_Sqlit db = new DB_Sqlit(this);

    EditText name, email,id;
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id=findViewById(R.id.idUpdate);
        name = findViewById(R.id.editText_name);
        email = findViewById(R.id.editText_email);
        list =  findViewById(R.id.listView);
        showData();
    }



    public void showData(){
        ArrayList<String> listData = db.getAllUsers();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        list.setAdapter(arrayAdapter);
    }





    public void delete(View view) {

        String Id = id.getText().toString();
        Integer result = db.deleteData(Id);

        if(Id.equals("")){
            Toast.makeText(MainActivity.this, "choose an ID", Toast.LENGTH_SHORT).show();
        }else{
            if (result > 0){
                Toast.makeText(MainActivity.this, "DELETE", Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
            }
        }

        showData();
    }

    public void update(View view) {
        String Id = id.getText().toString();
        String Name = name.getText().toString();
        String Email = email.getText().toString();

//        if(Name.equals("") || Email.equals("") || Phone.equals("") || Id.equals("")){
//            Toast.makeText(datshAdmin.this, "Form not complite", Toast.LENGTH_SHORT).show();
//        }else{
            Boolean result = db.update(Id, Name, Email);
            if (result==true){
                Toast.makeText(MainActivity.this, "UPDATE", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                id.setText("");
                showData();
            }else {
                Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
            }
        }



    public void addbtn(View view) {
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        showData();

        Boolean result = db.insertData(Name, Email);
        if (result == true){
            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            showData();
        }
        else {
            Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();

        }
    }
}