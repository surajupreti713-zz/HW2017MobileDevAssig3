package com.surajupreti.howardchat;

public class Message {
    private String name;
    private String id;
    private String text;

    public Message() {

    }

    public Message(String name, String id, String text) {
        this.name = name;
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getName() {

        return name;
    }


    public interface MessageListener {

    }
}
