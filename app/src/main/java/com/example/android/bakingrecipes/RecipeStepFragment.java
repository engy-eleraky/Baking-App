package com.example.android.bakingrecipes;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


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
    ImageView thumbnail;
    ImageView noVideo;
    Uri thumbnail_url;
    Uri video_url;
    int position;
    Long mPos;
    boolean mPlayVideo;
    ArrayList<StepItem> stepItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args == null) {
            return null;
        }
        final StepItem stepItem= (StepItem) args.getSerializable(DetailsActivity.DATA_KEY);
        position=args.getInt(DetailsActivity.CLICK_KEY);
        stepItems= (ArrayList) args.getSerializable(DetailsActivity.ARRAY_KEY);

        rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        exoPlayerView= rootView.findViewById(R.id.recipe_step_video);
        nextButton=rootView.findViewById(R.id.nextStep);
        prevButton=rootView.findViewById(R.id.previousStep);
        descriptionText = rootView.findViewById(R.id.recipe_description);
        thumbnail=rootView.findViewById(R.id.image);
        noVideo=rootView.findViewById(R.id.no_video);
        descriptionText.setText(stepItem.getDescription());

        thumbnail_url = Uri.parse(stepItems.get(position).getThumbnailURL());
        video_url=Uri.parse(stepItems.get(position).getVideoURL());

        setVideo();
        initialize();
        setExoPLayerVideo(video_url);

        if (savedInstanceState != null) {
            position=savedInstanceState.getInt("pos1");
            Uri newUrl=Uri.parse(stepItems.get(position).getVideoURL());
            descriptionText.setText(stepItems.get(position).getDescription());
            setVideo();
            setExoPLayerVideo(newUrl);
            mPos = savedInstanceState.getLong("seekto");
            seekTo(mPos);
            mPlayVideo=savedInstanceState.getBoolean("playVideo");
            exoPlayer.setPlayWhenReady(mPlayVideo);
        }

        if(DetailsActivity.mTwoPane)
        {
            nextButton.setVisibility(View.INVISIBLE);
            prevButton.setVisibility(View.INVISIBLE);
        }else {
            onClickNext();
            onClickPrev();
        }
        return rootView;

    }
    public void onClickNext(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                position++;
                if (position < stepItems.size()) {
                    Uri newUrl=Uri.parse(stepItems.get(position).getVideoURL());
                    descriptionText.setText(stepItems.get(position).getDescription());
                    releasePlayer();
                    initialize();
                    setVideo();
                    setExoPLayerVideo(newUrl);
                } else {
                    position--;
                }
            }
        });
    }
    public void onClickPrev(){
        prevButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                position--;
                if (position >= 0) {
                    Uri newUrl=Uri.parse(stepItems.get(position).getVideoURL());
                    descriptionText.setText(stepItems.get(position).getDescription());
                    releasePlayer();
                    initialize();
                    setVideo();
                    setExoPLayerVideo(newUrl);
                } else {
                    position++;
                }
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong("seekto", exoPlayer.getCurrentPosition());
        outState.putInt("pos1",position);
        outState.putBoolean("playVideo", exoPlayer.getPlayWhenReady());
        exoPlayer.setPlayWhenReady(false);

        super.onSaveInstanceState(outState);
    }

    public void setVideo() {

        if ( stepItems.get(position).getVideoURL()!=null&& stepItems.get(position).getVideoURL().length() > 0) {
            noVideo.setVisibility(View.INVISIBLE);
            exoPlayerView.setVisibility(View.VISIBLE);
            thumbnail.setVisibility(View.INVISIBLE);

        }
        else if ( stepItems.get(position).getThumbnailURL()!=null&&stepItems.get(position).getThumbnailURL().length() > 0){
            noVideo.setVisibility(View.INVISIBLE);
            exoPlayerView.setVisibility(View.INVISIBLE);
            thumbnail.setVisibility(View.VISIBLE);

            Picasso picasso = new Picasso.Builder(getActivity())
                    .listener(new Picasso.Listener() {
                        @Override
                        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                            Toast.makeText(getActivity(),"thunmbnail fails to load",Toast.LENGTH_SHORT).show();
                            thumbnail.setVisibility(View.INVISIBLE);
                            noVideo.setVisibility(View.VISIBLE);
                        }
                    })
                    .build();
            picasso.load(thumbnail_url)
                    .into(thumbnail);
        }
        else{
            exoPlayerView.setVisibility(View.INVISIBLE);
            noVideo.setVisibility(View.VISIBLE);
            thumbnail.setVisibility(View.INVISIBLE);

        }
    }

    private void setExoPLayerVideo(final Uri uri) {
        String userAgent = Util.getUserAgent(getActivity(), "bakingrecipes");
        MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }
    private void initialize(){
        if (exoPlayer==null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);

        }

    }
    private void seekTo(long pos) {
        exoPlayer.seekTo(pos);
    }

    private void releasePlayer() {
        if(exoPlayer!=null) {
            exoPlayer.stop();
            exoPlayer.release();
        }
        exoPlayer = null;
    }


    @Override
    public void onStop() {
        super.onStop();
        if(exoPlayer!=null) {
            mPos=exoPlayer.getCurrentPosition();
            mPlayVideo=exoPlayer.getPlayWhenReady();
            exoPlayer.setPlayWhenReady(false);
            exoPlayer.stop();
            exoPlayer.release();
        }
        exoPlayer=null;
    }

    @Override
    public void onResume() {
        super.onResume();

        try{
            initialize();
            video_url = Uri.parse(stepItems.get(position).getVideoURL());
            setExoPLayerVideo(video_url);
            exoPlayer.seekTo(mPos);
            exoPlayer.setPlayWhenReady(mPlayVideo);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }


}
