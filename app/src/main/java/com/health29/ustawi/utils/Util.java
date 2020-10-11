package com.health29.ustawi.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import com.health29.ustawi.R;
import com.orhanobut.hawk.Hawk;

import java.io.File;

import androidx.multidex.MultiDex;

public class Util extends Application {

    public void showToastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
