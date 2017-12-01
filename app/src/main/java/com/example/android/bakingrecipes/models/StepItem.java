package com.example.android.bakingrecipes.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Noga on 11/27/2017.
 */

public class StepItem  implements Serializable{
   // @SerializedName("id")
    private String id;
    //@SerializedName("shortDescription")
    private String shortDescription;
   // @SerializedName("description")
    private String description;
   // @SerializedName("videoURL")
    private String videoURL;
    //@SerializedName("thumbnailURL")
    private String thumbnailURL;
    public StepItem(String id,String shortDescription,String description,String videoURL,String thumbnailURL){
        this.id=id;
        this.shortDescription=shortDescription;
        this.description=description;
        this.videoURL=videoURL;
        this.thumbnailURL=thumbnailURL;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
