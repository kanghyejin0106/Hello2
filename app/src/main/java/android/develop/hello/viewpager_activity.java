package android.develop.hello;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class viewpager_activity extends AppCompatActivity {
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_viewpager_activity);

        viewPager  = findViewById(R.id.pager);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setOffscreenPageLimit(2);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentStateAdapter {
        int itemCount = 3;

        public MyPagerAdapter(FragmentManager fm, Lifecycle cycle) {
            super(fm, cycle);
        }

        @Override
        public int getItemCount() {
            return itemCount;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position) {
                case 0: {
                    return new tab_fragment1();
                }
                case 1: {
                    return new tab_fragment2();
                }
            }
            return new tab_fragment1();
        }

    }
/*
    class MyPagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        public void addItem(Fragment item){
            items.add(item);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }*/
}