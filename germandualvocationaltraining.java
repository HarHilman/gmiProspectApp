package gmi.harith.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class germandualvocationaltraining extends Fragment {

    public germandualvocationaltraining() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.germandualvocationaltraining, container, false);

        // Find the buttons by their ID
        Button mechatronicsReadMoreButton = view.findViewById(R.id.Mechatronics_button);
        Button lomReadMoreButton = view.findViewById(R.id.LOM_button);
        Button imReadMoreButton = view.findViewById(R.id.IM_button);

        // Set onClickListener for Mechatronics Read More
        mechatronicsReadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "READ MORE" link for Mechatronics
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/german-dual-vocational-training-gdvt/mechatronics-electronics/"));
                startActivity(browserIntent);
            }
        });

        // Set onClickListener for Logistics Operation Management (LOM) Read More
        lomReadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "READ MORE" link for Logistics Operation Management
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/german-dual-vocational-training-gdvt/logistic-operation-management/"));
                startActivity(browserIntent);
            }
        });

        // Set onClickListener for Industrial Management (IM) Read More
        imReadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "READ MORE" link for Industrial Management
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/german-dual-vocational-training-gdvt/industrial-management/"));
                startActivity(browserIntent);
            }
        });

        return view;
    }
}
