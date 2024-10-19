package gmi.harith.gmiprospect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CoursePagerAdapter extends FragmentStateAdapter {

    public CoursePagerAdapter(@NonNull AppCompatActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new diplomaprogramme();
            case 1:
                return new preuniversityprogramme();
            case 2:
                return new germandualvocationaltraining();
            case 3:
                return new gmiindustries();
            default:
                return new diplomaprogramme();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Number of tabs
    }
}
