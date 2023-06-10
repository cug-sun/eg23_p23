package fr.eg23.wargame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SoldierAdapter extends BaseAdapter {

    private List<Soldier> soldiers;
    private Context mContext;
    public SoldierAdapter(Context mContext, List<Soldier> data){
        super();
        this.mContext = mContext;
        this.soldiers = data;
    }
    @Override
    public int getCount() {
        return soldiers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.soldier_item,parent, false);
        TextView v = view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);

        TextView textView = new TextView(mContext);
        v.setText(soldiers.get(position).toString());
        return v;
    }
}
