package gmi.harith.gmiprospect;

import android.content.Intent; // Import Intent class
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus); // Ensure this layout file exists

        // Back Button Click - Finish Current Activity
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close the current activity
            }
        });

        // Customer Service Button Click
        Button customerServiceButton = findViewById(R.id.customer_service_btn);
        customerServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact.this, CustomerServiceForm.class);
                startActivity(intent);
            }
        });

        // Integrity Complaint Button Click
        Button integrityComplaintButton = findViewById(R.id.integrity_complaint_btn);
        integrityComplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact.this, IntegrityComplaintForm.class); // Replace with your activity
                startActivity(intent);
            }
        });

        // Enquiry Form Button Click
        Button enquiryFormButton = findViewById(R.id.enquiry_form_btn);
        enquiryFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact.this, EnquiryForm.class); // Replace with your activity
                startActivity(intent);
            }
        });
    }
}
