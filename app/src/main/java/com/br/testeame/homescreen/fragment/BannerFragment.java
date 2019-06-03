package com.br.testeame.homescreen.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.br.testeame.R;
import com.br.testeame.api.model.Banner;
import com.squareup.picasso.Picasso;


public class BannerFragment extends Fragment {


    private Banner banner;
    ImageView imageBanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_banner, container, false);
        banner = getArguments().getParcelable("banner");

        imageBanner = view.findViewById(R.id.image_banner);

        if (banner != null && imageBanner != null) {

            Picasso.get()
                    .load(banner.getUrlImagem())
                    .placeholder(getActivity().getResources().getDrawable(R.drawable.logo_sobre))
                    .error(getActivity().getResources().getDrawable(R.drawable.logo_sobre))
                    .into(imageBanner);
        }


        return view;
    }
}
