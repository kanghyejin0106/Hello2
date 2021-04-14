package android.develop.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FrameLayout extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);

    }

    public void onButton1Clicked(View v){
        changeImage();
    }

    private void changeImage(){
        if(imageIndex==0){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageIndex=1;
        }else{
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageIndex=0;
        }
    }

}