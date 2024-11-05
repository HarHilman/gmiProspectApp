package gmi.harith.gmiprospect; // Change this to your app's package name

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class IntegrityComplaintForm extends AppCompatActivity {

    private EditText complainantNameEditText;
    private EditText mobileNumberEditText;
    private EditText emailEditText;
    private EditText idNumberEditText;
    private EditText positionEditText;
    private EditText complaintDetailsEditText;
    private CheckBox harassmentCheckbox;
    private CheckBox nepotismCheckbox;
    private CheckBox fraudCheckbox;
    private CheckBox bribeCheckbox;
    private CheckBox acknowledgeCheckbox;
    private Button submitButton;
    private ImageView returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.integritycomplaintform); // Make sure this matches your XML filename

        // Initialize UI components
        complainantNameEditText = findViewById(R.id.complainantNameEditText);
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);
        idNumberEditText = findViewById(R.id.idNumberEditText);
        positionEditText = findViewById(R.id.positionEditText);
        complaintDetailsEditText = findViewById(R.id.complaintDetailsEditText);
        harassmentCheckbox = findViewById(R.id.harassmentCheckbox);
        nepotismCheckbox = findViewById(R.id.nepotismCheckbox);
        fraudCheckbox = findViewById(R.id.fraudCheckbox);
        bribeCheckbox = findViewById(R.id.bribeCheckbox);
        acknowledgeCheckbox = findViewById(R.id.acknowledgeCheckbox);
        submitButton = findViewById(R.id.submitButton);
        returnButton = findViewById(R.id.returnbutton);

        // Set up the return button to finish the activity when clicked
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Closes the current activity and returns to the previous one
            }
        });

        // Set up the submit button action
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitComplaint();
            }
        });
    }

    private void submitComplaint() {
        String complainantName = complainantNameEditText.getText().toString();
        String mobileNumber = mobileNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String idNumber = idNumberEditText.getText().toString();
        String position = positionEditText.getText().toString();
        String complaintDetails = complaintDetailsEditText.getText().toString();

        // Check if the acknowledgment checkbox is checked
        if (acknowledgeCheckbox.isChecked()) {
            // Handle the complaint submission logic here (e.g., save to database or send to server)

            // Check the state of each checkbox and handle accordingly
            if (harassmentCheckbox.isChecked()) {
                // Handle harassment case
                // Add logic to record or process this complaint
            }
            if (nepotismCheckbox.isChecked()) {
                // Handle nepotism case
                // Add logic to record or process this complaint
            }
            if (fraudCheckbox.isChecked()) {
                // Handle fraud case
                // Add logic to record or process this complaint
            }
            if (bribeCheckbox.isChecked()) {
                // Handle bribery case
                // Add logic to record or process this complaint
            }

            // Show a success message or navigate to another activity
            Toast.makeText(getApplicationContext(), "Complaint submitted successfully!", Toast.LENGTH_SHORT).show();
        } else {
            // Show a message that the user must acknowledge the complaint
            Toast.makeText(getApplicationContext(), "Please acknowledge that you are submitting this complaint.", Toast.LENGTH_SHORT).show();
        }
    }
}
