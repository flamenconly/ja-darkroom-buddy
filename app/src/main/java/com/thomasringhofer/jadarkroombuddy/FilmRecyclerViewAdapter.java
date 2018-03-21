package com.thomasringhofer.jadarkroombuddy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thomasringhofer.jadarkroombuddy.entities.Film;

import java.util.List;

/**
 * Created by Thomas on 21.03.2018.
 */
public class FilmRecyclerViewAdapter extends RecyclerView.Adapter<FilmRecyclerViewAdapter.FilmRecyclerViewHolder> {

    public FilmRecyclerViewAdapter(FilmFragment.OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public List<Film> getItems() {
        return items;
    }

    public void setItems(List<Film> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public FilmRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list_item,parent,false);
        return new FilmRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmRecyclerViewAdapter.FilmRecyclerViewHolder holder, int position) {

        final Film currentFilm = items.get(position);

        holder.getTitleView().setText(currentFilm.toString());
        holder.getDescriptionView().setText(currentFilm.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener !=null){
                    mListener.onFragmentInteraction(currentFilm);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(items!=null) return items.size();
        else return 0;
    }

    private List<Film> items;
    private final FilmFragment.OnFragmentInteractionListener mListener;

    public class FilmRecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView getTitleView() {
            return titleView;
        }

        public TextView getDescriptionView() {
            return descriptionView;
        }

        private TextView titleView;

        private TextView descriptionView;

        public FilmRecyclerViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.film_name);
            descriptionView = itemView.findViewById(R.id.film_description);

        }
    }
}
