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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.displayingbitmaps.R;
import com.example.android.displayingbitmaps.Service.SaveService;
import com.example.android.displayingbitmaps.events.FinishedSaveOneEvent;
import com.example.android.displayingbitmaps.events.SaveOneEvent;
import com.example.android.displayingbitmaps.util.ImageFetcher;
import com.example.android.displayingbitmaps.util.ImageWorker;
import com.example.android.displayingbitmaps.util.Utils;

import de.greenrobot.event.EventBus;

/**
 * This fragment will populate the children of the ViewPager from {@link ImageDetailActivity}.
 */
public class ImageDetailFragment extends Fragment implements OnClickListener {
    private static final String IMAGE_DATA_EXTRA = "extra_image_data";
    private String mImageUrl;
    private ImageView mImageView;
    private ImageFetcher mImageFetcher;

    private ProgressBar mSaveProgressBar;

    /**
     * Factory method to generate a new instance of the fragment given an image number.
     *
     * @param imageUrl The image url to load
     * @return A new instance of ImageDetailFragment with imageNum extras
     */
    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString(IMAGE_DATA_EXTRA, imageUrl);
        f.setArguments(args);

        return f;
    }

    /**
     * Empty constructor as per the Fragment documentation
     */
    public ImageDetailFragment() {}

    /**
     * Populate image using a url from extras, use the convenience factory method
     * {@link ImageDetailFragment#newInstance(String)} to create this fragment.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString(IMAGE_DATA_EXTRA) : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate and locate the main ImageView
        final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
        mImageView = (ImageView) v.findViewById(R.id.imageView);

        mSaveProgressBar = (ProgressBar) v.findViewById(R.id.save_ProgressBar);

        if (getResources().getBoolean(R.bool.enableLocalStorage)) {
            Button mSaveButton = (Button) v.findViewById(R.id.save_Button);
            mSaveButton.setVisibility(View.VISIBLE);
            mSaveButton.setOnClickListener(this);
        }

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Use the parent activity to load the image asynchronously into the ImageView (so a single
        // cache can be used over all pages in the ViewPager
        if (ImageDetailActivity.class.isInstance(getActivity())) {
            mImageFetcher = ((ImageDetailActivity) getActivity()).getImageFetcher();
            mImageFetcher.loadImage(mImageUrl, mImageView);
        }

        // Pass clicks on the ImageView to the parent activity to handle
        if (OnClickListener.class.isInstance(getActivity()) && Utils.hasHoneycomb()) {
            mImageView.setOnClickListener((OnClickListener) getActivity());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

        if (SaveService.INSTANCE.isInSaving(mImageUrl)) {
            mSaveProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImageView != null) {
            // Cancel any pending image work
            ImageWorker.cancelWork(mImageView);
            mImageView.setImageDrawable(null);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.save_Button:
                mSaveProgressBar.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new SaveOneEvent(getActivity().getApplicationContext(), mImageUrl));
                break;
        }
    }

    public void onEventMainThread(FinishedSaveOneEvent event) {
        if (mImageUrl.equals(event.getUrl())) {
            mSaveProgressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity().getApplicationContext(), event.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
