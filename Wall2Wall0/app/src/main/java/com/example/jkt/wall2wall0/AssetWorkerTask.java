package com.example.jkt.wall2wall0;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.jkt.wall2wall0.impl.AndroidImage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by JDK on 9/5/2015.
 */
public class AssetWorkerTask extends AsyncTask <Object, Void, AndroidImage> {

    AssetManager assets;

    @Override
    protected AndroidImage doInBackground(Object... params) {
        String fileName = (String) params[0];
        Graphics.ImageFormat format = (Graphics.ImageFormat) params[1];


        Bitmap.Config config = null;
        if (format == Graphics.ImageFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == Graphics.ImageFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;



        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }

        if (bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = Graphics.ImageFormat.RGB565;
        else if (bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = Graphics.ImageFormat.ARGB4444;
        else
            format = Graphics.ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);

    }

}
