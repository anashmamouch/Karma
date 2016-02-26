package com.benzino.karma.utils;

/**
 * Created on 23/2/16.
 *
 * @author Anas
 */
public class Constants{
    /* Firebase Nodes*/
    public static final String FIREBASE_NODE_CHAT = "chats";
    public static final String FIREBASE_NODE_MESSAGES = "messages";

    /*Firebase Properties*/
    public static final String FIREBASE_PROPERTY_CHATNAME = "chatName";
    public static final String FIREBASE_PROPERTY_MESSAGENAME= "messageName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";

    /* Firebase URL */
    public static final String FIREBASE_URL = "https://androidbenzino.firebaseio.com/";
    public static final String FIREBASE_URL_CHATS = FIREBASE_URL + "/" + FIREBASE_NODE_CHAT;
    public static final String FIREBASE_URL_MESSAGES = FIREBASE_URL + "/" + FIREBASE_NODE_MESSAGES;


    public static final String KEY_CHAT_ID = "CHAT_ID" ;
}
