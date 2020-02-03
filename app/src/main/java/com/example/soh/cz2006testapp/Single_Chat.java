package com.example.soh.cz2006testapp;

public class Single_Chat {
    private String DisplayName;
    private String ChatID;

    /**
     * Empty constructor for single chat
     */

    public Single_Chat(){

    }

    /**
     * Constructor for single chat.
     * @param displayName
     * @param chatID
     */

    public Single_Chat(String displayName, String chatID) {
        this.DisplayName = displayName;
        ChatID = chatID;

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
        this.DisplayName = displayName;
    }

    /**
     * Gets chat ID
     * @return chat ID.
     */

    public String getChatID() {
        return ChatID;
    }

    /**
     * Sets chat ID
     * @param chatID
     */

    public void setChatID(String chatID) {
        ChatID = chatID;
    }
}
