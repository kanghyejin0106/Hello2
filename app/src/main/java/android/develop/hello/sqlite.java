package android.develop.hello;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class sqlite extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;
    SQLiteDatabase database;
    String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        editText = findViewById(R.id.editTextTextPersonName4);
        editText2 = findViewById(R.id.editTextTextPersonName5);
        textView = findViewById(R.id.textView13);

        Button button = findViewById(R.id.button21);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button1 = findViewById(R.id.button22);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableName = editText2.getText().toString();
                createTable(tableName);

                insertRecore();
            }
        });

        Button button2 = findViewById(R.id.button23);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeQuery();
            }
        });
    }

    public void executeQuery(){
        println("executeQuere called");

        Cursor cursor = database.rawQuery("select _id, name,age,mobile from emp", null);
        int recordCount=cursor.getCount();
        println("count: "+recordCount);
        for(int i=0;i<recordCount;i++){
            cursor.moveToNext();
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            println("record: "+i+" : "+id+", "+name+", "+age+", "+mobile);

        }
        cursor.close();
    }
    private void createDatabase(String name){
        println("database create");
        database = openOrCreateDatabase(name, MODE_PRIVATE, null);
        println("database created");
    }

    private void createTable(String name){
        println("createTable");
        if(database==null){
            println("db make first");
            return;
        }

        database.execSQL("create table if not exists "+name+"( _id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)");
        println("table created");
    }

    private void insertRecore(){
        println("insert called");
        if(database==null){
            println("db make first");
            return;
        }

        if(tableName==null){
            println("db make first");
            return;
        }

        database.execSQL("insert into "+tableName+"(name,age,mobile) values ('HJ', 20, '010-1111-1222')");
        println("inserted");
    }

    public void println(String data){
        textView.append(data+"\n");
    }
}