package com.giyoon.ketorant.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.giyoon.ketorant.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment implements  View.OnClickListener {

    private static final String ARG_PAGE = "ARG_PAGE";
    private OnFragmentInteractionListener mListener;

    private Animation fab_open, fab_close, fab_transform, fab_transform_back;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab_sub_review, fab_sub_photo;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);



        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fab_transform = AnimationUtils.loadAnimation(getContext(), R.anim.fab_transform);
        fab_transform_back = AnimationUtils.loadAnimation(getContext(), R.anim.fab_transform_back);

        fab = (FloatingActionButton) rootView.findViewById(R.id.floatBtn);
        fab_sub_photo = (FloatingActionButton) rootView.findViewById(R.id.floatBtn_sub_photo);
        fab_sub_review = (FloatingActionButton) rootView.findViewById(R.id.floatBtn_sub_review);


        fab.setOnClickListener(this);
        fab_sub_photo.setOnClickListener(this);
        fab_sub_review.setOnClickListener(this);



        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.floatBtn:
                anim();
                break;
            case R.id.floatBtn_sub_photo:
                anim();
                //새로운 인텐트로 이동
                break;
            case R.id.floatBtn_sub_review:
                anim();
                //새로운 인텐트로 이동
                break;
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void anim() {

        if (isFabOpen) {
            fab.startAnimation(fab_transform_back);
            fab.setBackgroundColor(R.color.colorBlack);
            fab.setImageResource(R.drawable.ic_add_white_24dp);
            fab_sub_review.startAnimation(fab_close);
            fab_sub_photo.startAnimation(fab_close);
            fab_sub_review.setClickable(false);
            fab_sub_photo.setClickable(false);
            isFabOpen = false;
        } else {
            fab.setImageResource(R.drawable.ic_add_rot_white_24dp);
            fab.startAnimation(fab_transform);
            fab.setBackgroundColor(R.color.colorGray);
            fab_sub_review.startAnimation(fab_open);
            fab_sub_photo.startAnimation(fab_open);
            fab_sub_review.setClickable(true);
            fab_sub_photo.setClickable(true);
            isFabOpen = true;
        }
    }

}
