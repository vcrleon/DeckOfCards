package com.example.c4q.deckofcards.data;

/**
 * Created by c4q on 6/9/18.
 */

public class Cards {
    private String value;
    private String suit;
    private String image;

    public Cards(String value, String suit, String image) {
        this.value = value;
        this.suit = suit;
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
