package net.osmtracker.layoutsdesigner.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.osmtracker.layoutsdesigner.R;
import net.osmtracker.layoutsdesigner.utils.LayoutButtonGridItem;
import net.osmtracker.layoutsdesigner.utils.xmlutils.ButtonXmlObject;

import java.util.ArrayList;

public class CustomGridViewManagerAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<ButtonXmlObject> buttonsArray;

    public CustomGridViewManagerAdapter(Activity activity, ArrayList<ButtonXmlObject> buttons){
        this.activity = activity;
        this.buttonsArray = buttons;
    }

    @Override
    public int getCount() {
        if(buttonsArray.isEmpty()){
            return 0;
        }
        return buttonsArray.size();
    }

    @Override
    public Object getItem(int i) {
        return buttonsArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_grid_item, parent, false);
            holder = new ViewHolder();
            holder.imgvIcon = convertView.findViewById(R.id.imv_item_grid_icon);
            holder.txtvLabel = convertView.findViewById(R.id.txt_item_grid_name);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        ButtonXmlObject itemXml = (ButtonXmlObject) getItem(i);
        LayoutButtonGridItem item = new LayoutButtonGridItem(itemXml, activity);

        //set the name of the current button
        holder.txtvLabel.setText(item.getItemName());

        ConstraintLayout constraintLayout = convertView.findViewById(R.id.parent_item_layout);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        //look if there is an image to set in the button
        if(item.getImageURI() != null){
            holder.imgvIcon.setImageURI(item.getImageURI());
        }
        else if(item.getDefaultIcon() != null){
            holder.imgvIcon.setImageDrawable(item.getDefaultIcon());
        }
        else{
            //in case there is only text, expand the TextView to math the parent and hide the ImageView
            constraintSet.connect(R.id.txt_layout_name, ConstraintSet.TOP, R.id.parent_item_layout, ConstraintSet.TOP, 6);
            constraintSet.applyTo(constraintLayout);
            holder.imgvIcon.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    public static class ViewHolder{
        private TextView txtvLabel;
        private ImageView imgvIcon;
    }
}
