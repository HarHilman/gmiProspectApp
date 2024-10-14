package gmi.harith.gmiprospect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Handle Return Button Click (Navigate to AboutUs Activity)
        findViewById(R.id.ReturnButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start AboutUs activity when the return button is clicked
                Intent intent = new Intent(menu.this, aboutus.class);
                startActivity(intent);
            }
        });

        // Handle Course List Button Click
        findViewById(R.id.CourseList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start CourseListActivity when button is clicked
                Intent intent = new Intent(menu.this, courselist.class);
                startActivity(intent);
            }
        });

        // Handle Eligibility Checker Button Click
        findViewById(R.id.EligibilityChecker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start EligibilityCheckerActivity
                Intent intent = new Intent(menu.this, eligibilitychecker.class);
                startActivity(intent);
            }
        });

        // Handle Contact Us Button Click
        findViewById(R.id.ContactUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ContactUs activity
                Intent intent = new Intent(menu.this, contactus.class);
                startActivity(intent);
            }
        });

        // Other window inset handling code
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
