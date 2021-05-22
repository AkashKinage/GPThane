package com.example.gpthane.Student;

public class model {

    String noticeTitle, imageUri;

    model(){

    }

    public model(String noticeTitle, String imageUri) {
        this.noticeTitle = noticeTitle;
        this.imageUri = imageUri;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
