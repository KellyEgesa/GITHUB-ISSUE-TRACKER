package com.savannahInformatics.githubissuetracker.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.savannahInformatics.githubissuetracker.Models.GitHubRepoIssue;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserDetails;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;
import com.savannahInformatics.githubissuetracker.Network.GitHubClient;
import com.savannahInformatics.githubissuetracker.Network.GithubIssueTracker;
import com.savannahInformatics.githubissuetracker.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void searchGitHubUserName() {
        String username = mGitHubUserName.getText().toString().trim();

        if (!isUserNameValid(username)) {
            return;
        }

        progressDialog.show();
        GithubIssueTracker client = GitHubClient.urlRequest();
        Call<GitHubUserDetails> call = client.checkUser(username);
        call.enqueue(new Callback<GitHubUserDetails>() {
            @Override
            public void onResponse(Call<GitHubUserDetails> call, Response<GitHubUserDetails> response) {
                if (response.isSuccessful()) {
                    getRepos(username, response.body());
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(UserNameActivity.this, "User not Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GitHubUserDetails> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserNameActivity.this, "Something went wrong1", Toast.LENGTH_LONG).show();
            }
        });
    }


    private boolean isUserNameValid(String gitHubUserName) {
        if (gitHubUserName.equals("")) {
            mGitHubUserName.setError("Enter a valid UserName");
            return false;
        }
        return true;
    }

    private void loadingScreen() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Looking for User");
        progressDialog.setMessage("Searching for user with the given username");
        progressDialog.setCancelable(false);
    }

    private void getRepos(String username, GitHubUserDetails userDetails) {
        progressDialog.dismiss();
        progressDialog.show();
        loadingScreenRepos();

        GithubIssueTracker client = GitHubClient.urlRequest();

        Call call = client.getUserRepos(username);

        call.enqueue(new Callback<List<GitHubUserRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubUserRepo>> call, Response<List<GitHubUserRepo>> response) {
                if (response.isSuccessful()) {
                    Boolean gotRepos = false;
                    if (response.body().size() > 0) {
                        gotRepos = true;
                    }
                    Log.d("REsponseBody", String.valueOf(response.body().size()));
                    Intent intent = new Intent(UserNameActivity.this, RepositoryActivity.class);
                    List<GitHubUserRepo> userRepos = (ArrayList<GitHubUserRepo>) response.body();
                    intent.putExtra("githubUserDetails", Parcels.wrap(userDetails));
                    intent.putExtra("hasRepos", gotRepos);
                    intent.putExtra("githubUserRepo", Parcels.wrap(userRepos));
                    progressDialog.dismiss();
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(UserNameActivity.this, "Repositories not found", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<GitHubUserRepo>> call, Throwable t) {
                Toast.makeText(UserNameActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadingScreenRepos() {
        progressDialog.setTitle("Retrieving Repositories");
        progressDialog.setMessage("Retrieving Repositories associated the given username");
    }

}