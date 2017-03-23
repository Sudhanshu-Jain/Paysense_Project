package sample.example.com.paysense_project;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String BASE_URL = "http://pixabay.com/api/";
    public String API_KEY = "4898627-2b108aee01b09f44255af64c7";

    EditText search;
    ImageView cross;
    Button button;
    String url;
    ArrayList<String> arrayList = new ArrayList<>();
    private GridLayoutManager layout;
    RecyclerView grid_view;
    ImageListAdapter rdp;
    private ProgressBar spinner;
    SearchImages searchImages;


    @Override
    public void onBackPressed() {
        if (searchImages != null)
            searchImages.cancel(true);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid_view = (RecyclerView)findViewById(R.id.grid_view);
        grid_view.setHasFixedSize(true);
        layout = new GridLayoutManager(MainActivity.this, 2);
        grid_view.setLayoutManager(layout);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        search = (EditText)findViewById(R.id.search);
        cross = (ImageView)findViewById(R.id.cross);
        button = (Button)findViewById(R.id.button1);
        spinner.setVisibility(View.GONE);
        cross.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                search.setText("");
                cross.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {



                return false;
            }
        });

        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus || search.getText().toString().length() == 0) {
                    search.setHint("Search your images here");
                    cross.setVisibility(View.INVISIBLE);

                } else if (hasFocus) {

                    search.setHint("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

                }
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                cross.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)
                    cross.setVisibility(View.INVISIBLE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = search.getText().toString();
                url = BASE_URL+"?key="+API_KEY+"&q="+s+"&image_type=photo";
                hideKeyboard(search);
                arrayList.clear();
                searchImages = new SearchImages();
                searchImages.execute(url);

            }
        });




    }

    public class SearchImages extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            HttpResponse response = null ;
            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams
                    .setConnectionTimeout(httpParameters, 30000);
            try {

                HttpClient client = new DefaultHttpClient(httpParameters);

                HttpGet httppost = new HttpGet(strings[0]);
                try {
                    response = client.execute(httppost);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response != null) {
                    HttpEntity ent = response.getEntity();
                    try {
                        String responseString = EntityUtils.toString(ent, "UTF-8");
                        JSONObject resp = new JSONObject(responseString);
                        JSONArray hits = resp.getJSONArray("hits");
                        for (int i = 0; i < hits.length(); i++) {
                            JSONObject hit1 = hits.getJSONObject(i);
                            String imageUrl = hit1.getString("userImageURL");
                            arrayList.add(imageUrl);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }catch (Exception e){
                e.getMessage();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            spinner.setVisibility(View.GONE);
            if(!arrayList.isEmpty()) {
                rdp = new ImageListAdapter(MainActivity.this, arrayList);
                grid_view.setAdapter(rdp);
            }
            else{
                Toast.makeText(getApplicationContext(),"Invalid arguement for search",Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);
        }
    }

    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
