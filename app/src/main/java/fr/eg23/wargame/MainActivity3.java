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
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView pointText;
    private TextView soldierText;
    private ImageButton continueButton;
    private CheckBox reservist;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.spinner);
        force = findViewById(R.id.force);
        forceText = findViewById(R.id.forceText);
        dexterity = findViewById(R.id.dexterity);
        dexterityText = findViewById(R.id.dexterityText);
        resistance = findViewById(R.id.resistance);
        resistanceText = findViewById(R.id.resistanceText);
        constitution = findViewById(R.id.constitution);
        constitutionText = findViewById(R.id.constitutionText);
        initiative = findViewById(R.id.initiative);
        initiativeText = findViewById(R.id.initiativeText);
        pointText = findViewById(R.id.point);
        soldierText = findViewById(R.id.soldierText);
        continueButton = findViewById(R.id.continueButton);
        reservist = findViewById(R.id.checkBox);
        radioGroup = findViewById(R.id.radioGroup);


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
        pointText.setText(String.valueOf(selectedPlayer.getPoint()));
        //intent


        PlayerAdapter playerAdapter = new PlayerAdapter(getApplicationContext(), players);
        spinner.setAdapter(playerAdapter);
        spinner.setSelection(0, true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("selected player", String.valueOf(position));
                selectedPlayer = players.get(position);
                soldiersAdapter = new SoldiersAdapter(selectedPlayer.getPrivates());
                recyclerView.setAdapter(soldiersAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                setListener(soldiersAdapter);
                pointText.setText(String.valueOf(selectedPlayer.getPoint()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (selectedSoldier != null) {
                    if (checkedId == R.id.radioButton) {
                        selectedSoldier.setAi(AiType.Defensive);
                    } else if (checkedId == R.id.radioButton2) {
                        selectedSoldier.setAi(AiType.Offensive);
                    } else if (checkedId == R.id.radioButton3) {
                        selectedSoldier.setAi(AiType.Random);
                    }
                }

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
                if (selectedSoldier != null) {
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if (currentProgress > previousProgress) {
                        if (difference <= point) {
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setForce(currentProgress);
                        } else {
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    } else {
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setForce(currentProgress);
                    }
                    pointText.setText(String.valueOf(selectedPlayer.getPoint()));
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
                if (selectedSoldier != null) {
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if (currentProgress > previousProgress) {
                        if (difference <= point) {
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setDexterity(currentProgress);
                        } else {
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    } else {
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setDexterity(currentProgress);
                    }
                    pointText.setText(String.valueOf(selectedPlayer.getPoint()));
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
                if (selectedSoldier != null) {
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if (currentProgress > previousProgress) {
                        if (difference <= point) {
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setResistance(currentProgress);
                        } else {
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    } else {
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setResistance(currentProgress);
                    }
                    pointText.setText(String.valueOf(selectedPlayer.getPoint()));
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
                if (selectedSoldier != null) {
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if (currentProgress > previousProgress) {
                        if (difference <= point) {
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setConstitution(currentProgress);
                        } else {
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    } else {
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setConstitution(currentProgress);
                    }
                    pointText.setText(String.valueOf(selectedPlayer.getPoint()));
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
                if (selectedSoldier != null) {
                    int currentProgress = seekBar.getProgress();
                    int point = selectedPlayer.getPoint();
                    int difference = Math.abs(currentProgress - previousProgress);
                    if (currentProgress > previousProgress) {
                        if (difference <= point) {
                            //assign success
                            selectedPlayer.setPoint(-difference);
                            selectedSoldier.setInitiative(currentProgress);
                        } else {
                            //assign failed
                            seekBar.setProgress(previousProgress);
                        }
                    } else {
                        //assign success
                        selectedPlayer.setPoint(difference);
                        selectedSoldier.setInitiative(currentProgress);
                    }
                    pointText.setText(String.valueOf(selectedPlayer.getPoint()));
                }
            }
        });

        reservist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSoldier != null) {
                    if (!reservist.isSelected()) {
                        selectedSoldier.setReservist(true);
                    } else {
                        selectedSoldier.setReservist(true);
                    }
                }
            }
        });

        setListener(soldiersAdapter);
    }


    public void onEliteClick(View view) {
        soldiersAdapter = new SoldiersAdapter(selectedPlayer.getElites());
        recyclerView.setAdapter(soldiersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListener(soldiersAdapter);
        selectedSoldier = null;
    }

    public void onMaitreClick(View view) {
        soldiersAdapter = new SoldiersAdapter(selectedPlayer.getMaitres());
        recyclerView.setAdapter(soldiersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListener(soldiersAdapter);
        selectedSoldier = null;
    }

    public void onPrivateClick(View view) {
        soldiersAdapter = new SoldiersAdapter(selectedPlayer.getPrivates());
        recyclerView.setAdapter(soldiersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListener(soldiersAdapter);
        selectedSoldier = null;
    }

    public void setListener(SoldiersAdapter adapter) {
        adapter.setOnClickListener(new SoldiersAdapter.OnClickListener() {
            @Override
            public void onClick(int position, Soldier soldier) {
                Log.d("soldier", soldier.toString());
                selectedSoldier = soldier;
                soldierText.setText(soldier.toString());
                reservist.setChecked(selectedSoldier.isReservist());
//                radioGroup.clearCheck();
                setRadioGroup(soldier);
                setSeekbars(soldier);
                Log.d("Attributes", String.format("F:%d D: %d R: %d C: %d I: %d AI: %b",
                        soldier.getForce(), soldier.getDexterity(), soldier.getResistance(), soldier.getConstitution(), soldier.getInitiative(), soldier.isReservist()));
            }
        });
    }

    public void setRadioGroup(Soldier soldier) {
        switch (soldier.getAi()) {
            case Defensive:
                radioGroup.check(R.id.radioButton);
                break;
            case Offensive:
                radioGroup.check(R.id.radioButton2);
                break;
            case Random:
                radioGroup.check(R.id.radioButton3);
                break;
        }
    }

    public void setSeekbars(Soldier soldier) {
        force.setProgress(soldier.getForce());
        dexterity.setProgress(soldier.getDexterity());
        resistance.setProgress(soldier.getResistance());
        constitution.setProgress(soldier.getConstitution());
        initiative.setProgress(soldier.getInitiative());
        //set min value of seekbar depending on soldier's pre-assigned attributes
        switch (soldier.rank) {
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

    public void onContinueClick(View view) {
        if(player1.hasEnoughReservists() && player2.hasEnoughReservists()){
            Intent intent = new Intent(this, MainActivity4.class);
            intent.putExtra("player1", player1);
            intent.putExtra("player2", player2);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Veuillez sélectionner 5 soldats comme réservistes", Toast.LENGTH_SHORT).show();
        }

    }
}