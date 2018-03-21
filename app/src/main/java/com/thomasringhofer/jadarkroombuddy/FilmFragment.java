package com.thomasringhofer.jadarkroombuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.entities.Film;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmFragment extends Fragment {

    private RecyclerView recyclerView;

    private OnFragmentInteractionListener mListener;

    public FilmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FilmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilmFragment newInstance() {
        FilmFragment fragment = new FilmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parent = container.findViewById(R.id.filmListContainer);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film_list, (ViewGroup)parent, false);

        if(view instanceof RecyclerView){
            Context context = view.getContext();

            recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(new FilmRecyclerViewAdapter(mListener));
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            new Thread(new LoadAllFilmsTask(recyclerView)).start();
        }

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Film film) {
        if (mListener != null) {
            mListener.onFragmentInteraction(film);
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
         void onFragmentInteraction(Film film);
    }

    private class LoadAllFilmsTask implements Runnable{

        public LoadAllFilmsTask(@NonNull RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        private final RecyclerView recyclerView;

        @Override
        public void run() {
            final List<Film> items = AppDatabase.GetInstance().getFilms();
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    ((FilmRecyclerViewAdapter)recyclerView.getAdapter()).setItems(items);
                }
            });
        }
    }
}
