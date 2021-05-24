package com.tommunyiri.eclectics.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tommunyiri.eclectics.network.ApiClient;
import com.tommunyiri.eclectics.network.ApiService;
import com.tommunyiri.eclectics.responses.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesRepo {
    private ApiService apiService;
    public ArticlesRepo() {
        apiService= ApiClient.Companion.getRetrofit().create(ApiService.class);
    }

    public LiveData<GetArticlesResponse> getArticles() {
        MutableLiveData<GetArticlesResponse> data = new MutableLiveData<>();
        apiService.getArticles().enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetArticlesResponse> call, @NonNull Response<GetArticlesResponse> response) {
                data.setValue(response.body());
                /*try {
                    Log.d("Auth Repo",""+response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(@NonNull Call<GetArticlesResponse> call, @NonNull Throwable t) {
                data.setValue(null);
                Log.d("Articles Repo",t.getLocalizedMessage());
            }
        });
        return data;
    }
}
