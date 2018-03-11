package com.thomasringhofer.jadarkroombuddy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.model.DevelopmentProcessAndItsActivities;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecentDevelopmentProcessesFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecentDevelopmentProcessesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentDevelopmentProcessesFragment extends Fragment {

    private RecyclerView recyclerView;

    private OnListFragmentInteractionListener mListener;

    public RecentDevelopmentProcessesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param
     * @return A new instance of fragment RecentDevelopmentProcessesFragment.
     */
    public static RecentDevelopmentProcessesFragment newInstance() {
        RecentDevelopmentProcessesFragment fragment = new RecentDevelopmentProcessesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_developmentprocess_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(new MyDevelopmentProcessRecyclerViewAdapter(mListener));
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            new Thread(new LoadAllDevelopmentProcessTask(recyclerView)).start();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new LoadAllDevelopmentProcessTask(recyclerView)).start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentListInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        void onFragmentInteraction(DevelopmentProcessAndItsActivities developmentProcessAndItsActivities);
    }

    private class LoadAllDevelopmentProcessTask implements Runnable{

        private final RecyclerView recyclerView;

        public LoadAllDevelopmentProcessTask(final RecyclerView recyclerView){
            if(recyclerView==null) throw new IllegalArgumentException();
            this.recyclerView = recyclerView;
        }

        @Override
        public void run() {

            final List<DevelopmentProcessAndItsActivities> items = AppDatabase.GetInstance().getRecentProcessAndItsActivities();

            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    ((MyDevelopmentProcessRecyclerViewAdapter)recyclerView.getAdapter()).setItems(items);
                }
            });
        }
    }
}
