package com.example.c4q.deckofcards.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by c4q on 6/9/18.
 */

public interface CardsApiService {
    public static final String BASE_URL = "https://deckofcardsapi.com/api/deck/";

    @GET("new/shuffle/")
    Call<ShuffleApiResponse> getShuffledDeck();

    @GET("{deck_id}/draw/?count=num_cards")
    Call<ShuffleApiResponse> getCards(@Path("deck_id") String cards, @Query("count") int numOfCards);

}
