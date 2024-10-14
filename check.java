package gmi.harith.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class check extends AppCompatActivity {

    private Spinner typeSpinner, additionalSpinner;
    private EditText malayInput, englishInput, mathInput, historyInput, islamicMoralInput;
    private Button checkButton, uploadButton, addSubjectButton;
    private LinearLayout dynamicSubjectContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checker); // Link to checker.xml

        // Initialize the UI elements
        typeSpinner = findViewById(R.id.type_spinner);
        additionalSpinner = findViewById(R.id.additional_spinner); // Additional subject Spinner
        malayInput = findViewById(R.id.malay_input); // Malay input
        englishInput = findViewById(R.id.english_input); // English input
        mathInput = findViewById(R.id.mathematic_input); // Math input
        historyInput = findViewById(R.id.history_input); // History input
        islamicMoralInput = findViewById(R.id.islamicMoral_input); // Islamic/Moral input
        checkButton = findViewById(R.id.check_button);
        uploadButton = findViewById(R.id.upload_button);
        addSubjectButton = findViewById(R.id.add_subject_button);
        dynamicSubjectContainer = findViewById(R.id.dynamic_subject_container); // Container for dynamic EditText fields
        ImageButton backButton = findViewById(R.id.back_button);

        // Set up back button functionality
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Navigate to the previous screen
            }
        });

        // Add dynamic subject field when the "Add Subject" button is clicked
        addSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedSubject = additionalSpinner.getSelectedItem().toString();

                // Create a new EditText dynamically
                EditText newSubjectInput = new EditText(check.this);
                newSubjectInput.setHint(selectedSubject + " Grade");
                newSubjectInput.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                // Add the new EditText to the container
                dynamicSubjectContainer.addView(newSubjectInput);
            }
        });

        // Set up the Check Eligibility button logic
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = typeSpinner.getSelectedItem().toString();
                String malayGrade = malayInput.getText().toString();
                String englishGrade = englishInput.getText().toString();
                String mathGrade = mathInput.getText().toString();
                String historyGrade = historyInput.getText().toString();
                String islamicMoralGrade = islamicMoralInput.getText().toString();

                // Ensure all static fields are filled
                if (malayGrade.isEmpty() || englishGrade.isEmpty() || mathGrade.isEmpty() || historyGrade.isEmpty() || islamicMoralGrade.isEmpty()) {
                    Toast.makeText(check.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ensure static fields have valid grades
                if (!isValidGrade(malayGrade) || !isValidGrade(englishGrade) || !isValidGrade(mathGrade) || !isValidGrade(historyGrade) || !isValidGrade(islamicMoralGrade)) {
                    Toast.makeText(check.this, "Please enter valid grades (A, B, or C)", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Collect all dynamically added subjects' grades
                int childCount = dynamicSubjectContainer.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = dynamicSubjectContainer.getChildAt(i);
                    if (child instanceof EditText) {
                        String grade = ((EditText) child).getText().toString();
                        if (grade.isEmpty()) {
                            Toast.makeText(check.this, "Please fill all added subjects", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!isValidGrade(grade)) {
                            Toast.makeText(check.this, "Please enter valid grades (A, B, or C)", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                // Eligibility logic here
                // Call the eligibility checking logic and proceed accordingly
                Toast.makeText(check.this, "Eligibility check passed!", Toast.LENGTH_SHORT).show();
                // Redirect if eligible:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
                startActivity(intent);
            }
        });

        // Set up the file upload button functionality
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open file picker for educational result file
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedFileUri = data.getData();
            if (selectedFileUri != null) {
                Toast.makeText(this, "File selected: " + selectedFileUri.getPath(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isValidGrade(String grade) {
        return grade.matches("[ABCabc]");
    }
}
