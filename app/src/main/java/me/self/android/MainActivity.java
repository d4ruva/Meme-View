package me.self.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        ImageView imgView = findViewById(R.id.imgView);

        Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(v -> {
            String url = "https://meme-api.herokuapp.com/gimme";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            //                                Toast.makeText(getApplicationContext(), response.getString("url"), Toast.LENGTH_SHORT).show();
                            try {
                                Glide.with(getApplicationContext()).load(Uri.parse(response.getString("url"))).into(imgView);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Toast.makeText(getApplicationContext(), "Coluldnt Get the meme. Try Connecting to the internet!", Toast.LENGTH_SHORT).show();
                        }
                    });

            queue.add(jsonObjectRequest);
        });
        
    }
}