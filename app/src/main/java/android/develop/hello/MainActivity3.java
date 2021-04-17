package android.develop.hello;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    private static final int REQUEST_CODE_MENU = 101;
    LinearLayout container;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(), "호출됨", Toast.LENGTH_LONG).show();

            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                Toast.makeText(getApplicationContext(), "호출받은 name: "+name , Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        container = findViewById(R.id.container);

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.layout2, container, true);
                CheckBox checkBox = container.findViewById(R.id.checkBox2);
                checkBox.setText("로딩완료");

            }
        });

        Button button1 = findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }


}