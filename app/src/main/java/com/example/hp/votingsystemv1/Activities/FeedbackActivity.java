package com.example.hp.votingsystemv1.Activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.votingsystemv1.R;
import com.example.hp.votingsystemv1.databinding.ActivityFeedbackBinding;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFeedbackBinding binding;

    private String feedback;
    private EditText feedbackComment;
    private Button feedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feedback);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Feedback");

        binding.happyFeedback.setOnClickListener(this);
        binding.neutralFeedback.setOnClickListener(this);
        binding.sadFeedback.setOnClickListener(this);
        binding.sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedback != null && !feedback.isEmpty()) {
                    String toastMsg;
                    if (feedbackButton.getId() == R.id.sad_feedback && feedbackComment.getText().toString().isEmpty()) {
                        toastMsg = "Please help us make it better by writing a reason";
                        Toast.makeText(FeedbackActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
                    } else {
                        toastMsg = "Thank you for your feedback!";
                        Toast.makeText(FeedbackActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (feedbackComment != null) {
            feedbackComment.setVisibility(View.GONE);
            feedbackComment.setText("");
        }
        toggleDrawables();

        if (feedbackButton != null && v.getId() == feedbackButton.getId()) {
            feedbackButton = null;
            return;
        }

        switch (v.getId()) {
            case R.id.happy_feedback:
                feedbackComment = binding.happyFeedbackComment;
                ((Button) v).setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_down,
                        0,
                        R.drawable.ic_happy_selected,
                        0);
                break;
            case R.id.neutral_feedback:
                feedbackComment = binding.neutralFeedbackComment;
                ((Button) v).setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_down,
                        0,
                        R.drawable.ic_neutral_selected,
                        0);
                break;

            case R.id.sad_feedback:
                feedbackComment = binding.sadFeedbackComment;
                ((Button) v).setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_down,
                        0,
                        R.drawable.ic_sad_selected,
                        0);
                break;
        }
        feedbackButton = (Button) v;
        feedback = ((Button) v).getText().toString();
        feedbackComment.setVisibility(View.VISIBLE);
    }

    private void toggleDrawables() {
        if (feedbackButton == null) return;

        if (feedbackButton.getId() == R.id.sad_feedback) {
            feedbackButton.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_right,
                    0,
                    R.drawable.sad,
                    0);
        } else if (feedbackButton.getId() == R.id.neutral_feedback) {
            feedbackButton.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_right,
                    0,
                    R.drawable.emoticon,
                    0);
        } else if (feedbackButton.getId() == R.id.happy_feedback) {
            feedbackButton.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_right,
                    0,
                    R.drawable.happy,
                    0);
        }
    }
}