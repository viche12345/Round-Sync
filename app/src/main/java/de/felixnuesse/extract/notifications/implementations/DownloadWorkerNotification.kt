package de.felixnuesse.extract.notifications.implementations

import android.content.Context
import ca.pkay.rcloneexplorer.notifications.prototypes.WorkerNotification

class DownloadWorkerNotification(var context: Context) : WorkerNotification(context) {

    override val CHANNEL_ID = "de.felixnuesse.extract.download_service"
    override val CHANNEL_SUCCESS_ID = CHANNEL_ID+"_success"
    override val CHANNEL_FAIL_ID = CHANNEL_ID+"_fail"
    override val PERSISTENT_NOTIFICATION_ID = 389
}