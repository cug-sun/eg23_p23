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

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView27);
        }
    }

    private Zone[] zones;
    private ZoneAdapter.OnClickListener onClickListener;

    public ZoneAdapter(Zone[] zones){
        this.zones = zones;
    }
    @NonNull
    @Override
    public ZoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View soldierView = inflater.inflate(R.layout.zone_item, parent, false);
        ZoneAdapter.ViewHolder viewHolder = new ZoneAdapter.ViewHolder(soldierView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneAdapter.ViewHolder holder, int position) {
        Zone zone = zones[position];
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
        return zones.length;
    }

    public void setOnClickListener(ZoneAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Zone zone);
    }
}
