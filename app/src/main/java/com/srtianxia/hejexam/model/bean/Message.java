package com.srtianxia.hejexam.model.bean;
import java.util.ArrayList;
import java.util.List;

public class Message {
//    public String id;
//    public String authorId;
//    public String title;
//    public String summary;
//    public String content;
//    public String image;
//    public String imageType;
//    public String url;
//    public String source;
//    public boolean liked;
//    public int likeCount;
//    public int style;// MessageStyleShort 1 MessageStyleLong 2  MessageStyleUrl 3
//    public int type;//1 new //2 hot
//    public long createdAt;
//    //public String[] subjectIds;
//    public ArrayList<Stock> stocks;
//    public String shareUrl;

    private String Id;
    private String AuthorId;
    private Integer Style;
    private String Title;
    private String Summary;
    private String Image;
    private String ImageType;
    private String Url;
    private String Source;
    private String DisplayAuthor;
    private Boolean Liked;
    private Integer LikeCount;
    private Integer CreatedAt;
    private Integer UpdatedAt;
    private Integer Type;
    private String ShareUrl;

    public List<Stock> getStocks() {
        return Stocks;
    }

    public void setStocks(List<Stock> stocks) {
        Stocks = stocks;
    }

    private List<Stock> Stocks = new ArrayList<Stock>();

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
    }

    public Integer getStyle() {
        return Style;
    }

    public void setStyle(Integer style) {
        Style = style;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImageType() {
        return ImageType;
    }

    public void setImageType(String imageType) {
        ImageType = imageType;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getDisplayAuthor() {
        return DisplayAuthor;
    }

    public void setDisplayAuthor(String displayAuthor) {
        DisplayAuthor = displayAuthor;
    }

    public Boolean getLiked() {
        return Liked;
    }

    public void setLiked(Boolean liked) {
        Liked = liked;
    }

    public Integer getLikeCount() {
        return LikeCount;
    }

    public void setLikeCount(Integer likeCount) {
        LikeCount = likeCount;
    }

    public Integer getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Integer createdAt) {
        CreatedAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        UpdatedAt = updatedAt;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getShareUrl() {
        return ShareUrl;
    }

    public void setShareUrl(String shareUrl) {
        ShareUrl = shareUrl;
    }

}
