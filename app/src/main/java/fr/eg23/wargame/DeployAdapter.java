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

public class DeployAdapter extends RecyclerView.Adapter<DeployAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public TextView force;
        public TextView dexterity;
        public TextView resistance;
        public TextView constitution;
        public TextView initiative;
        public TextView ai;
        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            force = itemView.findViewById(R.id.textView9);
            dexterity = itemView.findViewById(R.id.textView10);
            resistance = itemView.findViewById(R.id.textView11);
            constitution = itemView.findViewById(R.id.textView12);
            initiative = itemView.findViewById(R.id.textView13);
            ai = itemView.findViewById(R.id.ai);
        }
    }

    private List<Soldier> mSoldiers;
    private DeployAdapter.OnClickListener onClickListener;

    public DeployAdapter(List<Soldier> soldiers){
        mSoldiers = soldiers;
    }
    @NonNull
    @Override
    public DeployAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View soldierView = inflater.inflate(R.layout.deploy_item, parent, false);
        DeployAdapter.ViewHolder viewHolder = new DeployAdapter.ViewHolder(soldierView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeployAdapter.ViewHolder holder, int position) {
        Soldier soldier = mSoldiers.get(position);
        TextView textView = holder.textView;
        textView.setText(soldier.toString());
        ImageView imageView = holder.imageView;
        imageView.setImageResource(R.drawable.common_soldier);
        TextView force = holder.force;
        TextView dexterity = holder.dexterity;
        TextView resistance = holder.resistance;
        TextView constitution = holder.constitution;
        TextView initiative = holder.initiative;
        TextView ai = holder.ai;
        force.setText(String.valueOf(soldier.getForce()));
        dexterity.setText(String.valueOf(soldier.getDexterity()));
        resistance.setText(String.valueOf(soldier.getResistance()));
        constitution.setText(String.valueOf(soldier.getConstitution()));
        initiative.setText(String.valueOf(soldier.getInitiative()));
        ai.setText(String.valueOf(soldier.getAi().toString().charAt(0)));

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

    public void setOnClickListener(DeployAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Soldier soldier);
    }
}
