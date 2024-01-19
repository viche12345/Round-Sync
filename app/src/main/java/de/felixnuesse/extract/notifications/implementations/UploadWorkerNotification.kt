package de.felixnuesse.extract.notifications.implementations

import android.content.Context
import ca.pkay.rcloneexplorer.R
import ca.pkay.rcloneexplorer.notifications.GenericSyncNotification
import ca.pkay.rcloneexplorer.notifications.prototypes.WorkerNotification

class UploadWorkerNotification(var context: Context) : WorkerNotification(context) {

    override val CHANNEL_ID = "de.felixnuesse.extract.upload_service"

    override val titleStartingSync = R.string.upload_failed
    override val serviceOngoingTitle = R.string.upload_failed
    override val serviceFailed = R.string.upload_failed
    override val serviceCancelled = R.string.upload_cancelled
    override val serviceSuccess = R.string.upload_complete


    override val channel_ongoing_title = mContext.getString(R.string.upload_service_ongoing_notification_title)
    override val channel_ongoing_description = mContext.getString(R.string.upload_service_ongoing_notification_description)
    override val channel_success_title = mContext.getString(R.string.upload_service_success_notification_title)
    override val channel_success_description = mContext.getString(R.string.upload_service_success_notification_description)
    override val channel_failed_title = mContext.getString(R.string.upload_service_failed_notification_title)
    override val channel_failed_description = mContext.getString(R.string.upload_service_failed_notification_description)

    override val PERSISTENT_NOTIFICATION_ID = 90

}