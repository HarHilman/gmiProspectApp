package gmi.harith.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class diplomaprogramme extends Fragment {

    public diplomaprogramme() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.diplomaprogramme, container, false);

        // Mechatronics buttons
        Button btnMechatronicsReadMore = view.findViewById(R.id.button_mechatronics_readmore);
        Button btnMechatronicsApply = view.findViewById(R.id.button_mechatronics_apply);

        // Electronics & IT buttons
        Button btnElectronicsReadMore = view.findViewById(R.id.button_electronics_readmore);
        Button btnElectronicsApply = view.findViewById(R.id.button_electronics_apply);

        // Set OnClickListener for Mechatronics "READ MORE"
        btnMechatronicsReadMore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/electrical-engineering/mechatronics/"));
            startActivity(intent);
        });

        // Set OnClickListener for Mechatronics "APPLY NOW"
        btnMechatronicsApply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // Set OnClickListener for Electronics & IT "READ MORE"
        btnElectronicsReadMore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/electrical-engineering/mechatronics/"));
            startActivity(intent);
        });

        // Set OnClickListener for Electronics & IT "APPLY NOW"
        btnElectronicsApply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        return view;
    }
}
