package com.haggbart.dat153.namequiz.helper;

import android.net.Uri;

import com.haggbart.dat153.namequiz.BuildConfig;

public class ImageHelper {

    /**
     * @param resourceId the id of the resource
     * @return URI for the resource
     */
    public static Uri getUri(int resourceId) {
        return Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + resourceId);
    }
}
