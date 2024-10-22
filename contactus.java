package gmi.harith.gmiprospect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;

public class contactus extends AppCompatActivity {

    private static final String CHANNEL_ID = "contact_us_notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus); // Link to contactus.xml

        Button enquirySubmit = findViewById(R.id.enquiry_submit);
        Button integritySubmit = findViewById(R.id.integrity_complaint_submit);
        Button feedbackSubmit = findViewById(R.id.feedback_submit);
        ImageView returnButton = findViewById(R.id.imageView2); // Your return button

        enquirySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEnquiry();
            }
        });

        integritySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitIntegrityComplaint();
            }
        });

        feedbackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });

        // Set an onClickListener for the return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to go to the menu.java activity
                Intent intent = new Intent(contactus.this, menu.class);
                startActivity(intent);
            }
        });

        createNotificationChannel(); // Create the notification channel
    }

    private void submitEnquiry() {
        // Get the input from the EditText fields
        EditText nameField = findViewById(R.id.enquiry_name);
        EditText messageField = findViewById(R.id.enquiry_message);

        String name = nameField.getText().toString();
        String message = messageField.getText().toString();

        // Show a notification
        showNotification("Enquiry Submitted", "Thank you, " + name + ", for your enquiry!");

        // Optionally, clear the input fields
        nameField.setText("");
        messageField.setText("");
    }

    private void submitIntegrityComplaint() {
        // Get the input from the EditText fields
        EditText nameField = findViewById(R.id.integrity_complaint_name);
        EditText messageField = findViewById(R.id.integrity_complaint_message);

        String name = nameField.getText().toString();
        String message = messageField.getText().toString();

        // Show a notification
        showNotification("Integrity Complaint Submitted", "Thank you, " + name + ", for your complaint!");

        // Optionally, clear the input fields
        nameField.setText("");
        messageField.setText("");
    }

    private void submitFeedback() {
        // Get the input from the EditText fields
        EditText feedbackField = findViewById(R.id.customer_service_feedback);
        String feedback = feedbackField.getText().toString();

        // Show a notification
        showNotification("Feedback Submitted", "Thank you for your feedback!");

        // Optionally, clear the input field
        feedbackField.setText("");
    }

    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); // Optional: Show a Toast message
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Contact Us Notifications";
            String description = "Channel for Contact Us notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
