package com.example.ex9_permissionsandbroadcast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RECEIVE_SMS_REQUEST_CODE   = 1;
    private static final int READ_SMS_REQUEST_CODE      = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This two function will call each time when MainActivity will create
        askForSmsDangerousPermissions();

    }


    //we need to ask for permission because we use dangerous permission.
    private void askForSmsDangerousPermissions() {
        requestSmsDangerousPermission(Manifest.permission.READ_SMS, READ_SMS_REQUEST_CODE);
        requestSmsDangerousPermission(Manifest.permission.RECEIVE_SMS, RECEIVE_SMS_REQUEST_CODE);
    }

    //we choose to use the new android flow in order to handle request.
    //permission= READ_SMS.
    //permissionRequestCode = READ_SMS_REQUEST_CODE.
    private void requestSmsDangerousPermission(String permission, int permissionRequestCode)
    {
        // check if permission already granted
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
            return;

        // Permission is not granted. show an explanation.
        else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
            Toast.makeText(this, "You must grant this permission in order to see SMS messages", Toast.LENGTH_LONG).show();

        // request the permission
        else ActivityCompat.requestPermissions(this, new String[] { permission }, permissionRequestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*if (grantResults.length == 0)
            return;

        boolean firstPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;*/

        /*switch (requestCode) {
            case RECEIVE_SMS_REQUEST_CODE:
                Toast.makeText(this, "RECEIVE_SMS permission granted: " + firstPermissionGranted, Toast.LENGTH_SHORT).show();
                break;
            case READ_SMS_REQUEST_CODE:
                Toast.makeText(this, "READ_SMS permission granted: " + firstPermissionGranted, Toast.LENGTH_SHORT).show();
                break;
        }
    }*/
        switch (requestCode) {
            case RECEIVE_SMS_REQUEST_CODE:
                Toast.makeText(this, "RECEIVE_SMS permission granted: ", Toast.LENGTH_SHORT).show();
                break;
            case READ_SMS_REQUEST_CODE:
                Toast.makeText(this, "READ_SMS permission granted: ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}