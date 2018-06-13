package com.example.c4q.deckofcards.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.c4q.deckofcards.R;
import com.example.c4q.deckofcards.data.Cards;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by c4q on 6/9/18.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    List<Cards> cardsList;

    public CardAdapter(List<Cards> cardsList) {
        this.cardsList = cardsList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_item_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Cards cards = cardsList.get(position);
        holder.onBind(cards);
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    public void addCards(List<Cards> cards) {
        cardsList.addAll(cards);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardImage;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image);
        }

        public void onBind(final Cards cards) {
            Picasso.with(itemView.getContext()).load(cards.getImage()).into(cardImage);
            cardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), cards.getValue() + " of " + cards.getSuit(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
