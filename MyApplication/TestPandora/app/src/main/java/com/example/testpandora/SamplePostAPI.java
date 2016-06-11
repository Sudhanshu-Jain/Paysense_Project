package com.example.testpandora;

import com.example.testpandora.models.Example;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by sudhanshu on 13/4/16.
 */
public interface SamplePostAPI {
    @GET("/repositories")
    Call<List<Example>> getData();
}
