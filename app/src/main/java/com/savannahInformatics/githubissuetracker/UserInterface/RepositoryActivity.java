package com.savannahInformatics.githubissuetracker.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.savannahInformatics.githubissuetracker.Models.GitHubUserDetails;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;
import com.savannahInformatics.githubissuetracker.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryActivity extends AppCompatActivity {
    @BindView(R.id.textViewUserName)
    TextView mTextViewUserName;
    @BindView(R.id.textViewBio)
    TextView mTextViewBio;
    @BindView(R.id.textViewRepositoriesNumber)
    TextView mTextViewRepositories;
    @BindView(R.id.imageViewAvatar)
    ImageView mImageViewAvatar;
    GitHubUserDetails userDetails;
    List<GitHubUserRepo> userRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);

        userDetails = Parcels.unwrap(getIntent().getParcelableExtra("githubUserDetails"));
        userRepos = Parcels.unwrap(getIntent().getParcelableExtra("githubUserRepo"));

        setUserDetails();

    }

    public void setUserDetails(){
        if (!userDetails.getAvatarUrl().isEmpty()) {
            Picasso.get().load(userDetails.getAvatarUrl()).into(mImageViewAvatar);
        }
        if (!userDetails.getName().isEmpty()) {
            mTextViewUserName.setText(userDetails.getName());
        }else{
            mTextViewUserName.setText(userDetails.getLogin());
        }
        mTextViewBio.setText(userDetails.getBio());
//        mTextViewRepositories.setText(userDetails.getPublicRepos());
    }
}