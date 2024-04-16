package de.felixnuesse.extract.updates.workmanager

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class UpdateManager(private var mContext: Context) {

    companion object {
        private val TAG_ONETIME = "TAG_ONETIME"
        private val TAG_REPEATING = "TAG_REPEATING"
    }


    fun queueOnetime() {
        val uploadWorkRequest = OneTimeWorkRequestBuilder<UpdateWorker>()
        uploadWorkRequest.addTag(TAG_ONETIME)
        work(uploadWorkRequest.build())
    }

    fun queueRepeating() {
        val checkRequest = PeriodicWorkRequestBuilder<UpdateWorker>(15, TimeUnit.MINUTES)
        checkRequest.setConstraints(getConstrains())
        work(checkRequest.build())
    }

    private fun getConstrains(): Constraints {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        return constraints
    }

    private fun work(request: WorkRequest) {
        WorkManager.getInstance(mContext).enqueue(request)
    }

    fun cancelOnetime() {
        WorkManager.getInstance(mContext).cancelAllWorkByTag(TAG_ONETIME)
    }

    fun cancelRepeating() {
        WorkManager.getInstance(mContext).cancelAllWorkByTag(TAG_REPEATING)
    }

    fun cancelAll() {
        WorkManager.getInstance(mContext).cancelAllWork()
    }
}