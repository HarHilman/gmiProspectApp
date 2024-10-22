package gmi.harith.gmiprospect;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.database.Cursor;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class eligibilitychecker extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST_CODE = 1;
    private static final int REQUEST_READ_PERMISSION = 100;

    private Spinner typeSpinner, additionalSpinner;
    private EditText malayInput, englishInput, mathInput, historyInput, islamicMoralInput;
    private Button checkButton, uploadButton, addSubjectButton;
    private LinearLayout dynamicSubjectContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eligibilitychecker); // Link to checker.xml

        // Initialize the UI elements
        initializeUI();

        // Request Read Permission if not already granted
        checkAndRequestPermission();

        // Upload button click listener
        uploadButton.setOnClickListener(v -> openFilePicker());

        // Eligibility check button listener
        checkButton.setOnClickListener(v -> {
            // Extract the entered grades from the input fields
            String malayGrade = malayInput.getText().toString().trim();
            String englishGrade = englishInput.getText().toString().trim();
            String mathGrade = mathInput.getText().toString().trim();
            String historyGrade = historyInput.getText().toString().trim();
            String islamicMoralGrade = islamicMoralInput.getText().toString().trim(); // Assuming Islamic/Moral for Science

            // Call the eligibility check method for the specified course
            if (isEligibleForElectronicsCourse(mathGrade, islamicMoralGrade, malayGrade, englishGrade, historyGrade)) {
                Toast.makeText(eligibilitychecker.this, "Eligibility check passed for Electronics-related courses!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(eligibilitychecker.this, "Eligibility check failed! Does not meet the requirements for Electronics-related courses.", Toast.LENGTH_SHORT).show();
            }
        });

        // Add subject button to add more dynamic fields
        addSubjectButton.setOnClickListener(v -> addDynamicSubjectField());
    }

    // Initialize UI elements
    private void initializeUI() {
        typeSpinner = findViewById(R.id.type_spinner);
        additionalSpinner = findViewById(R.id.additional_spinner);
        malayInput = findViewById(R.id.malay_input);
        englishInput = findViewById(R.id.english_input);
        mathInput = findViewById(R.id.mathematic_input);
        historyInput = findViewById(R.id.history_input);
        islamicMoralInput = findViewById(R.id.islamicMoral_input);
        checkButton = findViewById(R.id.check_button);
        uploadButton = findViewById(R.id.upload_button);
        addSubjectButton = findViewById(R.id.add_subject_button);
        dynamicSubjectContainer = findViewById(R.id.dynamic_subject_container);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_PERMISSION);
        }
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");  // Allow browsing for all file types
        intent.addCategory(Intent.CATEGORY_OPENABLE);  // Only show apps that can open files
        startActivityForResult(Intent.createChooser(intent, "Select a file"), PICK_FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri fileUri = data.getData();
            processSelectedFile(fileUri);
        }
    }

    private void processSelectedFile(Uri fileUri) {
        String fileName = getFileName(fileUri);
        Toast.makeText(this, "File selected: " + fileName, Toast.LENGTH_SHORT).show();

        try {
            InputImage image = InputImage.fromFilePath(this, fileUri);
            processImageWithOCR(image);
        } catch (Exception e) {
            Log.e("Error", "File processing error", e);
            Toast.makeText(this, "Error processing file. Make sure it's a valid image.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String fileName = "";
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        return fileName;
    }

    private void processImageWithOCR(InputImage image) {
        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        recognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    Log.d("OCR Result", "Extracted text: " + visionText.getText());
                    Toast.makeText(eligibilitychecker.this, "Extracted text: " + visionText.getText(), Toast.LENGTH_LONG).show();
                    extractTextAndFillFields(visionText);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(eligibilitychecker.this, "Failed to recognize text", Toast.LENGTH_SHORT).show();
                    Log.e("OCR", "Error: " + e.getMessage());
                });
    }

    private void extractTextAndFillFields(Text visionText) {
        String fullText = visionText.getText();

        malayInput.setText(extractGrade(fullText, "Malay"));
        englishInput.setText(extractGrade(fullText, "English"));
        mathInput.setText(extractGrade(fullText, "Math"));
        historyInput.setText(extractGrade(fullText, "History"));
        islamicMoralInput.setText(extractGrade(fullText, "Islamic"));

        Toast.makeText(this, "Form auto-filled from OCR result!", Toast.LENGTH_SHORT).show();
    }

    private String extractGrade(String text, String subject) {
        int index = text.indexOf(subject);
        if (index != -1) {
            String[] splitText = text.substring(index + subject.length()).trim().split("\\s+");
            for (String word : splitText) {
                if (word.matches("[A-Ca-c]|\\d+")) {
                    return word;
                }
            }
        }
        return "";
    }

    private void addDynamicSubjectField() {
        EditText newSubject = new EditText(this);
        newSubject.setHint("Additional Subject");
        dynamicSubjectContainer.addView(newSubject);
    }

    // Eligibility check for Electronics-related courses
    private boolean isEligibleForElectronicsCourse(String math, String science, String malay, String english, String history) {
        // Minimum credit (C or above) requirements for Mathematics and Science/Technical subject
        boolean hasMathCredit = isGradeSufficient(math, "C");
        boolean hasScienceCredit = isGradeSufficient(science, "C"); // Assuming Science includes Technical subjects

        // Check if student has at least 3 credits (Math + Science + any other passing grades)
        int creditCount = (hasMathCredit ? 1 : 0) + (hasScienceCredit ? 1 : 0);

        // For simplicity, we'll consider passing grades in other subjects as credits
        if (isGradePass(malay)) creditCount++;
        if (isGradePass(english)) creditCount++;
        if (isGradePass(history)) creditCount++;

        // Return true if student meets all conditions (at least 3 credits)
        return creditCount >= 3;
    }

    private boolean isGradeSufficient(String grade, String passingGrade) {
        return grade.equalsIgnoreCase(passingGrade) ||
                (passingGrade.equals("C") && grade.matches("[A]|[B]"));
    }

    private boolean isGradePass(String grade) {
        return grade.equalsIgnoreCase("A") ||
                grade.equalsIgnoreCase("B") ||
                grade.equalsIgnoreCase("C");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
