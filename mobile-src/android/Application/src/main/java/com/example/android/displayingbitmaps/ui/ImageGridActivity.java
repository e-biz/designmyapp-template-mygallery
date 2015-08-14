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

package com.example.android.displayingbitmaps.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.BuildConfig;
import com.example.android.displayingbitmaps.R;
import com.example.android.displayingbitmaps.events.CreateFileEvent;
import com.example.android.displayingbitmaps.events.FinishedCreateFileEvent;
import com.example.android.displayingbitmaps.events.UploadImageEvent;
import com.example.android.displayingbitmaps.util.Utils;

import java.io.File;
import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Simple FragmentActivity to hold the main {@link ImageGridFragment} and not much else.
 */
public class ImageGridActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "ImageGridActivity";

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    public static final String CURRENT_PHOTO_PATH = "mCurrentPhotoPath";
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, new ImageGridFragment(), TAG);
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(CURRENT_PHOTO_PATH, mCurrentPhotoPath);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentPhotoPath = savedInstanceState.getString(CURRENT_PHOTO_PATH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_picture_button:
                EventBus.getDefault().post(new CreateFileEvent(getExternalFilesDir(null)));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            EventBus.getDefault().post(new UploadImageEvent(mCurrentPhotoPath, getString(R.string.backendUrl)));
        }
    }

    public void onEventMainThread(FinishedCreateFileEvent event) {
        if (event.isSuccess()) {
            mCurrentPhotoPath = event.getFile().getPath();
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                // Put the file who will contain the picture in the intent
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(event.getFile()));
                // Start an activity with the takePictureIntent
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Toast.makeText(this, getString(R.string.no_camera), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.failed_create_file), Toast.LENGTH_SHORT).show();
        }
    }
}
