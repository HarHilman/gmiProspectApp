package gmi.harith.gmiprospect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class eligibilitychecker extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST_CODE = 1;
    private static final int REQUEST_READ_PERMISSION = 100;

    private Spinner typeSpinner, additionalSpinner;
    private EditText malayInput, englishInput, mathInput, historyInput, islamicMoralInput;
    private Button checkButton, uploadButton, addSubjectButton;
    private LinearLayout dynamicSubjectContainer;
    private TextView eligibilityResult;

    private String[] additionalSubjects = {
            "Science", "Geografi (Geography)", "Bahasa Cina (Chinese Language)", "Bahasa Tamil (Tamil Language)",
            "Bahasa Iban (Iban Language)", "Add Mathematics", "Biology", "Chemistry", "Physics",
            "Economics", "Accountancy", "Business Studies", "Visual Arts", "Music", "Computer Science",
            "Engineering Drawing", "Textiles and Fashion", "Agricultural Science", "Technical Drawing",
            "Home Science", "Sports Science", "Mandarin", "Tamil", "Arabic"
    };
    private ArrayList<String> selectedAdditionalSubjects = new ArrayList<>();
    private Map<String, String> additionalSubjectGrades = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eligibilitychecker);

        initializeUI();
        checkAndRequestPermission();

        uploadButton.setOnClickListener(v -> openFilePicker());
        checkButton.setOnClickListener(v -> checkEligibility());
        addSubjectButton.setOnClickListener(v -> showSubjectSelectionDialog());

        // Apply window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeUI() {
        malayInput = findViewById(R.id.malay_input);
        englishInput = findViewById(R.id.english_input);
        mathInput = findViewById(R.id.mathematic_input);
        historyInput = findViewById(R.id.history_input);
        islamicMoralInput = findViewById(R.id.islamicMoral_input);
        checkButton = findViewById(R.id.check_button);
        uploadButton = findViewById(R.id.upload_button);
        addSubjectButton = findViewById(R.id.add_subject_button);
        dynamicSubjectContainer = findViewById(R.id.dynamic_subject_container);
        eligibilityResult = findViewById(R.id.eligibility_result);

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
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
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
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
            Bitmap processedBitmap = preprocessImage(bitmap);
            InputImage image = InputImage.fromBitmap(processedBitmap, 0);

            processImageWithOCR(image);
        } catch (Exception e) {
            Log.e("Error", "File processing error", e);
            eligibilityResult.setText("Error processing file. Make sure it's a valid image.");
        }
    }

    private Bitmap preprocessImage(Bitmap bitmap) {
        Bitmap grayscaleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(grayscaleBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        Matrix matrix = new Matrix();
        matrix.postRotate(detectRotationAngle(grayscaleBitmap));
        return Bitmap.createBitmap(grayscaleBitmap, 0, 0, grayscaleBitmap.getWidth(), grayscaleBitmap.getHeight(), matrix, true);
    }

    private float detectRotationAngle(Bitmap bitmap) {
        return 90.0f;
    }

    private void processImageWithOCR(InputImage image) {
        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        recognizer.process(image)
                .addOnSuccessListener(this::extractTextAndFillFields)
                .addOnFailureListener(e -> eligibilityResult.setText("Failed to recognize text"));
    }

    private void extractTextAndFillFields(Text visionText) {
        String fullText = visionText.getText();
        Log.d("OCR Result", fullText);

        malayInput.setText(extractGrade(fullText, "Bahasa Melayu"));
        englishInput.setText(extractGrade(fullText, "Bahasa Inggeris"));
        mathInput.setText(extractGrade(fullText, "Matematik"));
        historyInput.setText(extractGrade(fullText, "Sejarah"));
        islamicMoralInput.setText(extractGrade(fullText, "Pendidikan Islam"));

        if (isFormIncomplete()) {
            showIncompleteFormDialog();
        } else {
            eligibilityResult.setText("Form auto-filled successfully!");
        }
    }

    private String extractGrade(String text, String subject) {
        int index = text.indexOf(subject);
        if (index != -1) {
            String[] splitText = text.substring(index + subject.length()).trim().split("\\s+");
            for (String word : splitText) {
                if (word.matches("[A-C][+-]?|A\\+|B\\+|C\\+")) {
                    return word;
                }
            }
        }
        return "";
    }

    private boolean isFormIncomplete() {
        return malayInput.getText().toString().isEmpty() ||
                englishInput.getText().toString().isEmpty() ||
                mathInput.getText().toString().isEmpty();
    }

    private void showIncompleteFormDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Incomplete Information")
                .setMessage("Some grades were not recognized. Would you like to manually fill in the missing fields or upload a clearer file?")
                .setPositiveButton("Manual Entry", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("Re-upload", (dialog, which) -> openFilePicker())
                .show();
    }

    private void showSubjectSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Additional Subject");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, additionalSubjects);
        builder.setAdapter(adapter, (dialog, which) -> {
            String selectedSubject = additionalSubjects[which];
            if (!selectedAdditionalSubjects.contains(selectedSubject)) {
                selectedAdditionalSubjects.add(selectedSubject);
                addDynamicSubjectField(selectedSubject);
            }
        });

        builder.show();
    }

    private void addDynamicSubjectField(String subject) {
        LinearLayout subjectLayout = new LinearLayout(this);
        subjectLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView subjectLabel = new TextView(this);
        subjectLabel.setText(subject);
        subjectLabel.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        EditText gradeInput = new EditText(this);
        gradeInput.setHint(subject + " Grade");
        gradeInput.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        gradeInput.setTag(subject);
        gradeInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                additionalSubjectGrades.put(subject, gradeInput.getText().toString());
            }
        });

        subjectLayout.addView(subjectLabel);
        subjectLayout.addView(gradeInput);

        dynamicSubjectContainer.addView(subjectLayout);
    }

    private void checkEligibility() {
        StringBuilder resultText = new StringBuilder("Eligible for the following courses:\n\n");

        String englishGrade = englishInput.getText().toString();
        String mathGrade = mathInput.getText().toString();

        if (isEligibleForCourse(mathGrade, getScienceGrade(), englishGrade, "Sustainable Energy and Power Distribution")) {
            resultText.append("- Sustainable Energy and Power Distribution\n");
        }
        if (isEligibleForCourse(mathGrade, getScienceGrade(), englishGrade, "Process Instrumentation and Control")) {
            resultText.append("- Process Instrumentation and Control\n");
        }
        if (isEligibleForCourse(mathGrade, getScienceGrade(), englishGrade, "Autotronics Engineering Technology")) {
            resultText.append("- Autotronics Engineering Technology\n");
        }
        if (isEligibleForCourse(mathGrade, getScienceGrade(), englishGrade, "Mechatronics (Mechanical Engineering)")) {
            resultText.append("- Mechatronics (Mechanical Engineering)\n");
        }
        if (isEligibleForCourse(mathGrade, getScienceGrade(), englishGrade, "Precision Tooling Engineering Technology")) {
            resultText.append("- Precision Tooling Engineering Technology\n");
        }
        if (isEligibleForCourse(mathGrade, englishGrade, "Software Engineering")) {
            resultText.append("- Software Engineering\n");
        }
        if (isEligibleForCourse(mathGrade, getScienceGrade(), englishGrade, "Cyber Security Technology")) {
            resultText.append("- Cyber Security Technology\n");
        }
        if (isEligibleForCourse(mathGrade, englishGrade, "Creative Multimedia")) {
            resultText.append("- Creative Multimedia\n");
        }
        if (isEligibleForPreUniversity(mathGrade, englishGrade, "German A Levels Preparatory Programme (GAPP)")) {
            resultText.append("- German A Levels Preparatory Programme (GAPP)\n");
        }
        if (isEligibleForPreUniversity(mathGrade, englishGrade, "GMI-UTP Foundation Programme (GUFP)")) {
            resultText.append("- GMI-UTP Foundation Programme (GUFP)\n");
        }

        eligibilityResult.setText(resultText.length() > 36 ? resultText.toString() : "Not eligible for any course.");
    }

    private boolean isEligibleForCourse(String math, String science, String english, String courseName) {
        return isCredit(math) && isCredit(science) && isCredit(english);
    }

    private boolean isEligibleForCourse(String math, String english, String courseName) {
        return isCredit(math) && isCredit(english) && hasEligibleAdditionalSubject();
    }

    private boolean isEligibleForPreUniversity(String math, String english, String program) {
        String addMathGrade = additionalSubjectGrades.get("Add Mathematics");
        String physicsGrade = additionalSubjectGrades.get("Physics");
        String chemistryGrade = additionalSubjectGrades.get("Chemistry");

        return isCredit(math) && isCredit(english) && isCredit(addMathGrade)
                && isCredit(physicsGrade) && isCredit(chemistryGrade);
    }

    private boolean hasEligibleAdditionalSubject() {
        for (String subject : selectedAdditionalSubjects) {
            String grade = additionalSubjectGrades.get(subject);
            if (grade != null && isCredit(grade)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCredit(String grade) {
        return grade != null && (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C"));
    }

    private String getScienceGrade() {
        return additionalSubjectGrades.getOrDefault("Science", historyInput.getText().toString());
    }
}
