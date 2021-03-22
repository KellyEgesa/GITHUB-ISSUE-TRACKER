package com.savannahInformatics.githubissuetracker.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savannahInformatics.githubissuetracker.Models.GitHubRepoIssue;
import com.savannahInformatics.githubissuetracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        @BindView(R.id.textViewIssueCode)
        TextView mIssueCode;

        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindIssues(GitHubRepoIssue gitHubRepoIssue) {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
            Date d = null;
            try {
                d = input.parse(gitHubRepoIssue.getCreatedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String issueCode = "#" + gitHubRepoIssue.getNumber();
            mIssueCode.setText(issueCode);
            mIssueDate.setText(output.format(d));
            mStatus.setText(gitHubRepoIssue.getState());
            mTitle.setText(gitHubRepoIssue.getTitle());
            mUserName.setText(gitHubRepoIssue.getUser().getLogin());
            mComments.setText(String.valueOf(gitHubRepoIssue.getComments()));
        }
    }


}
