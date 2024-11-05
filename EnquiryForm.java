package gmi.harith.gmiprospect; // Change to your actual package name

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EnquiryForm extends AppCompatActivity {

    // Declare UI components
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText contactNumberEditText;
    private EditText enquiryEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enquiryform); // Ensure this matches your XML file name

        // Initialize UI components
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        contactNumberEditText = findViewById(R.id.contactNumberEditText);
        enquiryEditText = findViewById(R.id.enquiryEditText);
        submitButton = findViewById(R.id.submitButton);

        // Set up a click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitEnquiry();
            }
        });
    }

    private void submitEnquiry() {
        // Retrieve input values
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String contactNumber = contactNumberEditText.getText().toString().trim();
        String enquiry = enquiryEditText.getText().toString().trim();

        // Basic validation
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contactNumber.isEmpty()) {
            Toast.makeText(this, "Please enter your contact number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (enquiry.isEmpty()) {
            Toast.makeText(this, "Please enter your enquiry", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Handle submission (e.g., send to a server, save to a database, etc.)
        // For demonstration purposes, we will just show a success message
        Toast.makeText(this, "Enquiry submitted successfully!", Toast.LENGTH_LONG).show();

        // Optionally, you can clear the fields after submission
        clearFields();
    }

    private void clearFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        contactNumberEditText.setText("");
        enquiryEditText.setText("");
    }
}
