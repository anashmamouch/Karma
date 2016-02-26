package com.benzino.karma.model;

import com.benzino.karma.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created on 24/2/16.
 *
 * @author Anas
 */
public class Message implements Serializable {
    String messageName;
    String owner;
    long likes;
    HashMap<String, Object> createdAt;

    public Message() {
    }

    public Message(String messageName, String owner) {
        this.messageName = messageName;
        this.owner = owner;

        HashMap<String, Object> createdAt = new HashMap<>();
        createdAt.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.createdAt = createdAt;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getOwner() {
        return owner;
    }

    public long getLikes() {
        return likes;
    }

    public HashMap<String, Object> getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public long createdAtLong(){
        return (long) createdAt.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }
}
