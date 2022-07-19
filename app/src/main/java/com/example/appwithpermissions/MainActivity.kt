package com.example.appwithpermissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasForegroundLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasBackgroundLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        val permissionsList = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()) {
            permissionsList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!hasForegroundLocationPermission()) {
            permissionsList.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasBackgroundLocationPermission()) {
            permissionsList.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (permissionsList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsList.toTypedArray(), 0)
        }
    }
}