package model;

import model.user.Guest;

public class Comment {
    private String content;
    private Guest owner;

    public Comment(String content, Guest owner) {
        setContent(content);
        setOwner(owner);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Guest getOwner() {
        return owner;
    }

    public void setOwner(Guest owner) {
        this.owner = owner;
    }

    public String toString() {
        return owner.getUsername() + " :" + content;
    }
}
