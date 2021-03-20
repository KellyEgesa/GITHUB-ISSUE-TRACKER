package com.savannahInformatics.githubissuetracker.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.savannahInformatics.githubissuetracker.Adapters.IssueListAdapter;
import com.savannahInformatics.githubissuetracker.Models.GitHubRepoIssue;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;
import com.savannahInformatics.githubissuetracker.R;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerViewIssues)
    RecyclerView mRecyclerViewIssues;
    @BindView(R.id.alternativeLayout)
    RelativeLayout alternativeLayout;
    @BindView(R.id.searchViewIssues)
    SearchView mSearch;
    @BindView(R.id.textViewDate)
    TextView mDate;
    List<GitHubRepoIssue> repoIssues;
    IssueListAdapter issueListAdapter;
    Boolean gotIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearch.setIconified(false);

        gotIssues = getIntent().getBooleanExtra("hasIssues", false);
        repoIssues = Parcels.unwrap(getIntent().getParcelableExtra("repoIssues"));

        setUpIssues();
        setUpDate();

    }

    private void setUpIssues() {
        if (gotIssues) {
            alternativeLayout.setVisibility(View.GONE);
            mRecyclerViewIssues.setVisibility(View.VISIBLE);
            issueListAdapter = new IssueListAdapter(repoIssues);
            mRecyclerViewIssues.setAdapter(issueListAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerViewIssues.setLayoutManager(layoutManager);
            mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterIssues(newText);
                    return false;
                }
            });
        }
    }

    private void filterIssues(String query) {
        List<GitHubRepoIssue> tempRepoIssue = new ArrayList<>();
        for (GitHubRepoIssue title : repoIssues) {
            if (title.getTitle().toLowerCase().contains(query)) {
                tempRepoIssue.add(title);
            }
        }
        issueListAdapter.updateList(tempRepoIssue);
    }

    private void setUpDate() {
        Calendar calendar = Calendar.getInstance();

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        mDate.setText(date.replace("2021", ""));

    }
}