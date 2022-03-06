package com.haggbart.dat153.namequiz.helper;

import android.net.Uri;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static Uri fromString(String uriString) {
        return Uri.parse(uriString);
    }

    @TypeConverter
    public static String uriToString(Uri uri) {
        return uri.toString();
    }
}
