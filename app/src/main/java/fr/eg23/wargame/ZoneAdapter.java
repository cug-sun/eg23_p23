package fr.eg23.wargame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Set;

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView27);
            imageView = itemView.findViewById((R.id.imageView7));
        }
    }

    private List<Zone> zones;
    private ZoneAdapter.OnClickListener onClickListener;

    public ZoneAdapter(List<Zone> zones){
        this.zones = zones;
    }
    @NonNull
    @Override
    public ZoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View zoneView = inflater.inflate(R.layout.zone_item, parent, false);
        ZoneAdapter.ViewHolder viewHolder = new ZoneAdapter.ViewHolder(zoneView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneAdapter.ViewHolder holder, int position) {
        Zone zone = zones.get(position);
        TextView textView = holder.textView;
        textView.setText(zone.toShortString());
        ImageView imageView = holder.imageView;
        int resId = 0;
        switch (zone){
            case gym:
                resId = R.drawable.gym;
                break;
            case bde:
                resId = R.drawable.bde;
                break;
            case library:
                resId = R.drawable.library;
                break;
            case admin:
                resId = R.drawable.admin;
                break;
            case industry:
                resId = R.drawable.industry;
                break;
            default:
                break;
        }
        imageView.setImageResource(resId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return zones.size();
    }

    public void setOnClickListener(ZoneAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Zone zone);
    }
}
