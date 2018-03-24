package com.thomasringhofer.jadarkroombuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.entities.Fluid;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FluidFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FluidFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FluidFragment newInstance() {
        FluidFragment fragment = new FluidFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fluid_list, container, false);

        // Set the adapter

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.fluid_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new FluidRecyclerViewAdapter(mListener));

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButtonNewFluid);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),NewFluidActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {

        new Thread(new LoadAllFluidsTask(recyclerView)).start();

        super.onResume();
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
        void onListFragmentInteraction(Fluid item);
    }

    private final class LoadAllFluidsTask implements Runnable{

        private final RecyclerView recyclerView;

        public LoadAllFluidsTask(final RecyclerView recyclerView){
            this.recyclerView = recyclerView;
        }

        @Override
        public void run() {
            final List<Fluid> items = AppDatabase.GetInstance().fluidDao().getAll();

            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    ((FluidRecyclerViewAdapter)recyclerView.getAdapter()).setItems(items);
                }
            });
        }
    }
}
