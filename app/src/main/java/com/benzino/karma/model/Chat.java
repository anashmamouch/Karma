package com.benzino.karma.model;

import com.benzino.karma.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created on 24/2/16.
 *
 * @author Anas
 */
public class Chat implements Serializable {
    private String chatName;
    private String admin;
    private HashMap<String, Object> timestampCreated;

    public Chat() {
    }

    public Chat(String chatName, String admin) {
        this.chatName = chatName;
        this.admin = admin;

        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        this.timestampCreated = timestampCreated;

    }

    public Chat(String chatName, String admin, long howManyMessages){
        this.chatName = chatName;
        this.admin = admin;

        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        this.timestampCreated = timestampCreated;

    }

    public String getChatName() {
        return chatName;
    }

    public String getAdmin() {
        return admin;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    @JsonIgnore
    public long getTimestampCreatedLong(){
        return (long) timestampCreated.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }
}
