package fr.eg23.wargame;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    private ActivityResultLauncher launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Log.d("Return", "Return from activity 5");
                    Player player = (Player) result.getData().getSerializableExtra("player");
                    Zone zone = (Zone) result.getData().getSerializableExtra("zone");
                    switch (player.getId()){
                        case 0:
                            players.set(0, player);
                            selectedPlayer = player;
                            break;
                        case 1:
                            players.set(1, player);
                            selectedPlayer = player;
                            break;
                        default:
                            break;
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
        //initialize game
        Game game = new Game();

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
        Intent intent = new Intent(this,MainActivity5.class);
        intent.putExtra("zone", Zone.gym);
        intent.putExtra("player", selectedPlayer);
//        startActivity(intent);
        launcher.launch(intent);

    }
    public void onBdeClick(View view){
        Intent intent = new Intent(this,MainActivity5.class);
        intent.putExtra("zone", Zone.bde);
        intent.putExtra("player", selectedPlayer);
        //        startActivity(intent);
        launcher.launch(intent);

    }
    public void onLibraryClick(View view){
        Intent intent = new Intent(this,MainActivity5.class);
        intent.putExtra("zone", Zone.library);
        intent.putExtra("player", selectedPlayer);
        //        startActivity(intent);
        launcher.launch(intent);

    }
    public void onAdminClick(View view){
        Intent intent = new Intent(this,MainActivity5.class);
        intent.putExtra("zone", Zone.admin);
        intent.putExtra("player", selectedPlayer);
        //        startActivity(intent);
        launcher.launch(intent);

    }
    public void onIndustryClick(View view){
        Intent intent = new Intent(this,MainActivity5.class);
        intent.putExtra("zone", Zone.industry);
        intent.putExtra("player", selectedPlayer);
        //        startActivity(intent);
        launcher.launch(intent);

    }

    public void onBattleClick(View view){
        presetDeployment();
        if(players.get(0).getDeployment().getDeployedZones() == 5 && players.get(1).getDeployment().getDeployedZones() == 5){
            //go to battle
//            Toast.makeText(this,"你选择的是:",Toast.LENGTH_SHORT).show();
            Log.d("soldier in gym", String.valueOf(players.get(0).getDeployment().getSoldiersByArea(Zone.gym).size()));
            Intent intent = new Intent(this, MainActivity6.class);
            intent.putExtra("player1", players.get(0));
            intent.putExtra("player2", players.get(1));
            startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();
//        selectedPlayer = players.get(0);
        Log.d("resume", "Resume");
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