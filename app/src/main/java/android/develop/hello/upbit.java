package android.develop.hello;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.net.URLConnection;

public class upbit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upbit);
        int version = android.os.Build.VERSION.SDK_INT;
        Log.d("sdk version:",version+"");
        if(version > 8){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        }

        try {
            URL url = new URL("http://www.naver.com/test.php?str=raara1");

//여기 주소를 바꿔주면 된다. 데이터를 추가하려면 변수를 추가 해서 이어붙이면 된다. &기호로 이어붙인다. ex) name=les&num=20130610&phone=1111
            URLConnection conn = url.openConnection();

//url경로를 열어준다.
            conn.getInputStream();

//해당 url로 접속한다.
            Log.i("msg","go");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("msg","no");
        }
    }
}