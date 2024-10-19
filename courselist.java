package gmi.harith.gmiprospect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class courselist extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courselist);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Set up ViewPager2 with an adapter
        viewPager.setAdapter(new CoursePagerAdapter(this));

        // Connect TabLayout and ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Diploma Programme");
                    break;
                case 1:
                    tab.setText("Pre-University Programme");
                    break;
                case 2:
                    tab.setText("German Dual Vocational Training");
                    break;
                case 3:
                    tab.setText("GMi Industries 4.0");
                    break;
            }
        }).attach();
    }
}
