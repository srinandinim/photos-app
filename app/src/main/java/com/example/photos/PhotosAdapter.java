package com.example.photos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhotosAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Photo> photos;

    public PhotosAdapter(Context context, List<Photo> photos) {
        this.mContext = context;
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Photo photo = photos.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_photo, null);
        }

        ImageView photoImage = convertView.findViewById(R.id.photoImage);
        TextView photoName = convertView.findViewById(R.id.photoName);
        ImageButton delete = convertView.findViewById(R.id.deleteButton);

        photoImage.setImageURI(photo.getUri()); //TODO: Scale image
        photoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                intent.putExtra("CurrentPhoto", photo);
                mContext.startActivity(intent);
            }
        });

        photoName.setText(photo.getName());

        final int arrPosition = position;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photos.remove(arrPosition);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
