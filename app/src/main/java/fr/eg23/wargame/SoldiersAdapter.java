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

public class SoldiersAdapter extends RecyclerView.Adapter<SoldiersAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    private List<Soldier> mSoldiers;
    private OnClickListener onClickListener;

    public SoldiersAdapter(List<Soldier> soldiers){
        mSoldiers = soldiers;
    }
    @NonNull
    @Override
    public SoldiersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View soldierView = inflater.inflate(R.layout.soldier_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(soldierView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SoldiersAdapter.ViewHolder holder, int position) {
        Soldier soldier = mSoldiers.get(position);
        TextView textView = holder.textView;
        textView.setText(soldier.toString());
        ImageView imageView = holder.imageView;
        imageView.setImageResource(R.drawable.common_soldier);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){
                    onClickListener.onClick(holder.getAdapterPosition(), soldier);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSoldiers.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Soldier soldier);
    }
}
