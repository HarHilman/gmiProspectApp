package gmi.harith.gmiprospect;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioGroup; // Ensure you're importing the RadioGroup
import android.widget.RadioButton; // Ensure you're importing the RadioButton

public class CustomerServiceForm extends AppCompatActivity {

    private EditText contactNameEditText, contactPhoneEditText, otherComments;
    private RadioGroup genderRadioGroup, maritalStatusRadioGroup, raceRadioGroup;
    private Spinner departmentSpinner;
    private SeekBar qualitySeekBar, professionalSeekBar, responseSeekBar;
    private TextView qualityRatingTextView, professionalRatingTextView, responseRatingTextView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerserviceform); // Ensure you have the correct layout file

        // Initialize views
        contactNameEditText = findViewById(R.id.contactNameEditText);
        contactPhoneEditText = findViewById(R.id.contactPhoneEditText);
        otherComments = findViewById(R.id.otherComments);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        maritalStatusRadioGroup = findViewById(R.id.maritalStatusRadioGroup);
        raceRadioGroup = findViewById(R.id.raceRadioGroup);
        departmentSpinner = findViewById(R.id.departmentSpinner);
        qualitySeekBar = findViewById(R.id.qualitySeekBar);
        professionalSeekBar = findViewById(R.id.professionalSeekBar);
        responseSeekBar = findViewById(R.id.responseSeekBar);
        qualityRatingTextView = findViewById(R.id.qualityRatingTextView);
        professionalRatingTextView = findViewById(R.id.professionalRatingTextView);
        responseRatingTextView = findViewById(R.id.responseRatingTextView);
        submitButton = findViewById(R.id.submitButton);

        // Populate the Spinner with department options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(adapter);

        // Set up SeekBar listeners to update TextViews
        setupSeekBarListeners();

        // Submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });

        View returnButton = findViewById(R.id.returnbutton);

        // Set up the return button click listener
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action to take when the return button is clicked
                finish(); // This will close the current activity
                // Optionally, if you want to start a specific activity instead, you could do:
                // Intent intent = new Intent(CustomerServiceForm.this, PreviousActivity.class);
                // startActivity(intent);
            }
        });
        
    }

    private void setupSeekBarListeners() {
        qualitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qualityRatingTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        professionalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                professionalRatingTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        responseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                responseRatingTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void handleSubmit() {
        String name = contactNameEditText.getText().toString().trim();
        String phone = contactPhoneEditText.getText().toString().trim();
        String comments = otherComments.getText().toString().trim();
        String selectedDepartment = departmentSpinner.getSelectedItem().toString(); // Get selected department

        // Validate inputs
        if (name.isEmpty()) {
            contactNameEditText.setError(getString(R.string.name_required));
            return;
        }
        if (phone.isEmpty()) {
            contactPhoneEditText.setError(getString(R.string.phone_email_required));
            return;
        }

        // Collecting ratings and other data
        int qualityRating = qualitySeekBar.getProgress();
        int professionalRating = professionalSeekBar.getProgress();
        int responseRating = responseSeekBar.getProgress();

        // Getting selected gender, marital status, and race
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String selectedGender = selectedGenderButton != null ? selectedGenderButton.getText().toString() : "Not specified";

        int selectedMaritalStatusId = maritalStatusRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedMaritalStatusButton = findViewById(selectedMaritalStatusId);
        String selectedMaritalStatus = selectedMaritalStatusButton != null ? selectedMaritalStatusButton.getText().toString() : "Not specified";

        int selectedRaceId = raceRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRaceButton = findViewById(selectedRaceId);
        String selectedRace = selectedRaceButton != null ? selectedRaceButton.getText().toString() : "Not specified";

        // Here, you could add code to handle the collected data, e.g., saving to a database

        // Show success message
        Toast.makeText(CustomerServiceForm.this, getString(R.string.form_submitted), Toast.LENGTH_SHORT).show();

        // Optionally, clear the form
        clearForm();
    }

    private void clearForm() {
        contactNameEditText.setText("");
        contactPhoneEditText.setText("");
        otherComments.setText("");
        qualitySeekBar.setProgress(0);
        professionalSeekBar.setProgress(0);
        responseSeekBar.setProgress(0);
        qualityRatingTextView.setText("0");
        professionalRatingTextView.setText("0");
        responseRatingTextView.setText("0");
        departmentSpinner.setSelection(0); // Reset Spinner to first item

        // Clear the selected radio buttons
        genderRadioGroup.clearCheck();
        maritalStatusRadioGroup.clearCheck();
        raceRadioGroup.clearCheck();
    }
}
