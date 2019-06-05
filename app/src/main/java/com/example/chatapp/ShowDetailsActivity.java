package com.example.chatapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chatapp.Adapters.MessageAdapter;
import com.example.chatapp.Models.Chat;
import com.example.chatapp.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

public class ShowDetailsActivity extends AppCompatActivity {

    CircleImageView image_profile;
    TextView username, phonenumber, email, dateOfBirth;
    FirebaseUser firebaseUser;

    DatabaseReference reference;


    Intent intent;

    String userid;


    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        image_profile = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        phonenumber = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        dateOfBirth = findViewById(R.id.date_of_birth);

        intent = getIntent();

        userid = intent.getStringExtra("userid");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);


                username.setText(user.getUsername());
                phonenumber.setText(user.getPhonenumber());
                email.setText(user.getEmail());
                dateOfBirth.setText(user.getDateOfBirth());
                if (user.getImageURL().equals("default")){
                    image_profile.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    Glide.with(getApplicationContext()).load(user.getImageURL()).into(image_profile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
