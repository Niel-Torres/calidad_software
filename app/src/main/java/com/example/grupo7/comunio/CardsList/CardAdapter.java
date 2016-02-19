package com.example.grupo7.comunio.CardsList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grupo7.comunio.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<PlayerItem> mItems;

    public CardAdapter() {
        super();
        mItems = new ArrayList<>();

        //Tarjetas de jugadores: se hará un bucle for para todos los jugadores del usuario, obteniendo los datos requeridos para cada uno


        PlayerItem player = new PlayerItem();
        player.setPlayerName("Cristiano Ronaldo");
        player.setPlayerData("➤ DELANTERO\n➤ Precio: 4000000€\n➤ Puntos: 340");
        player.setPlayerFoto(R.drawable.real_madrid);
        mItems.add(player);

        player = new PlayerItem();
        player.setPlayerName("Lionel Messi");
        player.setPlayerData("➤ DELANTERO\n➤ Precio: 3500000€\n➤ Puntos: 380");
        player.setPlayerFoto(R.drawable.real_madrid);
        mItems.add(player);

        player = new PlayerItem();
        player.setPlayerName("Sergio Morata");
        player.setPlayerData("➤ MEDIO\n➤ Precio: 1500000€\n➤ Puntos: 65");
        player.setPlayerFoto(R.drawable.real_madrid);
        mItems.add(player);

        player = new PlayerItem();
        player.setPlayerName("Gareth Bale");
        player.setPlayerData("➤ MEDIO\n➤ Precio: 2500000€\n➤ Puntos: 195");
        player.setPlayerFoto(R.drawable.real_madrid);
        mItems.add(player);


        player = new PlayerItem();
        player.setPlayerName("Diego Costa");
        player.setPlayerData("➤ DEFENSA\n➤ Precio: 2000000€\n➤ Puntos: 140");
        player.setPlayerFoto(R.drawable.real_madrid);
        mItems.add(player);

        player = new PlayerItem();
        player.setPlayerName("David De Gea");
        player.setPlayerData("➤ PORTERO\n➤ Precio: 1850000\n➤ Puntos: 72");
        player.setPlayerFoto(R.drawable.real_madrid);
        mItems.add(player);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        PlayerItem player = (PlayerItem) mItems.get(i);
        viewHolder.namePlayer.setText(player.getPlayerName());
        viewHolder.dataPlayer.setText(player.getPlayerData());
        viewHolder.imgPlayer.setImageResource(player.getPlayerFoto());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgPlayer;
        public TextView namePlayer;
        public TextView dataPlayer;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPlayer = (ImageView)itemView.findViewById(R.id.img_player);
            namePlayer = (TextView)itemView.findViewById(R.id.name_player);
            dataPlayer = (TextView)itemView.findViewById(R.id.data_player);
        }
    }


}