package com.example.uitutorial;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL_FAVOURITE_CONTACT = "https://emlenotes.com/challenges/android/Screen_1.json";
    private List<FavouriteContactList> favouriteContactList;
    private RecyclerView.Adapter favouriteContactAdapter;
    private RecyclerView recyclerViewFavouriteContact;
    private RecyclerView recyclerViewContact;
    private RecyclerView.Adapter contactAdapter;

    private List<ContactList> contactListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Toolbar toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);

        recyclerViewFavouriteContact = findViewById(R.id.recyclerViewFavourite);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewFavouriteContact.setLayoutManager(mLayoutManager);
        favouriteContactList = new ArrayList<>();

        recyclerViewContact = findViewById(R.id.recyclerViewContact);
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));
        contactListItems = new ArrayList<>();

        loadFavouriteContact();
        loadContact();
    }


    private void loadContact() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_FAVOURITE_CONTACT,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("Recent");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String newMessage = new String(object.getString("Message").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            ContactList contactList = new ContactList(object.getString("Name"), object.getString("Pic"), newMessage, object.getString("Time"), object.getInt("New"));
                            contactListItems.add(contactList);
                        }
                        contactAdapter = new ContactListAdapter(contactListItems, getApplicationContext());
                        recyclerViewContact.setAdapter(contactAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadFavouriteContact() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_FAVOURITE_CONTACT,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("Favorites");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            FavouriteContactList favouriteContactList = new FavouriteContactList(object.getString("Name"), object.getString("Pic"));
                            this.favouriteContactList.add(favouriteContactList);
                        }
                        favouriteContactAdapter = new FavouriteContactAdapter(favouriteContactList, getApplicationContext());
                        recyclerViewFavouriteContact.setAdapter(favouriteContactAdapter);
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