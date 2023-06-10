package fr.eg23.wargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
//    private ListView listView;
    private RecyclerView recyclerView;
    private Spinner spinner;
    private Player player1;
    private Player player2;
    private List<Player> players = new ArrayList<>();
    private SeekBar force;
    private TextView forceText;
    private SeekBar dexterity;
    private TextView dexterityText;
    private SeekBar resistance;
    private TextView resistanceText;
    private SeekBar constitution;
    private TextView constitutionText;
    private SeekBar initiative;
    private TextView initiativeText;
    private Soldier selectedSoldier;
    private Player selectedPlayer;
    private SoldiersAdapter soldiersAdapter;
    private int previousProgress;
    private TextView pointView;
    private TextView soldierText;
    private ImageButton continueButton;
    private CheckBox reservist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        spinner = (Spinner) findViewById(R.id.spinner);
        force = (SeekBar) findViewById(R.id.force);
        forceText = (TextView) findViewById(R.id.forceText);
        dexterity = (SeekBar) findViewById(R.id.dexterity);
        dexterityText = (TextView) findViewById(R.id.dexterityText);
        resistance = (SeekBar) findViewById(R.id.resistance);
        resistanceText = (TextView) findViewById(R.id.resistanceText);
        constitution = (SeekBar) findViewById(R.id.constitution);
        constitutionText = (TextView) findViewById(R.id.constitutionText);
        initiative = (SeekBar) findViewById(R.id.initiative);
        initiativeText = (TextView) findViewById(R.id.initiativeText);
        pointView = (TextView) findViewById(R.id.point);
        soldierText = (TextView) findViewById(R.id.soldierText);
        continueButton = (ImageButton) findViewById(R.id.continueButton);
        reservist = findViewById(R.id.checkBox);



        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name1");
        String name2 = intent.getStringExtra("name2");
        player1 = new Player(name1, 0);
        player2 = new Player(name2, 1);
        players.add(player1);
        players.add(player2);
        selectedPlayer = player1;

        List<Soldier> soldiers = player1.getPrivates();
        soldiersAdapter = new SoldiersAdapter(soldiers);
        recyclerView.setAdapter(soldiersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //intent


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

        force.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                forceText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                previousProgress = seekBar.getProgress();
                Log.d("previous progress", String.valueOf(previousProgress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedSoldier != null){
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if(currentProgress > previousProgress){
                        if(difference <= point){
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setForce(currentProgress);
                        }
                        else{
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    }
                    else{
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setForce(currentProgress);
                    }
                    pointView.setText(String.valueOf(selectedPlayer.getPoint()));
                }

            }
        });

        dexterity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dexterityText.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                previousProgress = seekBar.getProgress();
                Log.d("previous progress", String.valueOf(previousProgress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedSoldier != null){
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if(currentProgress > previousProgress){
                        if(difference <= point){
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setDexterity(currentProgress);
                        }
                        else{
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    }
                    else{
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setDexterity(currentProgress);
                    }
                    pointView.setText(String.valueOf(selectedPlayer.getPoint()));
                }
            }
        });

        resistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                resistanceText.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                previousProgress = seekBar.getProgress();
                Log.d("previous progress", String.valueOf(previousProgress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedSoldier != null){
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if(currentProgress > previousProgress){
                        if(difference <= point){
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setResistance(currentProgress);
                        }
                        else{
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    }
                    else{
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setResistance(currentProgress);
                    }
                    pointView.setText(String.valueOf(selectedPlayer.getPoint()));
                }
            }
        });

        constitution.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                constitutionText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                previousProgress = seekBar.getProgress();
                Log.d("previous progress", String.valueOf(previousProgress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedSoldier != null){
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if(currentProgress > previousProgress){
                        if(difference <= point){
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setConstitution(currentProgress);
                        }
                        else{
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    }
                    else{
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setConstitution(currentProgress);
                    }
                    pointView.setText(String.valueOf(selectedPlayer.getPoint()));
                }
            }
        });

        initiative.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                initiativeText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                previousProgress = seekBar.getProgress();
                Log.d("previous progress", String.valueOf(previousProgress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedSoldier != null){
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if(currentProgress > previousProgress){
                        if(difference <= point){
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setInitiative(currentProgress);
                        }
                        else{
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    }
                    else{
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setInitiative(currentProgress);
                    }
                    pointView.setText(String.valueOf(selectedPlayer.getPoint()));
                }
            }
        });

        reservist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedSoldier != null){
                    if(!reservist.isSelected()){
                        selectedSoldier.setReservist(true);
                    }
                    else{
                        selectedSoldier.setReservist(true);
                    }
                }
            }
        });

        setListener(soldiersAdapter);
    }


    public void onEliteClick(View view){
        soldiersAdapter = new SoldiersAdapter(player1.getElites());
        recyclerView.setAdapter(soldiersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListener(soldiersAdapter);
        selectedSoldier = null;
    }
    public void onMaitreClick(View view) {
        soldiersAdapter = new SoldiersAdapter(player1.getMaitres());
        recyclerView.setAdapter(soldiersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListener(soldiersAdapter);
        selectedSoldier = null;
    }

    public void onPrivateClick(View view){
        soldiersAdapter = new SoldiersAdapter(player1.getPrivates());
        recyclerView.setAdapter(soldiersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListener(soldiersAdapter);
        selectedSoldier = null;
    }

    public void setListener(SoldiersAdapter adapter){
        adapter.setOnClickListener(new SoldiersAdapter.OnClickListener(){
            @Override
            public void onClick(int position, Soldier soldier) {
                Log.d("soldier", soldier.toString());
                selectedSoldier = soldier;
                soldierText.setText(soldier.toString());
                reservist.setChecked(selectedSoldier.isReservist());
                setSeekbars(soldier);
                Log.d("Attributes", String.format("F:%d D: %d R: %d C: %d I: %d AI: %b",
                        soldier.getForce(),soldier.getDexterity(),soldier.getResistance(),soldier.getConstitution(), soldier.getInitiative(), soldier.isReservist()));
            }
        });
    }

    public void setSeekbars(Soldier soldier){
        force.setProgress(soldier.getForce());
        dexterity.setProgress(soldier.getDexterity());
        resistance.setProgress(soldier.getResistance());
        constitution.setProgress(soldier.getConstitution());
        initiative.setProgress(soldier.getInitiative());
        //set min value of seekbar depending on soldier's pre-assigned attributes
        switch (soldier.rank){
            case PRIVATE:
                force.setMin(0);
                dexterity.setMin(0);
                resistance.setMin(0);
                constitution.setMin(0);
                initiative.setMin(0);
                break;
            case ELIITE:
                force.setMin(1);
                dexterity.setMin(1);
                resistance.setMin(1);
                constitution.setMin(5);
                initiative.setMin(1);
                break;
            case MAITREDEGUERRE:
                force.setMin(2);
                dexterity.setMin(2);
                resistance.setMin(2);
                constitution.setMin(10);
                initiative.setMin(2);
                break;
            default:
                break;
        }
    }

    public void onContinueClick(View view){
        Intent intent = new Intent(this,MainActivity4.class);
        intent.putExtra("player1", player1);
        intent.putExtra("player2", player2);
        startActivity(intent);
    }
}