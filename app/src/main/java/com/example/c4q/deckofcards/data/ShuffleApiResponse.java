package com.example.c4q.deckofcards.data;

import java.util.List;

/**
 * Created by c4q on 6/9/18.
 */

public class ShuffleApiResponse {
    private boolean success;
    private boolean shuffled;
    private String deck_id;
    private int remaining;
    private List<Cards> cards;

    public ShuffleApiResponse(boolean success, boolean shuffled, String deck_id, int remaining, List<Cards> cards) {
        this.success = success;
        this.shuffled = shuffled;
        this.deck_id = deck_id;
        this.remaining = remaining;
        this.cards = cards;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }
}
