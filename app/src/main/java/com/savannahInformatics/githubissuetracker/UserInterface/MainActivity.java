package com.savannahInformatics.githubissuetracker.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.savannahInformatics.githubissuetracker.Adapters.IssueListAdapter;
import com.savannahInformatics.githubissuetracker.Models.GitHubRepoIssue;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;
import com.savannahInformatics.githubissuetracker.R;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    @BindView(R.id.spinnerFilterBy)
    Spinner mFilterBy;
    List<GitHubRepoIssue> repoIssues;
    IssueListAdapter issueListAdapter;
    Boolean gotIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gotIssues = getIntent().getBooleanExtra("hasIssues", false);
        repoIssues = Parcels.unwrap(getIntent().getParcelableExtra("repoIssues"));

        setUpIssues();
        setUpDate();
        setUpFilterBy();

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

    private void setUpFilterBy() {
        String[] arrayFilterBy = new String[]{"All", "Up to 5 comments", "Up to 10 comments", "Over 10 comments only", "No Comments"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, android.R.id.text1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFilterBy.setAdapter(adapter);

        for (String filterOptions : arrayFilterBy
        ) {
            adapter.add(filterOptions);

        }
        adapter.notifyDataSetChanged();


        mFilterBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setText(null);

                int commentsCount = -1;
                List<GitHubRepoIssue> tempRepoIssue = new ArrayList<>();

                if (position == 3) {
                    commentsCount = 11;
                } else if (position == 2) {
                    commentsCount = 10;
                } else if (position == 1) {
                    commentsCount = 5;
                } else if (position == 4) {
                    commentsCount = 0;
                }


                if (commentsCount == -1) {
                    tempRepoIssue = repoIssues;
                } else {
                    for (GitHubRepoIssue title : repoIssues) {
                        if (commentsCount <= 10) {
                            if (title.getComments() <= commentsCount) {
                                tempRepoIssue.add(title);
                            }
                        } else {
                            if (title.getComments() > 10) {
                                tempRepoIssue.add(title);
                            }
                        }
                    }
                }

                issueListAdapter.updateList(tempRepoIssue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}