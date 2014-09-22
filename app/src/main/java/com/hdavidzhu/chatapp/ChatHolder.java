package com.hdavidzhu.chatapp;

public class ChatHolder {
    String id, name, timestamp, message;
    byte[] picture;

    // Let's construct a place to hold our chat information.
    public ChatHolder(String id, String name, String timestamp, String message){
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.message = message;
    }

    // Set the name of the person.
    public void setName(String name){
        this.name = toTitleCase(name);
    }

    // Set the message of the text.
    public void setMessage(String message){
        this.message = toTitleCase(message);
    }

    // What does this do? [???]
    public String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }
}