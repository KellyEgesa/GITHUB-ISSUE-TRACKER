package com.mamawaestate.githubissuetracker.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mamawaestate.githubissuetracker.R;

import butterknife.BindView;

public class RepositoryActivity extends AppCompatActivity {
    @BindView(R.id.textViewGithubUserName)
    TextView mTextViewGithubUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
    }
}