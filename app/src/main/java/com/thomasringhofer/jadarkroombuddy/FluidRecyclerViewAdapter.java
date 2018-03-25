package com.thomasringhofer.jadarkroombuddy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thomasringhofer.jadarkroombuddy.FluidFragment.OnListFragmentInteractionListener;
import com.thomasringhofer.jadarkroombuddy.dummy.DummyContent.DummyItem;
import com.thomasringhofer.jadarkroombuddy.entities.Fluid;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FluidRecyclerViewAdapter extends RecyclerView.Adapter<FluidRecyclerViewAdapter.ViewHolder> {

    private List<Fluid> items;
    private final OnListFragmentInteractionListener mListener;


    public FluidRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fluid_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setItem(items.get(position));
        holder.getNameTextView().setText(items.get(position).getTitle());
        holder.getTypeTextView().setText(items.get(position).getType());
        holder.getColorTypeTextView().setText(items.get(position).getColorType());

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.getItem(),false);
                }
            }
        });

        holder.parentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(null != mListener){
                    mListener.onListFragmentInteraction(holder.getItem(),true);
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        if(items==null)return 0;
        else return items.size();
    }

    public List<Fluid> getItems(){
        return items;
    }

    public void setItems(List<Fluid> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View parentView;
        private final TextView nameTextView;
        private final TextView typeTextView;
        private final TextView colorTypeTextView;

        public Fluid getItem() {
            return item;
        }

        public void setItem(Fluid item) {
            this.item = item;
        }

        private Fluid item;

        public View getParentView() {
            return parentView;
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getTypeTextView() {
            return typeTextView;
        }

        public TextView getColorTypeTextView() {
            return colorTypeTextView;
        }


        public ViewHolder(View view) {
            super(view);
            parentView = view;
            nameTextView = view.findViewById(R.id.fluid_name);
            typeTextView = view.findViewById(R.id.fluid_type);
            colorTypeTextView = view.findViewById(R.id.fluid_color_type);
        }

    }
}
