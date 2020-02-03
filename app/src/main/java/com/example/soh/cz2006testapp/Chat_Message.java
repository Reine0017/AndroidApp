package com.example.soh.cz2006testapp;

import java.util.Date;

public class Chat_Message {
    private String messageText;
    private String messageUser;
    private long messageTime;

    /**
     * Constructor for chat message.
     * @param text
     * @param user
     */

    public Chat_Message(String text, String user){
        messageText = text;
        messageUser = user;

        messageTime = new Date().getTime();
    }

    /**
     * Empty constructor for chat message.
     */

    public Chat_Message(){}

    /**
     * Gets chat message.
     * @return message text
     */

    public String getMessageText() {
        return messageText;
    }

    /**
     * Sets chat message.
     * @param messageText
     */

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Gets user display name.
     * @return user display name.
     */

    public String getMessageUser() {
        return messageUser;
    }

    /**
     * Sets user display name.
     * @param messageUser
     */

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    /**
     * Gets time that chat message has been posted.
     * @return time
     */

    public long getMessageTime() {
        return messageTime;
    }

    /**
     * Sets time that chat message has been posted.
     * @param messageTime
     */

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
