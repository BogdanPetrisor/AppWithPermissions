package com.example.appwithpermissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val requestButton = findViewById<Button>(R.id.btn_requestPermission)
        requestButton.setOnClickListener{
            requestPermissions()
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionsRequest","${permissions[i]} granted")
                }
            }
        }
    }

}