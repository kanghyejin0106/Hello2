package android.develop.hello;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.mainbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(80);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command","show");
                intent.putExtra("name", name);

                startService(intent);
            }
        });

        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        checkPermissions(permissions);
    }

    public void checkPermissions(String[] permissions){
        ArrayList<String> targetList = new ArrayList<String>();

        for(int i=0;i<permissions.length;i++){
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if(permissionCheck==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "권한있음", Toast.LENGTH_SHORT).show();
            }else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)){
                    Toast.makeText(this, "권한설명필요", Toast.LENGTH_SHORT).show();
                }else{
                    targetList.add(curPermission);
                }
            }
        }

        if(targetList.size()>0){
            String[] targets = new String[targetList.size()];
            targetList.toArray(targets);

            ActivityCompat.requestPermissions(this, targets, 101);
        }
        return;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 101:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "승인함", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "거부함", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    public void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Info");
        builder.setMessage("quit?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onClickMainButton(View v){
        //Toast.makeText(this, "pushed", Toast.LENGTH_LONG).show();
        /*try{
            Toast toastView = Toast.makeText(this, "위치가 바뀐 토스트!", Toast.LENGTH_LONG);
            int xOffset = Integer.parseInt(editText.getText().toString());
            toastView.setGravity(Gravity.TOP|Gravity.TOP, xOffset,xOffset);
            toastView.show();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }*/

    }

    public void onClickProgressBar(View v){

    }

    public void onClickMainButtonForToast(View v){
        /*LayoutInflater inflater = getLayoutInflater();
        View layoutChangeRecToast = inflater.from(this).inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView textThis = (TextView) layoutChangeRecToast.findViewById(R.id.text2);
        textThis.setText("모양바꾼 토스트");

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layoutChangeRecToast);
        toast.show();*/


    }

    public void onClickNaverButton(View v){
        Intent naverIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(naverIntent);
    }
    public void onClickCallButton(View v){
        Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-1323"));
        startActivity(callIntent);
    }
}

