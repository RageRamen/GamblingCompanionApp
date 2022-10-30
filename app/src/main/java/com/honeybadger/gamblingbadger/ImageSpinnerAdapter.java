package com.honeybadger.gamblingbadger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Map;

public class ImageSpinnerAdapter extends BaseAdapter {

    LayoutInflater inflater;
    private final Integer[] imageIds;

    public ImageSpinnerAdapter(Context context, Integer[] imageIds) {
        this.imageIds = imageIds;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.image_spinner_layout, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.imageSpinnerLayout_imageView);
        image.setImageResource(imageIds[position]);

        return convertView;
    }

}
