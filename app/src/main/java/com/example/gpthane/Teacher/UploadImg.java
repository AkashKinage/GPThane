package com.example.gpthane.Teacher;

public class UploadImg {

    private String noticeTitle;
    private String imageUri;

    public UploadImg(){

    }

    public UploadImg(String title, String imageuri){
        if(title.trim().equals("")){
            title = "no title";
        }
        noticeTitle = title;
        imageUri = imageuri;
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
