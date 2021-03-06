package com.example.nimmy.task7_apicalling;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


//    private JsonDataAdapter jsonDataAdapter;
//    private ListView listView;
//    private  ArrayList<Post> postArrayList = new ArrayList<>();

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyClass().execute("https://jsonplaceholder.typicode.com/posts");
       /* if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        HttpURLConnection connection;
        URL url = null;
        try {
            url = new URL("https://jsonplaceholder.typicode.com/posts");

        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();

        InputStream stream = null;
        try {
            stream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(stream));
        StringBuffer buffer = new StringBuffer();
        String line="";
        try {
            while((line = reader.readLine())!=null)
            {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String bufferString = buffer.toString();


        listView = (ListView)findViewById(R.id.main_listview);
        jsonDataAdapter = new JsonDataAdapter(this,postArrayList);
        listView.setAdapter(jsonDataAdapter);

        try {
                JSONArray rootArray = new JSONArray(bufferString);
                for (int i = 0; i < rootArray.length(); i++) {
                JSONObject postObject = rootArray.getJSONObject(i);
                int userId = postObject.getInt("userId");
                int id = postObject.getInt("id");
                String title = postObject.getString("title");
                String body = postObject.getString("body");

                    Post post = new Post();
                    post.setId(id);
                    post.setUserId(userId);
                    post.setTitle(title);
                    post.setBody(body);
                    postArrayList.add(post);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }*/

    }
    class MyClass extends AsyncTask<String,Void,String>{

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);
                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line =reader.readLine())!= null){
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return  bufferString;


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(dialog.isShowing()) {
                dialog.dismiss();
            }
            ArrayList<Post> postArrayList = new ArrayList<>();
            try {
                JSONArray rootArray = new JSONArray(s);
                for (int i = 0; i < rootArray.length(); i++) {
                    JSONObject postObject = rootArray.getJSONObject(i);
                    int userId = postObject.getInt("userId");
                    int id = postObject.getInt("id");
                    String title = postObject.getString("title");
                    String body = postObject.getString("body");

                    Post post = new Post();
                    post.setId(id);
                    post.setUserId(userId);
                    post.setTitle(title);
                    post.setBody(body);
                    postArrayList.add(post);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListView  listView = (ListView)findViewById(R.id.main_listview);
            JsonDataAdapter   jsonDataAdapter = new JsonDataAdapter(MainActivity.this,postArrayList);
            listView.setAdapter(jsonDataAdapter);

        }
    }
}
