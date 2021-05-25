package com.tommunyiri.eclectics.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tommunyiri.eclectics.models.Article;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface ArticlesDao {
    @Query("SELECT * FROM articles")
    Flowable<List<Article>> getArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addArticles(Article article);

    @Delete
    Completable removeArticle(Article article);

    @Query("SELECT * FROM articles WHERE title=:title")
    Flowable<Article> getArticleFromArticles(String title);
}
