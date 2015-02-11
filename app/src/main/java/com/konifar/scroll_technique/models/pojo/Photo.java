package com.konifar.scroll_technique.models.pojo;

public class Photo {

    private String imageUrl;
    private String title;
    private String subTitle;

    public Photo(String imageUrl, String title, String subTitle) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

}
