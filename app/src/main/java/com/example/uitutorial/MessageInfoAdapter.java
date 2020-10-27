package com.example.uitutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageInfoAdapter extends RecyclerView.Adapter<MessageInfoAdapter.ViewHolder> {
    private final static int RECEIVER = 0;
    private final static int SENDER = 1;

    List<MessageInfoList> messageInfoListItems;
    private Context context;

    public MessageInfoAdapter(List<MessageInfoList> messageInfoListItems, Context context) {
        this.messageInfoListItems = messageInfoListItems;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        final MessageInfoList messageInfoListItem = messageInfoListItems.get(position);
        if (messageInfoListItem.getSender() == RECEIVER) {
            return RECEIVER;
        } else {
            return SENDER;
        }
    }

    @NonNull
    @Override
    public MessageInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == RECEIVER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.receiver, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sender, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageInfoAdapter.ViewHolder holder, int position) {
        final MessageInfoList messageInfoListItem = messageInfoListItems.get(position);

        switch (holder.getItemViewType()) {
            case RECEIVER:
                holder.receiverTextView.setText(messageInfoListItem.getMessage());
                break;
            case SENDER:
                holder.senderTextView.setText(messageInfoListItem.getMessage());
                Glide.with(context).load(messageInfoListItem.getImageUrl()).override(150, 150).into(holder.senderImageView);

        }
    }


    @Override
    public int getItemCount() {

        return messageInfoListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView senderImageView;
        public TextView senderTextView;
        public TextView receiverTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            senderImageView = itemView.findViewById(R.id.senderImageView);
            senderTextView = itemView.findViewById(R.id.senderTextView);
            receiverTextView = itemView.findViewById(R.id.receiverTextView);
        }
    }
}
