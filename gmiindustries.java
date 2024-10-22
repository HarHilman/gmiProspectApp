package gmi.harith.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class gmiindustries extends Fragment {

    public gmiindustries() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gmiindustries, container, false);

        // Find the buttons by their ID
        Button readMoreButton1 = view.findViewById(R.id.button_GMiI__readmore1);
        Button readMoreButton2 = view.findViewById(R.id.button_GMiI__readmore2);

        // Set onClickListener for the first "READ MORE" button
        readMoreButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "READ MORE" link for the first image
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/programmes/gmi-industrie4-0-initiatives/"));
                startActivity(browserIntent);
            }
        });

        // Set onClickListener for the second "READ MORE" button
        readMoreButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "READ MORE" link for the second image
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/programmes/gmi-industrie4-0-initiatives/"));
                startActivity(browserIntent);
            }
        });

        return view;
    }
}
