package com.tommunyiri.eclectics.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.tommunyiri.eclectics.models.Article;
import com.tommunyiri.eclectics.room.daos.ArticlesDao;
import com.tommunyiri.eclectics.room.typeconverters.SourceConverter;

@Database(entities = Article.class,version = 1,exportSchema = false)
@TypeConverters({SourceConverter.class})
public abstract class ArticlesDatabase extends RoomDatabase {
    private static ArticlesDatabase articlesDatabase;

    public static synchronized ArticlesDatabase getArticlesDatabase(Context context){
        if(articlesDatabase==null){
            articlesDatabase= Room.databaseBuilder(
                    context,
                    ArticlesDatabase.class,
                    "articles_db"
            ).build();
        }
        return articlesDatabase;
    }

    public abstract ArticlesDao articlesDao();
}
