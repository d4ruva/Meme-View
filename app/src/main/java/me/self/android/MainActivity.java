package me.self.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Intent intent = new Intent(this, MemeView.class);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String url = "https://meme-api.herokuapp.com/gimme";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                intent.putExtra(EXTRA_NAME, response.getString("url"));
//                                Toast.makeText(getApplicationContext(), response.getString("url"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                intent.putExtra(EXTRA_NAME, "Couldnt get it sorry");
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });

            queue.add(jsonObjectRequest);

            try {
//                intent.putExtra(EXTRA_NAME, "This is the Value");
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Got error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}