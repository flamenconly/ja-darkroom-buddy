package com.thomasringhofer.jadarkroombuddy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.database.DevelopmentProcessDao;
import com.thomasringhofer.jadarkroombuddy.databinding.DevelopmentProcessItemBinding;
import com.thomasringhofer.jadarkroombuddy.dummy.DummyContent;
import com.thomasringhofer.jadarkroombuddy.dummy.DummyContent.DummyItem;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DevelopmentProcessFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DevelopmentProcessFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DevelopmentProcessFragment newInstance(int columnCount) {
        DevelopmentProcessFragment fragment = new DevelopmentProcessFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_developmentprocess_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            new Thread(new LoadAllDevelopmentProcessTask(recyclerView)).start();
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DevelopmentProcess item);
    }

    private class LoadAllDevelopmentProcessTask implements Runnable{

        private final RecyclerView recyclerView;

        public LoadAllDevelopmentProcessTask(final RecyclerView recyclerView){
            if(recyclerView==null) throw new IllegalArgumentException();
            this.recyclerView = recyclerView;
        }

        @Override
        public void run() {

            DevelopmentProcessDao dao = AppDatabase.GetInstance().developmentProcessDao();
            final List<DevelopmentProcess> items = new ArrayList<>();
            if(Collections.addAll(items, dao.LoadAllProcesses())==false){
                throw new IllegalStateException("Not all data loaded.");
            }

            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setAdapter(new MyDevelopmentProcessRecyclerViewAdapter(items, mListener));
                }
            });

            dao = null;
        }
    }
}
