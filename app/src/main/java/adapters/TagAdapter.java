package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.photos.R;

import java.util.List;

import models.Tag;

public class TagAdapter extends ArrayAdapter<Tag> {

    Context mContext;
    List<Tag> tags;

    public TagAdapter(Context context, List<Tag> tags) {
        super(context, R.layout.linearlayout_tag, tags);
        this.mContext = context;
        this.tags = tags;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tag tag = tags.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_tag, null);
        }

        TextView tagVal = convertView.findViewById(R.id.tagTag);
        TextView valueVal = convertView.findViewById(R.id.tagVal);
        ImageButton delete = convertView.findViewById(R.id.tagDel);

        tagVal.setText(tag.getKey());
        valueVal.setText(tag.getValue());

        final int arrPosition = position;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tags.remove(arrPosition);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
