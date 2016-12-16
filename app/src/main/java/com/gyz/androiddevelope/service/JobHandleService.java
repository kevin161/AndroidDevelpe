package com.gyz.androiddevelope.service;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.service.JobHandleService.java
 * @author: ZhaoHao
 * @date: 2016-12-15 11:17
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobHandleService extends JobService {
    private static final String TAG = "JobHandleService";
    private int kJobId = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("INFO", "jobService create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("INFO", "jobService start");
        scheduleJob(getJobInfo());
        return START_NOT_STICKY;
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("INFO", "job start");
        boolean isLocalServiceWork = isServiceWork(this, "com.gyz.androiddevelope.service.LocalService");
        boolean isRemoteServiceWork = isServiceWork(this, "com.gyz.androiddevelope.service.RemoteService");
        if(!isLocalServiceWork||
                !isRemoteServiceWork){
            this.startService(new Intent(this,LocalService.class));
            this.startService(new Intent(this,RemoteService.class));
            Toast.makeText(this, "process start", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    /** Send job to the JobScheduler. */
    public void scheduleJob(JobInfo t) {
        Log.i("INFO", "Scheduling job");
        JobScheduler tm =(JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(t);
    }

    private JobInfo getJobInfo() {
        JobInfo.Builder builder = new JobInfo.Builder(kJobId++, new ComponentName(this, JobHandleService.class));
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);
        builder.setRequiresCharging(false);
        builder.setRequiresDeviceIdle(false);
        builder.setPeriodic(1000*60*10);//间隔时间-10分钟-周期
        return builder.build();
    }


    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName
     *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(100);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}
