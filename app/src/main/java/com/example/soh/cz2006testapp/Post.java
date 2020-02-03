package com.example.soh.cz2006testapp;

public class Post {

    private String Title, Price, Image, DisplayName;

    /**
     * Empty Constructor for post.
     */

    public Post(){

    }

    /**
     * Constructor for post.
     * @param title
     * @param price
     * @param image
     * @param displayName
     */

    public Post(String title, String price, String image, String displayName) {
        Title = title;
        Price = price;
        Image = image;
        DisplayName = displayName;

    }

    /**
     * Gets post title.
     * @return title
     */

    public String getTitle() {

        return Title;
    }

    /**
     * Sets post title.
     * @param title
     */

    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Gets property price.
     * @return price
     */

    public String getPrice() {
        return Price;
    }

    /**
     * Sets property price.
     * @param price
     */

    public void setPrice(String price) {
        Price = price;
    }

    /**
     * Gets property image.
     * @return image
     */

    public String getImage() {
        return Image;
    }

    /**
     * Sets property image.
     * @param image
     */

    public void setImage(String image) {
        Image = image;
    }

    /**
     * Gets user display name.
     * @return user display name
     */

    public String getDisplayName() {
        return DisplayName;
    }

    /**
     * Sets user display name.
     * @param displayName
     */

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
}
