package me.self.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MemeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_view);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);

        ImageView imgView = findViewById(R.id.imageView);
//        imgView.setImageURI(Uri.parse(name));
        try {
            Glide.with(getApplicationContext()).load(Uri.parse(name)).into(imgView);
        }catch(Exception e){
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
    }
}