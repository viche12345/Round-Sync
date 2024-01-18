package ca.pkay.rcloneexplorer.workmanager

import android.content.Context
import android.os.Parcel
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import ca.pkay.rcloneexplorer.Items.FileItem
import ca.pkay.rcloneexplorer.Items.RemoteItem
import java.util.Random


class EphemeralTaskManager(private var mContext: Context) {

    companion object {

        fun queueDownload(
            context: Context,
            remote: RemoteItem,
            downloadItem: FileItem,
            selectedPath: String) {

            val data = Data.Builder()
            data.putString(EphemeralWorker.EPHEMERAL_TYPE, Type.DOWNLOAD.name)
            data.putString(EphemeralWorker.REMOTE_ID, remote.name)
            data.putString(EphemeralWorker.REMOTE_TYPE, remote.typeReadable)

            data.putString(EphemeralWorker.DOWNLOAD_TARGETPATH, selectedPath)


            val parcel = Parcel.obtain()
            downloadItem.writeToParcel(parcel, 0)
            data.putByteArray(EphemeralWorker.DOWNLOAD_SOURCE, parcel.marshall())

            EphemeralTaskManager(context).work(data.build(), "")
        }

        fun queueUpload(
            context: Context,
            remote: RemoteItem,
            uploadFile: String,
            currentPath: String
        ) {}

        fun queueMove(
            context: Context,
            remote: RemoteItem,
            currentPath: String,
            moveItem: FileItem,
            path2: String
        ) {}

        fun queueDelete(
            context: Context,
            remote: RemoteItem,
            deleteItem: FileItem,
            currentPath: String
        ) {}
    }


    protected fun work(inputData: Data, tag: String) {
        val uploadWorkRequest = OneTimeWorkRequestBuilder<EphemeralWorker>()
        uploadWorkRequest.setInputData(inputData)
        uploadWorkRequest.addTag(tag)
        WorkManager.getInstance(mContext).enqueue(uploadWorkRequest.build())
    }

    fun cancel() {
        WorkManager.getInstance(mContext)
            .cancelAllWork()
    }
    fun cancel(tag: String) {

        //Intent syncIntent = new Intent(context, SyncService.class);
        //syncIntent.setAction(TASK_CANCEL_ACTION);
        //syncIntent.putExtra(EXTRA_TASK_ID, intent.getLongExtra(EXTRA_TASK_ID, -1));
        //context.startService(syncIntent);
        Log.e("TAG", "CANCEL"+tag)
        WorkManager
            .getInstance(mContext)
            .cancelAllWorkByTag(tag)
    }
}