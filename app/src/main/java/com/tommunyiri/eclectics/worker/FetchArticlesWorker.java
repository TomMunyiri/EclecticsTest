package com.tommunyiri.eclectics.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.tommunyiri.eclectics.R;
import com.tommunyiri.eclectics.repos.ArticlesRepo;
import com.tommunyiri.eclectics.responses.GetArticlesResponse;

import org.jetbrains.annotations.NotNull;

public class FetchArticlesWorker extends Worker {
    Handler handler;
    Runnable runnable;
    private String TAG="WorkManager";
    public FetchArticlesWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @NotNull
    @Override
    public Result doWork() {
        getArticles();
        Log.d(TAG, "doWork: "+Result.success());
        displayNotification("Eclectics","Fetching Articles");
        Log.d(TAG, "doWork: "+getArticles().toString());
        return Result.retry();
    }

    private void displayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Eclectics", "Eclectics", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "Eclectics")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());
    }

    public LiveData<GetArticlesResponse> getArticles(){
        ArticlesRepo articlesRepo=new ArticlesRepo();
        return articlesRepo.getArticles();
    }
}