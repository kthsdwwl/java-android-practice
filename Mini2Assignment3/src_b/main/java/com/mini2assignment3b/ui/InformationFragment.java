package com.mini2assignment3b.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mini2assignment3b.R;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class InformationFragment extends Fragment {
    private TextView homeLink;
    private TextView socialLink;
    private TextView likeText;
    private TextView likeCountText;
    private TextView dislikeText;
    private TextView dislikeCountText;

    public InformationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_information, container, false);
        getElements(view);
        addListeners();
        return view;
    }

    /**
     * Get all the ui objects
     * @param view
     */
    private void getElements(View view) {
        homeLink = (TextView) view.findViewById(R.id.text_artist_website);
        socialLink = (TextView) view.findViewById(R.id.text_artist_social);
        likeText = (TextView) view.findViewById(R.id.text_like);
        likeCountText = (TextView) view.findViewById(R.id.text_like_count);
        dislikeText = (TextView) view.findViewById(R.id.text_dislike);
        dislikeCountText = (TextView) view.findViewById(R.id.text_dislike_count);
    }

    /**
     * Add listeners to ui objects
     */
    private void addListeners() {
        homeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLink(getResources().getString(R.string.href_website));
            }
        });

        socialLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLink(getResources().getString(R.string.href_social));
            }
        });

        likeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseLikeCount();
            }
        });

        dislikeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseDislikeCount();
            }
        });

    }

    /**
     * Open websites according to the addresses
     * @param href
     */
    private void gotoLink(String href) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(href));
        startActivity(intent);
    }

    /**
     * Increase the count of like
     */
    private void increaseLikeCount() {
        Integer count = Integer.parseInt(likeCountText.getText().toString());
        ++count;
        likeCountText.setText(count.toString());
    }

    /**
     * Increase the count of dislike
     */
    private void increaseDislikeCount() {
        Integer count = Integer.parseInt(dislikeCountText.getText().toString());
        ++count;
        dislikeCountText.setText(count.toString());
    }

}
