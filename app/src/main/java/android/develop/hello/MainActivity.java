package android.develop.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMainButton(View v){
        Toast.makeText(this, "pushed", Toast.LENGTH_LONG).show();
    }
    public void onClickNaverButton(View v){
        Intent naverIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(naverIntent);
    }
    public void onClickCallButton(View v){
        Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-1313"));
        startActivity(callIntent);
    }
}