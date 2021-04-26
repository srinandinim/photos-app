package com.example.photos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class AlbumsAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Album> albums;

    public AlbumsAdapter(Context context, List<Album> albums) {
        this.mContext = context;
        this.albums = albums;
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Album album = albums.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_album, null);
        }

        ImageView albumCover = convertView.findViewById(R.id.albumCover);
        TextView albumName = convertView.findViewById(R.id.albumName);
        ImageButton rename = convertView.findViewById(R.id.albumEdit);
        ImageButton delete = convertView.findViewById(R.id.albumDelete);

        if (album.getSize() == 0) {
            albumCover.setImageResource(R.drawable.noimageavailable);
            //TODO: fix image scaling
        } else {
            //TODO: image
        }

        albumCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlbumActivity.class);
                intent.putExtra("CurrentAlbum", (Serializable) album);
                mContext.startActivity(intent);
            }
        });

        albumName.setText(album.getName());

        rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(mContext);

                AlertDialog dialog = (new AlertDialog.Builder(mContext))
                        .setTitle("Rename Album")
                        .setMessage("\nAlbum Name:")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (!containsAlbum(input.getText().toString().trim())) {
                                    album.setName(input.getText().toString());
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "Invalid Album Name", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();

                dialog.setView(input, 40, 0, 40, 0);
                dialog.show();
            }
        });

        final int arrPosition = position;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                albums.remove(arrPosition);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private boolean containsAlbum(String name) {
        if (name == null || name.isEmpty())
            return true;

        for (Album currAlbum : albums) {
            if (currAlbum.getName().toLowerCase().equals(name.toLowerCase()))
                return true;
        }

        return false;
    }


}
