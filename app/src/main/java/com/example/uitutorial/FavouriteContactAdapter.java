package com.example.uitutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FavouriteContactAdapter extends RecyclerView.Adapter<FavouriteContactAdapter.ViewHolder> {
    private List<FavouriteContactList> favouriteContactList;
    private Context context;

    public FavouriteContactAdapter(List<FavouriteContactList> favouriteContactList, Context context) {
        this.favouriteContactList = favouriteContactList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_favourite_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavouriteContactList favouriteContact = favouriteContactList.get(position);
        Glide.with(context).load(favouriteContact.getImageUrl()).override(170, 170).into(holder.imageViewFavouriteContact);
        holder.contactName.setText(favouriteContact.getName());
    }

    @Override
    public int getItemCount() {
        return favouriteContactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewFavouriteContact;
        public TextView contactName;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewFavouriteContact = itemView.findViewById(R.id.imageFavouriteContact);
            contactName = itemView.findViewById(R.id.textFavouriteContact);
        }
    }
}
