package com.tommunyiri.eclectics.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.work.BackoffPolicy;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.tommunyiri.eclectics.R;
import com.tommunyiri.eclectics.adapters.ArticlesListAdapter;
import com.tommunyiri.eclectics.databinding.ActivityMainBinding;
import com.tommunyiri.eclectics.models.Article;
import com.tommunyiri.eclectics.ui.fragments.auth.LoginHomeViewModel;
import com.tommunyiri.eclectics.viewmodels.ArticlesViewModel;
import com.tommunyiri.eclectics.worker.FetchArticlesWorker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ArticlesViewModel articlesViewModel;
    private ActivityMainBinding binding;
    private String TAG = "Main Activity";
    private ArticlesListAdapter articlesListAdapter;
    private List<Article> articlesList;
    final PeriodicWorkRequest periodicWorkRequest
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
                    } else {
                        //display a message that there are no articles
                    }
                } else {
                    Snackbar.make(binding.activityMain, getArticlesResponse.getStatus(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}