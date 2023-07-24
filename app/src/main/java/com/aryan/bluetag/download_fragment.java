package com.aryan.bluetag;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class download_fragment extends Fragment {

    ImageView imageView;
    Button downloadButton;
    String imageUrl = ""; // Replace this with the actual image URL


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_download_fragment);

    imageView = imageView.findViewById(R.id.imageView);
            downloadButton = downloadButton.findViewById(R.id.downloadButton);

            downloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Download and display the image using Glide
                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop();

                    Glide.with(download_fragment.this)
                            .load(imageUrl)
                            .apply(requestOptions)
                            .into(imageView);
                }
            });
        }

    private void setContentView(int fragment_download_fragment) {

    }
}
