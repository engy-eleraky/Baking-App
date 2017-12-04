package com.example.android.bakingrecipes;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingrecipes.models.StepItem;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import static android.R.attr.orientation;

/**
 * Created by Noga on 11/26/2017.
 */

public class RecipeStepFragment extends Fragment {
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    TextView descriptionText;
    View rootView;
    ImageButton nextButton;
    ImageButton prevButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args == null) {
            return null;
        }

        StepItem stepItem= (StepItem) args.getSerializable(DetailsActivity.DATA_KEY);
            rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
            exoPlayerView= rootView.findViewById(R.id.recipe_step_video);
            nextButton=rootView.findViewById(R.id.nextStep);
            prevButton=rootView.findViewById(R.id.previousStep);
            descriptionText = rootView.findViewById(R.id.recipe_description);
            descriptionText.setText(stepItem.getDescription());
        exoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource
                (getResources(), R.drawable.caramello));

        setVideo(stepItem);
        return rootView;

    }

    public void setVideo(StepItem stepItem) {

        try {
            setExoPLayerVideo(Uri.parse(stepItem.getVideoURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void setExoPLayerVideo(Uri uri) {
        if (exoPlayer==null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);
            String userAgent = Util.getUserAgent(getActivity(), "bakingrecipes");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
    }
    private void releasePlayer() {
        if(exoPlayer!=null) {
            exoPlayer.stop();
            exoPlayer.release();
        }
        exoPlayer = null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

}
