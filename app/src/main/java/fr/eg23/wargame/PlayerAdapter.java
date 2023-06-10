package fr.eg23.wargame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlayerAdapter extends BaseAdapter {
    private Context mContext;
    private List<Player> mPlayers;
    private LayoutInflater inflater;
    public PlayerAdapter(Context context, List<Player> players) {
        mContext = context;
        mPlayers = players;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return mPlayers.size();
    }

    @Override
    public Object getItem(int position) {

        return mPlayers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.player_item, null);
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView);
        name.setText(mPlayers.get(position).getName());
        icon.setImageResource(R.drawable.player_icon);
        return convertView;

    }
}
