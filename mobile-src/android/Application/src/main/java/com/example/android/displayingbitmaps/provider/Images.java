/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.displayingbitmaps.provider;

/**
 * Some simple test data to use for this sample app.
 */
public class Images {

    public static final String THUMBNAILS = "?thumbnail=true";
    public static final String IMAGES = "image/";

    private static String[] imageUrls = new String[0];

    private static String[] imageThumbUrls = new String[0];

    public static String[] getImageUrls() {
        return imageUrls;
    }

    public static String[] getImageThumbUrls() {
        return imageThumbUrls;
    }

    public static void setUrls(String[] urls) {
        String[] imgUrls = new String[urls.length];
        String[] imgThumbUrls = new String[urls.length];


        for (int i=0; i<urls.length; i++) {
            imgUrls[i] = IMAGES + urls[i];
            imgThumbUrls[i] = IMAGES + urls[i] + THUMBNAILS;
        }
        imageUrls = imgUrls;
        imageThumbUrls = imgThumbUrls;
    }
}
