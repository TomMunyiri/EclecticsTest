package com.tommunyiri.eclectics.network;

import com.tommunyiri.eclectics.responses.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("everything?q=bitcoin&apiKey=b7c93266b90840d091f8eb414bf6ea5b")
    Call<GetArticlesResponse> getArticles();
}
