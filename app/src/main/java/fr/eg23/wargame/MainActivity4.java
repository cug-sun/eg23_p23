package fr.eg23.wargame;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    private Player selectedPlayer;
    private List<Player> players;
    private Deployment deployment1;
    private Deployment deployment2;
    private Spinner spinner;
    private ActivityResultLauncher launcher5;
    private ActivityResultLauncher launcher6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        launcher5 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    int from = result.getData().getIntExtra("from", -1);
                    if (from == 5) {
                        Log.d("Return", "Return from activity 5");
                        Player player = (Player) result.getData().getSerializableExtra("player");
                        Zone zone = (Zone) result.getData().getSerializableExtra("zone");
                        switch (player.getId()){
                            case 0:
                                players.set(0, player);
                                selectedPlayer = player;
                                spinner.setSelection(0, true);
                                break;
                            case 1:
                                players.set(1, player);
                                selectedPlayer = player;
                                spinner.setSelection(1, true);
                                break;
                            default:
                                break;
                        }
                    }

                }
            }
        });

        launcher6 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Log.d("Return", "Return from activity 6");
                    int from = result.getData().getIntExtra("from", -1);
                    if (from == 6) {
                        Log.d("Return", "Return from activity 6");
                        Player player1 = (Player) result.getData().getSerializableExtra("player1");
                        Player player2 = (Player) result.getData().getSerializableExtra("player2");
                        players.set(0, player1);
                        players.set(1, player2);
                        selectedPlayer = player1;
                        spinner.setSelection(0, true);
                    }
                }
            }
        });

        Intent intent = getIntent();
        Player player1 = (Player) intent.getSerializableExtra("player1");
        Player player2 = (Player) intent.getSerializableExtra("player2");
        Log.d("activity4", "call onCreate");
        players = new ArrayList<>();
        deployment1 = new Deployment();
        deployment2 = new Deployment();
        player1.setDeployment(deployment1);
        player2.setDeployment(deployment2);
        players.add(player1);
        players.add(player2);
        Log.d("player1", player1.getName());
        //default selected player is 1
        selectedPlayer = player1;

        spinner = (Spinner) findViewById(R.id.spinner2);

        PlayerAdapter playerAdapter = new PlayerAdapter(getApplicationContext(), players);
        spinner.setAdapter(playerAdapter);
        spinner.setSelection(0, true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position", String.valueOf(position));
                selectedPlayer = players.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onGymClick(View view){
        if(isBattleFinished(Zone.gym)){
            Intent intent = new Intent(this,MainActivity5.class);
            intent.putExtra("zone", Zone.gym);
            intent.putExtra("player", selectedPlayer);
            launcher5.launch(intent);
        }else {
            Toast.makeText(this, "Le combat n’est pas fini ici", Toast.LENGTH_LONG).show();
        }


    }
    public void onBdeClick(View view){
        if (isBattleFinished(Zone.bde)) {
            Intent intent = new Intent(this, MainActivity5.class);
            intent.putExtra("zone", Zone.bde);
            intent.putExtra("player", selectedPlayer);
            launcher5.launch(intent);
        } else {
            Toast.makeText(this, "Le combat n’est pas fini ici", Toast.LENGTH_LONG).show();
        }
    }
    public void onLibraryClick(View view){
        if(isBattleFinished(Zone.library)) {
            Intent intent = new Intent(this, MainActivity5.class);
            intent.putExtra("zone", Zone.library);
            intent.putExtra("player", selectedPlayer);
            launcher5.launch(intent);
        }else {
            Toast.makeText(this, "Le combat n’est pas fini ici", Toast.LENGTH_LONG).show();
        }
    }
    public void onAdminClick(View view){
        if(isBattleFinished(Zone.admin)) {
            Intent intent = new Intent(this, MainActivity5.class);
            intent.putExtra("zone", Zone.admin);
            intent.putExtra("player", selectedPlayer);
            launcher5.launch(intent);
        }else {
            Toast.makeText(this, "Le combat n’est pas fini ici", Toast.LENGTH_LONG).show();
        }
    }
    public void onIndustryClick(View view){
        if (isBattleFinished(Zone.industry)) {
            Intent intent = new Intent(this, MainActivity5.class);
            intent.putExtra("zone", Zone.industry);
            intent.putExtra("player", selectedPlayer);
            launcher5.launch(intent);
        } else {
            Toast.makeText(this, "Le combat n’est pas fini ici", Toast.LENGTH_LONG).show();
        }
    }

    public void onBattleClick(View view){
//        presetDeployment();
        if(players.get(0).getDeployment().isAllDeployed() && players.get(1).getDeployment().isAllDeployed()){
            //go to battle
            Log.d("soldier in gym", String.valueOf(players.get(0).getDeployment().getSoldiersByArea(Zone.gym).size()));
            Intent intent = new Intent(this, MainActivity6.class);
            intent.putExtra("player1", players.get(0));
            intent.putExtra("player2", players.get(1));
            launcher6.launch(intent);
        }
        else{
            //alert
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle);
            builder.setTitle("Attention");
            builder.setMessage("Il faut affecter au moins 1 combattant à chaque zone");
            builder.setIcon(R.drawable.common_soldier);
            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }

    public boolean isBattleFinished(Zone zone){
        if(Game.round == 0){
            return true;
        }else {
            if(players.get(0).getOccupation().contains(zone) || players.get(1).getOccupation().contains(zone)){
                return true;
            }
            else{
                return false;
            }
        }
    }

    //TODO preset the deployment, only use for debug
    public void presetDeployment(){
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        List<Soldier> soldiers = new ArrayList<>();
        for(Zone zone: Zone.values()){
            soldiers.clear();
            soldiers.addAll(player1.getAllSoldiers().subList(0, 2));
            player1.getDeployment().addSoldier(zone, soldiers);
            soldiers.clear();
            soldiers.addAll(player2.getAllSoldiers().subList(0, 2));
            player2.getDeployment().addSoldier(zone, soldiers);
        }
    }
}