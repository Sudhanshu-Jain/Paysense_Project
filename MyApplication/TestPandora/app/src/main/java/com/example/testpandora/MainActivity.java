package com.example.testpandora;

import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.testpandora.models.Example;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String ROOT_URL = "https://api.github.com";

    private List<Example> postList;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       JSONfromLink jsoNfromLink = new JSONfromLink();
//        try {
//            String response = jsoNfromLink.execute(url).get();
//            JSONObject jsonObject = new JSONObject(response);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        getPosts();

        //showPages();



    }

    private void getPosts(){
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        SamplePostAPI api = retrofit.create(SamplePostAPI.class);
        Call<List<Example>> call = api.getData();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Response<List<Example>> response, Retrofit retrofit) {
                loading.dismiss();
                List<Example> post123 = response.body();
                postList = post123;
//                List<Child> list =data_.getChildren();
//                    Child child = list.get(0);
//                Data_ childData = child.getData();

               // showPages();
                Log.d("CallBack", " response is " + response);
                showPages();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("CallBack", " response is " + t);
                loading.dismiss();
            }
        });


    }

    public void showPages(){
        viewPager.setAdapter(new CustomPagerAdapter(this,postList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
