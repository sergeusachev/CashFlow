package com.serge.usachev.cashflow

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    companion object {
        private const val SMS_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isSmsPermissionGranted()) {
            showRequestPermissionsInfoAlertDialog()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            SMS_PERMISSION_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // SMS related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

                return
            }
        }
    }

    /**
     * Check if we have SMS permission
     */
    fun isSmsPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Request runtime SMS permission
     */
    private fun requestReadAndSendSmsPermission() {
       /* if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
            // You may display a non-blocking explanation here, read more in the documentation:
            // https://developer.android.com/training/permissions/requesting.html
        }*/
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS),
            SMS_PERMISSION_CODE)
    }

    /**
     * Displays an AlertDialog explaining the user why the SMS permission is going to be requests
     *
     * @param makeSystemRequest if set to true the system permission will be shown when the dialog is dismissed.
     */
    fun showRequestPermissionsInfoAlertDialog() {
        showRequestPermissionsInfoAlertDialog(true)
    }

    fun showRequestPermissionsInfoAlertDialog(makeSystemRequest: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Автоматическое чтение СМС") // Your own title
        builder.setMessage("Чтобы Ваши финансовые траты автоматически " +
                "заносились в список мы читаем смс сообщения от баноков.") // Your own message

        builder.setPositiveButton("Круто, я согласен") { dialog, which ->
            dialog.dismiss()
            // Display system runtime permission request?
            if (makeSystemRequest) {
                requestReadAndSendSmsPermission()
            }
        }

        builder.setCancelable(false)
        builder.show()
    }
}
