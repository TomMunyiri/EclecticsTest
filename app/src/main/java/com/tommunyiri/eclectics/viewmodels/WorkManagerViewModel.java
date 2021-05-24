package com.tommunyiri.eclectics.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.tommunyiri.eclectics.worker.FetchArticlesWorker;

public class WorkManagerViewModel extends AndroidViewModel {
    private WorkManager mWorkManager;

    // BlurViewModel constructor
    public WorkManagerViewModel(@NonNull Application application) {
        super(application);
        mWorkManager = WorkManager.getInstance(application);
    }

    void fetchArticles() {
        mWorkManager.enqueue(OneTimeWorkRequest.from(FetchArticlesWorker.class));
    }
}
