package android.develop.hello;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity3 extends AppCompatActivity {
    private static final int REQUEST_CODE_MENU = 101;
    LinearLayout container;
    EditText nameInput;
    MainFragment mainFragment;
    MenuFragment menuFragment;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            ///Toast.makeText(getApplicationContext(), "호출됨", Toast.LENGTH_LONG).show();

            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                //Toast.makeText(getApplicationContext(), "호출받은 name: "+name , Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //container = findViewById(R.id.container);

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //inflater.inflate(R.layout.layout2, container, true);
                //CheckBox checkBox = container.findViewById(R.id.checkBox2);
                //checkBox.setText("로딩완료");

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

        //mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment01);


        nameInput = findViewById(R.id.editTextText);
    }

    public void onFragmentChanged(int index){
        if(index == 0){
            menuFragment = new MenuFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment01, menuFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        else  if(index == 1){
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment01, mainFragment).commit();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "onPause 호출", Toast.LENGTH_SHORT).show();
        saveState();;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "onResume 호출", Toast.LENGTH_SHORT).show();
        restoreState();
    }

    protected void restoreState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if((pref != null) && (pref.contains("name"))){
            String name = pref.getString("name","");
            nameInput.setText(name);
        }
    }

    protected void saveState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", nameInput.getText().toString());
        editor.commit();

    }

    protected void clearState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}