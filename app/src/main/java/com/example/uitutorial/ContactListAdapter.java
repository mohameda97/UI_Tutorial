package com.example.uitutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private final Context context;
    List<ContactList> contactListItems;

    public ContactListAdapter(List<ContactList> contactListItems, Context context) {
        this.contactListItems = contactListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ContactList contactListItem = contactListItems.get(position);

        if (position % 2 == 0) { // set a background to even views
            holder.contactRelativeLayout.setBackgroundResource(R.drawable.contactbackground);
        }
        if (contactListItem.getNumNewMessage() > 0) {
            holder.textViewTime.setText(contactListItem.getTime());
            holder.textViewNumNewMessage.setText(String.valueOf(contactListItem.getNumNewMessage()));
        } else {
            holder.textViewTime.setVisibility(View.INVISIBLE);
            holder.textViewNumNewMessage.setVisibility(View.INVISIBLE);
        }
        holder.textViewName.setText(contactListItem.getName());
        Glide.with(context).load(contactListItem.getImageUrl()).override(170, 170).into(holder.imageViewContact);
        holder.textViewMessage.setText(contactListItem.getNewMessage());
        holder.contactRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(context, Messages.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageViewContact;
        public TextView textViewMessage;
        public TextView textViewTime;
        public TextView textViewNumNewMessage;
        public RelativeLayout contactRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactRelativeLayout = itemView.findViewById(R.id.contactView);
            textViewName = itemView.findViewById(R.id.name);
            imageViewContact = itemView.findViewById(R.id.pic);
            textViewMessage = itemView.findViewById(R.id.newMessage);
            textViewTime = itemView.findViewById(R.id.time);
            textViewNumNewMessage = itemView.findViewById(R.id.numNewMessage);
        }
    }

}
