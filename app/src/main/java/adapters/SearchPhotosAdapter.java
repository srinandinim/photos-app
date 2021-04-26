package adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photos.R;

import java.util.List;

import models.Photo;

public class SearchPhotosAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Photo> photos;

    public SearchPhotosAdapter(Context context, List<Photo> photos) {
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
            convertView = layoutInflater.inflate(R.layout.linearlayout_searchphoto, null);
        }

        ImageView photoImage = convertView.findViewById(R.id.photoImage);
        TextView photoName = convertView.findViewById(R.id.photoName);

        photoImage.setImageURI(Uri.parse(photo.getUriString())); //TODO: Scale image
        photoName.setText(photo.getName());

        return convertView;
    }
}
