package gmi.harith.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class preuniversityprogramme extends Fragment {

    public preuniversityprogramme() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.preuniversityprogramme, container, false);

        // Find the buttons by their ID
        Button gappReadMoreButton = view.findViewById(R.id.button_GAPP_readmore);
        Button gappApplyNowButton = view.findViewById(R.id.button_GAPP_apply);

        Button gufpReadMoreButton = view.findViewById(R.id.button_GUFP_readmore);
        Button gufpApplyNowButton = view.findViewById(R.id.button_GUFP_apply);

        // Set onClickListener for GAPP Read More
        gappReadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "READ MORE" link
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/pre-university-programme/german-a-level-preparatory-programme-gapp/"));
                startActivity(browserIntent);
            }
        });

        // Set onClickListener for GAPP Apply Now
        gappApplyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "APPLY NOW" link
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sis.sqayy.com/oa/gmi"));
                startActivity(browserIntent);
            }
        });

        // Set onClickListener for GUFP Read More
        gufpReadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "READ MORE" link
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/gmi-utp-foundation-studies-programme/"));
                startActivity(browserIntent);
            }
        });

        // Set onClickListener for GUFP Apply Now
        gufpApplyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a browser with the "APPLY NOW" link
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sis.sqayy.com/oa/gmi"));
                startActivity(browserIntent);
            }
        });

        return view;
    }
}
