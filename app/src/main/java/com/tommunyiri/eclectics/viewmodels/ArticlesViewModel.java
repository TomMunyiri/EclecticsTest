package com.tommunyiri.eclectics.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tommunyiri.eclectics.models.Article;
import com.tommunyiri.eclectics.repos.ArticlesRepo;
import com.tommunyiri.eclectics.responses.GetArticlesResponse;
import com.tommunyiri.eclectics.room.database.ArticlesDatabase;

import io.reactivex.Completable;

public class ArticlesViewModel extends AndroidViewModel {
    private ArticlesRepo articlesRepo;
    private ArticlesDatabase articlesDatabase;

    public ArticlesViewModel(@NonNull Application application) {
        super(application);
        articlesRepo=new ArticlesRepo();
        articlesDatabase=ArticlesDatabase.getArticlesDatabase(application);
    }

    public LiveData<GetArticlesResponse> getArticles(){
        return articlesRepo.getArticles();
    }

    public Completable addArticleToDatabase(Article article){
        return articlesDatabase.articlesDao().addArticles(article);
    }
}
