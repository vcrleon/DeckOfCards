package com.example.c4q.deckofcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c4q.deckofcards.data.Cards;
import com.example.c4q.deckofcards.data.CardsApiService;
import com.example.c4q.deckofcards.data.ShuffleApiResponse;
import com.example.c4q.deckofcards.presentation.CardAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView cardsRemaining;
    private Button shuffleCardsBttn;
    private EditText cardsInput;
    private Button drawCardsBttn;
    private String shuffledId;
    private int remainingCards;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CardAdapter(new ArrayList<Cards>());
        recyclerView.setAdapter(adapter);

       retrofit = new Retrofit.Builder()
                .baseUrl(CardsApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        shuffleCardsBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardsApiService cardsApiService = retrofit.create(CardsApiService.class);
                Call<ShuffleApiResponse> call = cardsApiService.getShuffledDeck();
                call.enqueue(new Callback<ShuffleApiResponse>() {
                    @Override
                    public void onResponse(Call<ShuffleApiResponse> call, Response<ShuffleApiResponse> response) {
                        shuffledId = response.body().getDeck_id();
                        remainingCards = response.body().getRemaining();
                        Log.d("shuffled cards", shuffledId + " " + remainingCards);
                        cardsRemaining.setText(getString(R.string.cards_remaining_mssg, remainingCards+""));
                    }

                    @Override
                    public void onFailure(Call<ShuffleApiResponse> call, Throwable t) {

                    }
                });
            }
        });

        drawCardsBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cardsChosen = Integer.parseInt(cardsInput.getText().toString());
//                boolean empty = TextUtils.isEmpty(cardsInput.getText().toString());

                if(cardsChosen< 1){
                    cardsInput.setError(getString(R.string.one_card_error));
                } else if(cardsChosen > remainingCards){
                    cardsInput.setError(getString(R.string.less_cards_error, remainingCards+""));
                } else {
                    CardsApiService cardsApiService = retrofit.create(CardsApiService.class);
                    Call<ShuffleApiResponse> call = cardsApiService.getCards(shuffledId, cardsChosen);
                    call.enqueue(new Callback<ShuffleApiResponse>() {
                        @Override
                        public void onResponse(Call<ShuffleApiResponse> call, Response<ShuffleApiResponse> response) {
                            List<Cards> cards = response.body().getCards(); //a list of the urls
                           adapter.addCards(cards);
                           adapter.notifyDataSetChanged();

                            Log.d("cards deck", cards.toString()); //i'm getting back the objects but for some reason they are not displaying on the recycler view
                            Log.d("card name", cards.get(0).getSuit());
                        }
                        @Override
                        public void onFailure(Call<ShuffleApiResponse> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                    cardsInput.setText(null);

                }

            }
        });

    }

    public void setViews(){
        cardsRemaining = findViewById(R.id.cards_remaining_view);
        shuffleCardsBttn = findViewById(R.id.shuffle_cards_bttn);
        cardsInput = findViewById(R.id.cards_input_view);
        drawCardsBttn = findViewById(R.id.draw_cards_bttn);
        recyclerView = findViewById(R.id.cards_rv);
    }

}
