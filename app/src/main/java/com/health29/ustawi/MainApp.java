package com.health29.ustawi;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.orhanobut.hawk.Hawk;

import java.io.File;

import androidx.multidex.MultiDex;

public class MainApp extends Application {

    private static File mFolder;

    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();

        mFolder = createFolder(getResources()
                .getString(R.string.save_directory_file));
    }


    private static File createFolder(String name) {
        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + name;
        File f = new File(folder);
        if (!f.exists()){
            f.mkdir();
        }
        return f;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
