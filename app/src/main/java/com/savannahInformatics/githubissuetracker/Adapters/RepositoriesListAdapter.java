package com.savannahInformatics.githubissuetracker.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savannahInformatics.githubissuetracker.Models.GitHubRepoIssue;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;
import com.savannahInformatics.githubissuetracker.Network.GitHubClient;
import com.savannahInformatics.githubissuetracker.Network.GithubIssueTracker;
import com.savannahInformatics.githubissuetracker.R;
import com.savannahInformatics.githubissuetracker.UserInterface.MainActivity;
import com.savannahInformatics.githubissuetracker.UserInterface.RepositoryActivity;
import com.savannahInformatics.githubissuetracker.UserInterface.UserNameActivity;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class RepositoriesListAdapter extends RecyclerView.Adapter<RepositoriesListAdapter.RepositoriesViewHolder> {
    private List<GitHubUserRepo> mRepositories;

    public RepositoriesListAdapter(List<GitHubUserRepo> repos) {
        mRepositories = repos;
    }


    @NonNull
    @Override
    public RepositoriesListAdapter.RepositoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos, parent, false);
        RepositoriesViewHolder viewHolder = new RepositoriesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesListAdapter.RepositoriesViewHolder holder, int position) {
        holder.bindRepositories(mRepositories.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }

    public class RepositoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewRepositoryName)
        TextView mTextViewRepositoryName;
        @BindView(R.id.textViewLanguageUsed)
        TextView mLanguageUsed;
        @BindView(R.id.textViewDescription)
        TextView mDescription;
        @BindView(R.id.repoList)
        LinearLayout mRepo;
        @BindView(R.id.textViewDate)
        TextView mDate;
        private Context mContext;
        private ProgressDialog progressDialog;


        public RepositoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            loadingScreen();
        }

        public void bindRepositories(GitHubUserRepo gitHubUserRepo) {
            mTextViewRepositoryName.setText(gitHubUserRepo.getName());
            if (gitHubUserRepo.getLanguage() == null) {
                mLanguageUsed.setBackgroundResource(android.R.color.transparent);
            } else {
                mLanguageUsed.setText(gitHubUserRepo.getLanguage());

            }
            mDescription.setText(gitHubUserRepo.getDescription());

            mRepo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.show();
                    GithubIssueTracker client = GitHubClient.urlRequest();
                    Call<List<GitHubRepoIssue>> call = client.getRepoIssues(gitHubUserRepo.getOwner().getLogin(), gitHubUserRepo.getName());

                    call.enqueue(new Callback<List<GitHubRepoIssue>>() {
                        @Override
                        public void onResponse(Call<List<GitHubRepoIssue>> call, Response<List<GitHubRepoIssue>> response) {
                            if (response.isSuccessful()) {
                                Boolean gotIssues = false;
                                if (response.body().size() > 0) {
                                    gotIssues = true;
                                }
                                List<GitHubRepoIssue> repoIssues = (ArrayList<GitHubRepoIssue>) response.body();
                                Intent intent = new Intent(mContext, MainActivity.class);
                                intent.putExtra("repoIssues", Parcels.wrap(repoIssues));
                                intent.putExtra("hasIssues", gotIssues);
                                progressDialog.dismiss();
                                mContext.startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<GitHubRepoIssue>> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.d("Errors", t.getLocalizedMessage());
                            Toast.makeText(mContext, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });

            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
            Date d = null;
            try {
                d = input.parse(gitHubUserRepo.getUpdatedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String updatedDate = "Updated on: " + output.format(d);
            mDate.setText(updatedDate);

        }

        private void loadingScreen() {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle("Retrieving Issues");
            progressDialog.setMessage("Retrieving issues for the repository selected");
            progressDialog.setCancelable(false);
        }
    }
}
