package net.osmtracker.layoutsdesigner.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.osmtracker.layoutsdesigner.R;
import net.osmtracker.layoutsdesigner.utils.ItemListMain;

import java.util.ArrayList;

public class CustomMainRecyclerAdapter extends RecyclerView.Adapter<CustomMainRecyclerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ItemListMain> items;
    private OnCustomLayoutListener customLayoutListener;

    public CustomMainRecyclerAdapter(Context context, ArrayList<ItemListMain> items, OnCustomLayoutListener customLayoutLister){
        this.context = context;
        this.items = items;
        this.customLayoutListener = customLayoutLister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_card_main_list, parent, false);
        //create a new holder to instance the views inside the layout inflated (To do the process only once)
        return new ViewHolder(view, customLayoutListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCustomLayoutName.setText(items.get(position).getLayoutCreatedName());
        holder.txtCustomLayoutDescription.setText(items.get(position).getLayoutCreatedDescription());
    }

    @Override
    public int getItemCount() {
        if(items.isEmpty())
            return 0;
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtCustomLayoutName;
        private TextView txtCustomLayoutDescription;
        private OnCustomLayoutListener listener;

        public ViewHolder(View itemView, OnCustomLayoutListener listener) {
            super(itemView);

            this.listener = listener;

            txtCustomLayoutName = itemView.findViewById(R.id.item_layout_name);
            txtCustomLayoutDescription = itemView.findViewById(R.id.item_layout_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCustomLayoutClick(getAdapterPosition());
        }
    }

    /**
     * Make an interface to do the listener connection between the class that implements the adapter and the item when the user makes a click in one of the
     * recycler items
     */
    public interface OnCustomLayoutListener{
        void onCustomLayoutClick(int position);
    }
}
