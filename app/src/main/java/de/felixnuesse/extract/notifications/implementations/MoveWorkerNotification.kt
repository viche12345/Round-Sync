package de.felixnuesse.extract.notifications.implementations

import android.content.Context
import ca.pkay.rcloneexplorer.R
import ca.pkay.rcloneexplorer.notifications.GenericSyncNotification
import ca.pkay.rcloneexplorer.notifications.prototypes.WorkerNotification

class MoveWorkerNotification(var context: Context) : WorkerNotification(context) {

    override val CHANNEL_ID = "de.felixnuesse.extract.move_service"

    override val titleStartingSync = R.string.upload_failed
    override val serviceOngoingTitle = R.string.upload_failed
    override val serviceFailed = R.string.upload_failed
    override val serviceCancelled = R.string.upload_cancelled
    override val serviceSuccess = R.string.upload_complete

    // use only ongoing and "success" channels
    override val CHANNEL_FAIL_ID = CHANNEL_SUCCESS_ID

    override val channel_ongoing_title = mContext.getString(R.string.move_service_notification_title)
    override val channel_ongoing_description = mContext.getString(R.string.move_service_notification_description)
    override val channel_success_title = channel_ongoing_title
    override val channel_success_description = channel_ongoing_description
    override val channel_failed_title = channel_ongoing_title
    override val channel_failed_description = channel_ongoing_description

    override val PERSISTENT_NOTIFICATION_ID = 43

}