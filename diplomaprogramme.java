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
        Button button_mechatronics_readmore = view.findViewById(R.id.button_mechatronics_readmore);
        Button button_mechatronics_apply = view.findViewById(R.id.button_mechatronics_apply);

        // Set OnClickListener for Mechatronics "READ MORE"
        button_mechatronics_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/electrical-engineering/mechatronics/"));
            startActivity(intent);
        });

        // Set OnClickListener for Mechatronics "APPLY NOW"
        button_mechatronics_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // electronics buttons
        Button button_electronics_readmore = view.findViewById(R.id.button_electronics_readmore);
        Button button_electronics_apply = view.findViewById(R.id.button_electronics_apply);

        // Set OnClickListener for electronics "READ MORE"
        button_electronics_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/electrical-engineering/electric-information-technology/"));
            startActivity(intent);
        });

        // Set OnClickListener for electronics "APPLY NOW"
        button_electronics_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // energy buttons
        Button button_energy_apply = view.findViewById(R.id.button_energy_apply);
        Button button_energy_readmore = view.findViewById(R.id.button_energy_readmore);

        // Set OnClickListener for energy "READ MORE"
        button_energy_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/electrical-engineering/sustainable-energy-power-distribution/"));
            startActivity(intent);
        });

        // Set OnClickListener for energy "APPLY NOW"
        button_energy_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // instrumentation buttons
        Button button_instrumentation_apply = view.findViewById(R.id.button_instrumentation_apply);
        Button button_instrumentation_readmore = view.findViewById(R.id.button_instrumentation_readmore);

        // Set OnClickListener for instrumentation "READ MORE"
        button_instrumentation_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/electrical-engineering/process-instrumentation-control/"));
            startActivity(intent);
        });

        // Set OnClickListener for instrumentation "APPLY NOW"
        button_instrumentation_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // autotronics buttons
        Button button_autotronics_apply = view.findViewById(R.id.button_autotronics_apply);
        Button button_autotronics_readmore = view.findViewById(R.id.button_autotronics_readmore);

        // Set OnClickListener for autotronics "READ MORE"
        button_autotronics_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/electrical-engineering/autotronics-engineering-technology/"));
            startActivity(intent);
        });

        // Set OnClickListener for autotronics "APPLY NOW"
        button_autotronics_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // tooling buttons
        Button button_tooling_apply = view.findViewById(R.id.button_tooling_apply);
        Button button_tooling_readmore = view.findViewById(R.id.button_tooling_readmore);

        // Set OnClickListener for tooling "READ MORE"
        button_tooling_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/precision-tooling/"));
            startActivity(intent);
        });

        // Set OnClickListener for tooling "APPLY NOW"
        button_tooling_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // industrial buttons
        Button button_industrial_apply = view.findViewById(R.id.button_industrial_apply);
        Button button_industrial_readmore = view.findViewById(R.id.button_industrial_readmore);

        // Set OnClickListener for industrial "READ MORE"
        button_industrial_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/industrial-design-2/"));
            startActivity(intent);
        });

        // Set OnClickListener for industrial "APPLY NOW"
        button_industrial_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // quality buttons
        Button button_quality_apply = view.findViewById(R.id.button_quality_apply);
        Button button_quality_readmore = view.findViewById(R.id.button_quality_readmore);

        // Set OnClickListener for quality "READ MORE"
        button_quality_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/industrial-quality-engineering/"));
            startActivity(intent);
        });

        // Set OnClickListener for quality "APPLY NOW"
        button_quality_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // innovative buttons
        Button button_innovative_apply = view.findViewById(R.id.button_innovative_apply);
        Button button_innovative_readmore = view.findViewById(R.id.button_innovative_readmore);

        // Set OnClickListener for innovative "READ MORE"
        button_innovative_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/innovative-product-design/"));
            startActivity(intent);
        });

        // Set OnClickListener for innovative "APPLY NOW"
        button_innovative_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // precision buttons
        Button button_precision_apply = view.findViewById(R.id.button_precision_apply);
        Button button_precision_readmore = view.findViewById(R.id.button_precision_readmore);

        // Set OnClickListener for precision "READ MORE"
        button_precision_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/cnc-precision-technology/"));
            startActivity(intent);
        });

        // Set OnClickListener for precision "APPLY NOW"
        button_precision_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // maintenance buttons
        Button button_maintenance_apply = view.findViewById(R.id.button_maintenance_apply);
        Button button_maintenance_readmore = view.findViewById(R.id.button_maintenance_readmore);

        // Set OnClickListener for maintenance "READ MORE"
        button_maintenance_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/machine-tools-maintenance/"));
            startActivity(intent);
        });

        // Set OnClickListener for maintenance "APPLY NOW"
        button_maintenance_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // manufacturing buttons
        Button button_manufacturing_apply = view.findViewById(R.id.button_manufacturing_apply);
        Button button_manufacturing_readmore = view.findViewById(R.id.button_manufacturing_readmore);

        // Set OnClickListener for manufacturing "READ MORE"
        button_manufacturing_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/manufacturing-system/"));
            startActivity(intent);
        });

        // Set OnClickListener for manufacturing "APPLY NOW"
        button_manufacturing_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // software buttons
        Button button_software_apply = view.findViewById(R.id.button_software_apply);
        Button button_software_readmore = view.findViewById(R.id.button_software_readmore);

        // Set OnClickListener for software "READ MORE"
        button_software_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/computer-and-information/software-engineering/"));
            startActivity(intent);
        });

        // Set OnClickListener for software "APPLY NOW"
        button_software_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // cybersecurity buttons
        Button button_cybersecurity_apply = view.findViewById(R.id.button_cybersecurity_apply);
        Button button_cybersecurity_readmore = view.findViewById(R.id.button_cybersecurity_readmore);

        // Set OnClickListener for cybersecurity "READ MORE"
        button_cybersecurity_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/computer-and-information/cyber-security-technology/"));
            startActivity(intent);
        });

        // Set OnClickListener for cybersecurity "APPLY NOW"
        button_cybersecurity_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        // multimedia buttons
        Button button_multimedia_apply = view.findViewById(R.id.button_multimedia_apply);
        Button button_multimedia_readmore = view.findViewById(R.id.button_multimedia_readmore);

        // Set OnClickListener for multimedia "READ MORE"
        button_multimedia_readmore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/computer-and-information/creative-multimedia/"));
            startActivity(intent);
        });

        // Set OnClickListener for multimedia "APPLY NOW"
        button_multimedia_apply.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(intent);
        });

        return view;
    }
}