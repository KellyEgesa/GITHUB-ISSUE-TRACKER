package com.savannahInformatics.githubissuetracker.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.savannahInformatics.githubissuetracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserNameActivity extends AppCompatActivity {
    @BindView(R.id.proceedButton)
    Button mProceedButton;
    @BindView(R.id.editTextGithubUserName)
    EditText mGitHubUserName;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);

        loadingScreen();

        mProceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGitHubUserName();
            }
        });
    }

    private void searchGitHubUserName(){
        String username = mGitHubUserName.getText().toString().trim();

        if(!isUserNameValid(username)){
            return;
        }

        Intent intent = new Intent(UserNameActivity.this, RepositoryActivity.class);
        intent.putExtra("githubUserName", username);
        startActivity(intent);
    }


    private boolean isUserNameValid(String gitHubUserName) {
        if (gitHubUserName.equals("")) {
            mGitHubUserName.setError("Enter a valid UserName");
            return false;
        }
        return true;
    }

    private void loadingScreen(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Searching");
        progressDialog.setMessage("Searching for user with the given username");
        progressDialog.setCancelable(false);
    }



}