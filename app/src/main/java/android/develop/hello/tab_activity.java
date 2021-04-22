package android.develop.hello;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;


public class tab_activity extends AppCompatActivity {
    Toolbar toolbar;
    tab_fragment1 fragment1;
    tab_fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity);

        toolbar = findViewById(R.id.tab_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new tab_fragment1();
        fragment2 = new tab_fragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.tab_container, fragment1).commit();

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("통화"));
        tabLayout.addTab(tabLayout.newTab().setText("스팸"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position==0){
                    selected = fragment1;
                }else{
                    selected = fragment2;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.tab_container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*BottomNavigationView bottomNavigationView = findViewById(R.id.tab_toolbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = item.getItemId();
                return false;
            }
        });*/
    }
}