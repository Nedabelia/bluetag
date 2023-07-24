package com.aryan.bluetag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class wallpaper_frag extends Fragment {
    ImageView imagevie;




        public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup
        container, @Nullable Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.wallpaper_frag, container, false);

            View imageVie = rootView.findViewById(R.id.i1);

            imageVie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the download_fragment activity when the image is clicked
                    Intent intent = new Intent(getActivity(), download_fragment.class);
                    startActivity(intent);
                }
            });

            return rootView;

        }
    }
