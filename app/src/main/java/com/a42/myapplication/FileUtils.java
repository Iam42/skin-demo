package com.a42.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by a42 on 14/12/30.
 */
public class FileUtils {

    public static void copyAssets(Context context) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("skin.apk");
                File outFile = new File(context.getFilesDir() , "skin.apk");
                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in = assetManager.open("skin2.apk");
                outFile = new File(context.getFilesDir() , "skin2.apk");
                out = new FileOutputStream(outFile);
                copyFile(in, out);

            } catch(IOException e) {
                Log.e("tag", "---", e);
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }

    }
    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

}
