package com.dsc.inventorymanagement.adapterClasses;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dsc.inventorymanagement.MainActivity;
import com.dsc.inventorymanagement.R;
import com.dsc.inventorymanagement.dataStorers.Items;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Items>{
    private Context context;
    private int layoutResId;
    private ArrayList<Items> items;

    public CustomAdapter(Context context,int layoutResId,ArrayList<Items> items){
        super(context,layoutResId,items);
        this.context=context;
        this.layoutResId=layoutResId;
        this.items=items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.name = (TextView) convertView.findViewById(R.id.product_name);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.quantity);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Items item = items.get(position);
        viewHolder.img.setImageURI(Uri.parse(item.getImage()));
        viewHolder.name.setText(item.getName());
        viewHolder.price.setText(item.getPrice());
        viewHolder.quantity.setText(item.getQuantity());

        final long id = item.getId();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.clicked(id);
            }
        });
        return convertView;
    }

    public class ViewHolder{
        ImageView img;
        TextView name;
        TextView price;
        TextView quantity;

    }
}
