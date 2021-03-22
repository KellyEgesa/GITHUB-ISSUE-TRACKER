package com.savannahInformatics.githubissuetracker.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @BindView(R.id.spinnerDate)
    Spinner mDateFilter;
    @BindView(R.id.imageViewIssueToolBar)
    ImageView mBackButton;

    List<GitHubRepoIssue> repoIssues;
    IssueListAdapter issueListAdapter;
    Boolean gotIssues;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gotIssues = getIntent().getBooleanExtra("hasIssues", false);
        repoIssues = Parcels.unwrap(getIntent().getParcelableExtra("repoIssues"));


        setUpDate();

        if (gotIssues) {
            setUpIssues();
            setUpFilterBy();
            setUpDateFilter();
        }

    }

    private void setUpIssues() {
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

        date = calendar.getTime();

        String stringDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        mDate.setText(stringDate.replace("2021", ""));

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

    private void setUpDateFilter() {
        String[] arrayFilterDate = new String[]{"All", "This Week", "This Month", "This Year"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, android.R.id.text1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDateFilter.setAdapter(adapter);

        for (String filterOptions : arrayFilterDate
        ) {
            adapter.add(filterOptions);

        }
        adapter.notifyDataSetChanged();


        mDateFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setText(null);
                SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int week = cal.get(Calendar.WEEK_OF_YEAR);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                int dateFromIndex = 0;
                List<GitHubRepoIssue> tempRepoIssue = new ArrayList<>();

                if (position > 0) {
                    dateFromIndex = position;
                }

                if (dateFromIndex == 0) {
                    tempRepoIssue = repoIssues;
                } else {
                    for (GitHubRepoIssue title : repoIssues) {
                        Date d = null;
                        try {
                            d = input.parse(title.getCreatedAt());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (dateFromIndex == 1) {
                            Calendar calIssue = Calendar.getInstance();
                            calIssue.setTime(d);

                            int weekIssue = calIssue.get(Calendar.WEEK_OF_YEAR);
                            if (week == weekIssue) {
                                tempRepoIssue.add(title);
                            }
                        } else if (dateFromIndex == 2) {
                            Calendar calIssue = Calendar.getInstance();
                            calIssue.setTime(d);

                            int monthIssue = calIssue.get(Calendar.MONTH);
                            int yearIssue = calIssue.get(Calendar.YEAR);
                            if (month == monthIssue && year == yearIssue) {
                                tempRepoIssue.add(title);
                            }
                        } else {
                            Calendar calIssue = Calendar.getInstance();
                            calIssue.setTime(d);

                            int yearIssue = calIssue.get(Calendar.YEAR);
                            if (year == yearIssue) {
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