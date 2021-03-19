package com.savannahInformatics.githubissuetracker.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savannahInformatics.githubissuetracker.Models.GitHubRepoIssue;
import com.savannahInformatics.githubissuetracker.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.IssueViewHolder> {
    private List<GitHubRepoIssue> mRepoIssues;


    public IssueListAdapter(List<GitHubRepoIssue> issues) {
        mRepoIssues = issues;
    }

    @NonNull
    @Override
    public IssueListAdapter.IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issues, parent, false);
        IssueViewHolder viewHolder = new IssueViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IssueListAdapter.IssueViewHolder holder, int position) {
        holder.bindIssues(mRepoIssues.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepoIssues.size();
    }

    public void updateList(List<GitHubRepoIssue> repoIssues) {
        mRepoIssues = repoIssues;
        notifyDataSetChanged();
    }

    public class IssueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewDateIssue)
        TextView mIssueDate;
        @BindView(R.id.textViewStatusIssue)
        TextView mStatus;
        @BindView(R.id.textViewTitleIssue)
        TextView mTitle;
        @BindView(R.id.textViewUserIssue)
        TextView mUserName;
        @BindView(R.id.textViewCommentsIssue)
        TextView mComments;

        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindIssues(GitHubRepoIssue gitHubRepoIssue) {
            mIssueDate.setText(gitHubRepoIssue.getCreatedAt());
            mStatus.setText(gitHubRepoIssue.getState());
            mTitle.setText(gitHubRepoIssue.getTitle());
            mUserName.setText(gitHubRepoIssue.getUser().getLogin());
            mComments.setText(String.valueOf(gitHubRepoIssue.getComments()));
        }
    }


}
