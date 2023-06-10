package fr.eg23.wargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    private Zone zone;
    private ImageView imageview;
    private Player player;
    private DeployAdapter unDeployedAdapter;
    private DeployAdapter deployedAdapter;
    private RecyclerView deployedView;
    private RecyclerView unDeployedView;
    private List<Soldier> deployedSoldiers;
    private List<Soldier> unDeployedSoldiers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Log.d("activity5", "onCreate");
        Intent intent = getIntent();
        zone = (Zone) intent.getSerializableExtra("zone");
        player = (Player) intent.getSerializableExtra("player");
        Log.d("zone", zone.toString());

        imageview = findViewById(R.id.zone);
        deployedView = findViewById(R.id.deployList);
        unDeployedView = findViewById(R.id.soldierList);


        unDeployedSoldiers = getDeployableSoldiers(player);
        unDeployedAdapter = new DeployAdapter(unDeployedSoldiers);
        unDeployedView.setAdapter(unDeployedAdapter);
        unDeployedView.setLayoutManager(new LinearLayoutManager(this));

        deployedSoldiers = player.getDeployment().getSoldiersByArea(zone);
        deployedAdapter = new DeployAdapter(deployedSoldiers);
        deployedView.setAdapter(deployedAdapter);
        deployedView.setLayoutManager(new LinearLayoutManager(this));

        switch (zone){
            case gym:
                imageview.setImageResource(R.drawable.gym);
                break;
            case bde:
                imageview.setImageResource(R.drawable.bde);
                break;
            case library:
                imageview.setImageResource(R.drawable.library);
                break;
            case admin:
                imageview.setImageResource(R.drawable.admin);
                break;
            case industry:
                imageview.setImageResource(R.drawable.industry);
                break;
            default:
                break;
        }

        unDeployedAdapter.setOnClickListener(new DeployAdapter.OnClickListener(){
            @Override
            public void onClick(int position, Soldier soldier) {
                Log.d("soldier", soldier.toString());
                unDeployedSoldiers.remove(soldier);
                unDeployedAdapter.notifyItemRemoved(position);
                deployedSoldiers.add(soldier);
//                deployedAdapter.notifyItemInserted(deployedSoldiers.size());
                deployedSoldiers = sortSoldierList(deployedSoldiers);
                deployedAdapter.notifyDataSetChanged();



            }
        });
        deployedAdapter.setOnClickListener(new DeployAdapter.OnClickListener(){
            @Override
            public void onClick(int position, Soldier soldier) {
                Log.d("soldier", soldier.toString());
                deployedSoldiers.remove(soldier);
                deployedAdapter.notifyItemRemoved(position);
                unDeployedSoldiers.add(soldier);
//                unDeployedAdapter.notifyItemInserted(unDeployedSoldiers.size());
                unDeployedSoldiers = sortSoldierList(unDeployedSoldiers);
                unDeployedAdapter.notifyDataSetChanged();


            }
        });
        int a = Game.round;
    }

    public List<Soldier> sortSoldierList(List<Soldier> soldiers){
        soldiers.sort(Comparator.comparing(Soldier::getId)
                .thenComparing(Soldier::getRank));
        return soldiers;
    }

    public List<Soldier> getDeployableSoldiers(Player player){
        List<Soldier> soldiers = new ArrayList<>();
        if(Game.round == 0){
//            for(Soldier soldier: player.getPrivates()){
//                if(!soldier.isReservist()){
//                    soldiers.add(soldier);
//                }
//            }
//            for(Soldier soldier: player.getElites()){
//                if(!soldier.isReservist()){
//                    soldiers.add(soldier);
//                }
//            }
//            for(Soldier soldier: player.getMaitres()){
//                if(!soldier.isReservist()){
//                    soldiers.add(soldier);
//                }
//            }
            for(Soldier soldier : player.getAllSoldiers()){
                if(!soldier.isReservist()){
                    soldiers.add(soldier);
                }
            }
        }
        else{
            soldiers = player.getAllSoldiers();
        }
        return soldiers;
    }

    public void onFinishClick(View view){
        player.getDeployment().addSoldier(zone, deployedSoldiers);
        player.setAllSoldiers(unDeployedSoldiers);
        Intent intent = new Intent();
//        intent.putExtra("deployment", (Serializable) deployedSoldiers);
        intent.putExtra("player", player);
        intent.putExtra("zone", zone);
        setResult(RESULT_OK, intent);
        finish();
    }

}