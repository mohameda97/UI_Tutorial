package com.example.uitutorial;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Messages extends AppCompatActivity {
    private static final String URL_MESSAGES = "https://emlenotes.com/challenges/android/Screen_2.json";
    public ImageView senderImageView;
    public TextView senderTextView;
    String name;
    String pic;
    private List<MessageInfoList> messageInfoListItems;
    private RecyclerView.Adapter messagesAdapter;
    private RecyclerView messagesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = findViewById(R.id.messagesToolBar);
        setSupportActionBar(toolbar);
        senderImageView = findViewById(R.id.toolbarSenderImageView);
        senderTextView = findViewById(R.id.toolbarSenderTextView);
        messagesRecyclerView = findViewById(R.id.recyclerViewMessages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(linearLayoutManager);
        messageInfoListItems = new ArrayList<>();

        loadMessages();


    }

    private void loadMessages() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_MESSAGES,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        name = jsonObject.getString("Name");
                        pic = jsonObject.getString("Pic");
                        senderTextView.setText(name);
                        Glide.with(this).load(pic).override(150, 150).into(senderImageView);

                        JSONArray messagesArray = jsonObject.getJSONArray("Messages");
                        for (int i = 0; i < messagesArray.length(); i++) {
                            JSONObject object = messagesArray.getJSONObject(i);
                            MessageInfoList messageInfoList = new MessageInfoList(pic, object.getString("Message"), object.getInt("Sender"));
                            messageInfoListItems.add(messageInfoList);
                        }
                        messagesAdapter = new MessageInfoAdapter(messageInfoListItems, getApplicationContext());
                        messagesRecyclerView.setAdapter(messagesAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}