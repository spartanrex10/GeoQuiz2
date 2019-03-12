package com.ctech.zachary.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private TextView mQuestionTextView;
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_alaska, true, false),
            new Question(R.string.question_america, false, false),
            new Question(R.string.question_antarctica, false, false),
            new Question(R.string.question_australia, true, false),
            new Question(R.string.question_europe, true, false),
            new Question(R.string.question_fox_village, true, false),
            new Question(R.string.question_japan, false, false),
            new Question(R.string.question_mississippi, true, false),
            new Question(R.string.question_chad, false, false),
            new Question(R.string.question_colonies, false, false),
            new Question(R.string.question_ctech, true, false),
    };

    private int mCurrentIndex = 0;
    int messageResourceId = 0;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) has been called!");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        //Sets question to the current index
        mQuestionTextView = findViewById(R.id.question_text_view);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mBackButton = findViewById(R.id.back_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1);
                if (mCurrentIndex < 0) {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Min: 0, Max: 11
                int random = new Random().nextInt(11);
                mCurrentIndex = random;
                updateQuestion();
            }
        });

        updateQuestion();
    }
    private void updateQuestion() {
        int questionResourceId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(questionResourceId);
    }

    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if (userPressedTrue == answerIsTrue) {
            messageResourceId = R.string.correct_toast;
            points = points + 1;
            mQuestionBank[mCurrentIndex]  answered = true;
        } else {
            messageResourceId = R.string.incorrect_toast;
        }

        Toast toast= Toast.makeText(MainActivity.this, messageResourceId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 150);
        toast.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart has been called!");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume has been called!");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause has been called!");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop has been called!");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy has been called!");
    }
}
