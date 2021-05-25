package com.tommunyiri.eclectics.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tommunyiri.eclectics.R;
import com.tommunyiri.eclectics.adapters.ArticlesListAdapter;
import com.tommunyiri.eclectics.databinding.ActivityMainBinding;
import com.tommunyiri.eclectics.models.Article;
import com.tommunyiri.eclectics.viewmodels.ArticlesViewModel;
import com.tommunyiri.eclectics.worker.FetchArticlesWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ArticlesViewModel articlesViewModel;
    private ActivityMainBinding binding;
    private String TAG = "Main Activity";
    private ArticlesListAdapter articlesListAdapter;
    private List<Article> articlesList;
    PeriodicWorkRequest periodicWorkRequest
            = new PeriodicWorkRequest.Builder(FetchArticlesWorker.class, 2, TimeUnit.MINUTES)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articlesViewModel = new ViewModelProvider(this).get(ArticlesViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        articlesList = new ArrayList<>();
        binding.rvArticles.setLayoutManager(new LinearLayoutManager(this));
        binding.srArticles.setOnRefreshListener(this::fetchArticles);
        fetchArticles();
        WorkManager.getInstance().enqueueUniquePeriodicWork("Eclectics",
                ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest);
        setContentView(view);
    }

    private void fetchArticles() {
        binding.srArticles.setRefreshing(true);
        articlesViewModel.getArticles().observe(this, getArticlesResponse -> {
            if (getArticlesResponse != null) {
                binding.srArticles.setRefreshing(false);
                if (getArticlesResponse.getStatus().equals("ok")) {
                    articlesList = getArticlesResponse.getArticles();
                    if (articlesList.size() > 0) {
                        articlesListAdapter = new ArticlesListAdapter(articlesList, this, MainActivity.this);
                        binding.rvArticles.setAdapter(articlesListAdapter);
                        persistWithRoom(articlesList);
                    } else {
                        //display a message that there are no articles
                    }
                } else {
                    Snackbar.make(binding.activityMain, getArticlesResponse.getStatus(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void persistWithRoom(List<Article> articleList){
        for (Article article:articleList) {
            CompositeDisposable compositeDisposable=new CompositeDisposable();
            compositeDisposable.add(articlesViewModel.addArticleToDatabase(article)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        Toast.makeText(getApplicationContext(), getString(R.string.added_to_database), Toast.LENGTH_SHORT).show();
                        compositeDisposable.dispose();
                    }));
        }
    }

}