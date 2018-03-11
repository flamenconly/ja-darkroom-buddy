package com.thomasringhofer.jadarkroombuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomasringhofer.jadarkroombuddy.model.DevelopmentProcessAndItsActivities;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecentDevelopmentProcessesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecentDevelopmentProcessesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentDevelopmentProcessesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_DESCRIPTION = "arg_description";
    private static final String ARG_DURATION = "arg_duration";


    private String mTitle;
    private String mDescription;
    private String mDuration;

    private OnFragmentInteractionListener mListener;

    public RecentDevelopmentProcessesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param developmentProcessAndItsActivities Parameter 1.
     * @return A new instance of fragment RecentDevelopmentProcessesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecentDevelopmentProcessesFragment newInstance(DevelopmentProcessAndItsActivities developmentProcessAndItsActivities) {
        RecentDevelopmentProcessesFragment fragment = new RecentDevelopmentProcessesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, developmentProcessAndItsActivities.getTitle());
        args.putString(ARG_DESCRIPTION, developmentProcessAndItsActivities.getDescription());
        args.putString(ARG_DURATION, developmentProcessAndItsActivities.getDuration());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mDescription = getArguments().getString(ARG_DESCRIPTION);
            mDuration = getArguments().getString(ARG_DURATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_development_processes, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
