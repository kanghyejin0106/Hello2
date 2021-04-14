package android.develop.hello;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //editText = findViewById(R.id.textView);
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

