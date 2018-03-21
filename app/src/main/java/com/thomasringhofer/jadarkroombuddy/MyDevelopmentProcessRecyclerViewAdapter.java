package com.thomasringhofer.jadarkroombuddy;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thomasringhofer.jadarkroombuddy.RecentDevelopmentProcessesFragment.OnListFragmentInteractionListener;
import com.thomasringhofer.jadarkroombuddy.model.DevelopmentProcessAndItsActivities;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link com.thomasringhofer.jadarkroombuddy.model.DevelopmentProcessAndItsActivities} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyDevelopmentProcessRecyclerViewAdapter extends RecyclerView.Adapter<MyDevelopmentProcessRecyclerViewAdapter.ViewHolder> {

    public List<DevelopmentProcessAndItsActivities> getItems() {
        return items;
    }

    public void setItems(List<DevelopmentProcessAndItsActivities> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    private List<DevelopmentProcessAndItsActivities> items;

    private final OnListFragmentInteractionListener mListener;

    public MyDevelopmentProcessRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.development_process_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setProcess(items.get(position));

        holder.durationView.setText(holder.getProcess().getFilm());
        holder.titleView.setText(holder.getProcess().getTitle());
        holder.descriptionView.setText(holder.getProcess().getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFragmentInteraction(holder.getProcess());
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        if(items!=null) return items.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;

        public TextView titleView;
        public TextView descriptionView;
        public TextView durationView;

        public DevelopmentProcessAndItsActivities getProcess() {
            return process;
        }

        public void setProcess(DevelopmentProcessAndItsActivities mItem) {
            this.process = mItem;
        }

        public DevelopmentProcessAndItsActivities process;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleView = mView.findViewById(R.id.development_title);
            descriptionView = mView.findViewById(R.id.development_description);
            durationView = mView.findViewById(R.id.development_duration);
        }
    }
}
