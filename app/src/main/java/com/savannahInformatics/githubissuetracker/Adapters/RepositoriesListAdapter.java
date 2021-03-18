package com.savannahInformatics.githubissuetracker.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;
import com.savannahInformatics.githubissuetracker.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        public RepositoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindRepositories(GitHubUserRepo gitHubUserRepo) {
            mTextViewRepositoryName.setText(gitHubUserRepo.getName());
            mLanguageUsed.setText(gitHubUserRepo.getLanguage());
            mDescription.setText(gitHubUserRepo.getDescription());
        }
    }
}
