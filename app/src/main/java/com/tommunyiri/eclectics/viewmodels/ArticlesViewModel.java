package com.tommunyiri.eclectics.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tommunyiri.eclectics.repos.ArticlesRepo;
import com.tommunyiri.eclectics.responses.GetArticlesResponse;

public class ArticlesViewModel extends ViewModel {
    private ArticlesRepo articlesRepo;

    public ArticlesViewModel() {
        articlesRepo=new ArticlesRepo();
    }

    public LiveData<GetArticlesResponse> getArticles(){
        return articlesRepo.getArticles();
    }
}
