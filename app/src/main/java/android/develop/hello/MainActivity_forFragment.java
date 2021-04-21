package android.develop.hello;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity_forFragment extends AppCompatActivity
implements ListFragment.ImageSelectionCallback{

    ListFragment listFragment;
    ViewFragment viewFragment;

    int[] images = {R.drawable.bye, R.drawable.happy, R.drawable.sleep};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_fragment);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        viewFragment = (ViewFragment) manager.findFragmentById(R.id.viewFragment);
    }

    @Override
    public void onImageSelected(int position) {
        viewFragment.setImageView(images[position]);
    }
}