package android.develop.hello;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class socket extends AppCompatActivity {
    EditText editText;
    TextView textView;
    TextView textView2;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView9);
        textView2 = findViewById(R.id.textView10);

        Button button = findViewById(R.id.button17);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        Button button1 = findViewById(R.id.button18);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }

    public void printClientLog(final String data){
        Log.d("MainActivity", data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data+"\n");
            }
        });
    }

    public void printServerLog(final String data){
        Log.d("MainActivity", data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView2.append(data+"\n");
            }
        });
    }

    public void send(String data){
        try{
            int portNumber = 5001;
            Socket sock = new Socket("localhost", portNumber);
            printClientLog("소켓연결함");
            ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();
            printClientLog("데이터전송");

            ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
            printClientLog("서버로부터 받음"+inputStream.readObject());
            sock.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void startServer(){
        try{
            int portNumber = 5001;

            ServerSocket server = new ServerSocket(portNumber);
            printServerLog("서버시작함 "+portNumber);
            while(true){
                Socket sock = server.accept();
                InetAddress clientHost = sock.getLocalAddress();
                int clientPort = sock.getPort();
                printServerLog("클라이언트 연결함 "+clientHost+" : "+clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
                Object obj = inputStream.readObject();
                printServerLog("데이터받음 : "+obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
                outputStream.writeObject(obj + " from server");
                outputStream.flush();
                printServerLog("데이터보냄");

                sock.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}