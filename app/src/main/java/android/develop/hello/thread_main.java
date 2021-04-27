package android.develop.hello;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class thread_main extends AppCompatActivity {
    int value =0;
    TextView textView;
    MainHandler handler;
    Handler handler2 = new Handler();
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_main);

        Button button = findViewById(R.id.button13);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        textView = findViewById(R.id.textView6);
        handler = new MainHandler();
        textView2 = findViewById(R.id.textView7);

        Button button1 = findViewById(R.id.button14);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    class BackgroundThread extends Thread{
        public void run(){
            for(int i=0;i<10;i++){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){}

                value+=1;
                Log.d("Thread", "value : "+value);

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);

                handler.sendMessage(message);
            }
        }
    }

    class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("value: "+value);
        }
    }

    private void request(){
        String title = "원격요청";
        String message = "요청하시겠습니까?";
        String titleButton = "예";
        String titleButtonNo = "아뇨";

        AlertDialog dialog = makeRequestDialog(title, message, titleButton, titleButtonNo);
        dialog.show();

        textView2.setText("대화상자 표시중 ...");
    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message, CharSequence titleButton, CharSequence titleButtonNo){
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView2.setText("5초 후..");
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText("요청완료됨");
                    }
                }, 5000);
            }
        });

        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return requestDialog.create();
    }
}