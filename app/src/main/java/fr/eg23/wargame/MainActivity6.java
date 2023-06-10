package fr.eg23.wargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity6 extends AppCompatActivity {

    private Player player1;
    private Player player2;
    private Player winner;
    private Player loser;
    private RecyclerView occupation1;
    private RecyclerView occupation2;
    private ImageButton gymButton1;
    private ImageButton gymButton2;
    private ImageButton bdeButton1;
    private ImageButton bdeButton2;
    private ImageButton libButton1;
    private ImageButton libButton2;
    private ImageButton adminButton1;
    private ImageButton adminButton2;
    private ImageButton industryButton1;
    private ImageButton industryButton2;
    private ZoneAdapter adapter1;
    private ZoneAdapter adapter2;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;
    private TextView text8;
    private TextView text9;
    private TextView text10;
    private TextView buttonText;
    private boolean isRoundFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Game.round += 1;

        Intent intent = getIntent();
        player1 = (Player) intent.getSerializableExtra("player1");
        player2 = (Player) intent.getSerializableExtra("player2");
        TextView nameText1 = findViewById(R.id.textView8);
        TextView nameText2 = findViewById(R.id.textView14);
        initView();
        setDeployText();
        gymButton1.setOnClickListener(buttonClickListener);


        nameText1.setText(player1.getName());
        nameText2.setText(player2.getName());

        setImageButtonListener();

        adapter1 = new ZoneAdapter(player1.getOccupation());
        occupation1.setAdapter(adapter1);
        occupation1.setLayoutManager(new LinearLayoutManager(this));

        adapter2 = new ZoneAdapter(player2.getOccupation());
        occupation2.setAdapter(adapter2);
        occupation2.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initView() {

        occupation1 = findViewById(R.id.occupation1);
        occupation2 = findViewById(R.id.occupation2);
        gymButton1 = findViewById(R.id.imageButton17);
        gymButton2 = findViewById(R.id.imageButton18);
        bdeButton1 = findViewById(R.id.imageButton21);
        bdeButton2 = findViewById(R.id.imageButton22);
        libButton1 = findViewById(R.id.imageButton19);
        libButton2 = findViewById(R.id.imageButton20);
        adminButton1 = findViewById(R.id.imageButton23);
        adminButton2 = findViewById(R.id.imageButton24);
        industryButton1 = findViewById(R.id.imageButton25);
        industryButton2 = findViewById(R.id.imageButton26);
        text1 = findViewById(R.id.textView17);
        text2 = findViewById(R.id.textView18);
        text3 = findViewById(R.id.textView19);
        text4 = findViewById(R.id.textView20);
        text5 = findViewById(R.id.textView21);
        text6 = findViewById(R.id.textView22);
        text7 = findViewById(R.id.textView23);
        text8 = findViewById(R.id.textView24);
        text9 = findViewById(R.id.textView25);
        text10 = findViewById(R.id.textView26);
        buttonText = findViewById(R.id.textView28);
    }

    public void showPopupWindow(List<Soldier> soldiers) {
        View view = LayoutInflater.from(this).inflate(R.layout.popup, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.popuplist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DeployAdapter adapter = new DeployAdapter(soldiers);
        recyclerView.setAdapter(adapter);
        PopupWindow popupWindow = new PopupWindow(view, 500, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
//        popupWindow.showAsDropDown(industryButton1);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.imageButton17) {
                showPopupWindow(player1.getDeployment().getSoldiersByArea(Zone.gym));
            } else if (id == R.id.imageButton18) {
                showPopupWindow(player2.getDeployment().getSoldiersByArea(Zone.gym));
            } else if (id == R.id.imageButton21) {
                showPopupWindow(player1.getDeployment().getSoldiersByArea(Zone.bde));
            } else if (id == R.id.imageButton22) {
                showPopupWindow(player2.getDeployment().getSoldiersByArea(Zone.bde));
            } else if (id == R.id.imageButton19) {
                showPopupWindow(player1.getDeployment().getSoldiersByArea(Zone.library));
            } else if (id == R.id.imageButton20) {
                showPopupWindow(player2.getDeployment().getSoldiersByArea(Zone.library));
            } else if (id == R.id.imageButton23) {
                showPopupWindow(player1.getDeployment().getSoldiersByArea(Zone.admin));
            } else if (id == R.id.imageButton24) {
                showPopupWindow(player2.getDeployment().getSoldiersByArea(Zone.admin));
            } else if (id == R.id.imageButton25) {
                showPopupWindow(player1.getDeployment().getSoldiersByArea(Zone.industry));
            } else if (id == R.id.imageButton26) {
                showPopupWindow(player2.getDeployment().getSoldiersByArea(Zone.industry));
            }
        }
    };

    public void setImageButtonListener() {
        gymButton1.setOnClickListener(buttonClickListener);
        gymButton2.setOnClickListener(buttonClickListener);
        bdeButton1.setOnClickListener(buttonClickListener);
        bdeButton2.setOnClickListener(buttonClickListener);
        libButton1.setOnClickListener(buttonClickListener);
        libButton2.setOnClickListener(buttonClickListener);
        adminButton1.setOnClickListener(buttonClickListener);
        adminButton2.setOnClickListener(buttonClickListener);
        industryButton1.setOnClickListener(buttonClickListener);
        industryButton2.setOnClickListener(buttonClickListener);
    }

    public void setDeployText() {
        text1.setText(String.valueOf(player1.getDeployment().getDeployedNumByArea(Zone.gym)));
        text2.setText(String.valueOf(player2.getDeployment().getDeployedNumByArea(Zone.gym)));
        text3.setText(String.valueOf(player1.getDeployment().getDeployedNumByArea(Zone.bde)));
        text4.setText(String.valueOf(player2.getDeployment().getDeployedNumByArea(Zone.bde)));
        text5.setText(String.valueOf(player1.getDeployment().getDeployedNumByArea(Zone.library)));
        text6.setText(String.valueOf(player2.getDeployment().getDeployedNumByArea(Zone.library)));
        text7.setText(String.valueOf(player1.getDeployment().getDeployedNumByArea(Zone.admin)));
        text8.setText(String.valueOf(player2.getDeployment().getDeployedNumByArea(Zone.admin)));
        text9.setText(String.valueOf(player1.getDeployment().getDeployedNumByArea(Zone.industry)));
        text10.setText(String.valueOf(player2.getDeployment().getDeployedNumByArea(Zone.industry)));
    }

    public List<Soldier> battle() {
//        Deployment deployment1 = player1.getDeployment();
//        Deployment deployment2 = player2.getDeployment();
//        int strengthGap = 0;
//        Map<Zone, Integer> strengthGapMap = new HashMap<>();
//        //iterate through all the zones
//        for (Zone zone : Zone.values()) {
//            List<Soldier> squad1 = deployment1.getSoldiersByArea(zone);
//            List<Soldier> squad2 = deployment2.getSoldiersByArea(zone);
//            int strength1 = squad1.size();
//            int strength2 = squad2.size();
//            strengthGap = strength1 - strength2;
//            strengthGapMap.put(zone, strengthGap);
//        }
//        int maxGap = 0;
//        Zone maxGapZone = null;
        List<Soldier> clearedSoldiers = null;
//        for (Map.Entry<Zone, Integer> entry : strengthGapMap.entrySet()) {
//            if (Math.abs(entry.getValue()) > Math.abs(maxGap)) {
//                maxGap = entry.getValue();
//                maxGapZone = entry.getKey();
//            }
//        }
//        if (maxGap > 0) {
//            //player1 wins
//            winner = player1;
//            loser = player2;
//            clearedSoldiers = player2.getDeployment().getSoldiersByArea(maxGapZone);
//            player2.getDeployment().clearZone(maxGapZone);
//            player2.removeSoldiers(clearedSoldiers);
//            updateOccupation(player1, player2, maxGapZone);
//        } else if (maxGap < 0) {
//            //player2 wins
//            winner = player2;
//            loser = player1;
//            clearedSoldiers = player1.getDeployment().getSoldiersByArea(maxGapZone);
//            player1.getDeployment().clearZone(maxGapZone);
//            player1.removeSoldiers(clearedSoldiers);
//            player2.getOccupation().add(maxGapZone);
//            updateOccupation(player2, player1, maxGapZone);
//        } else {
//            //choose randomly a zone to clear
//            Random random = new Random();
//            Zone randomZone = Zone.values()[random.nextInt(Zone.values().length)];
//            int randomPlayer = random.nextInt(2);
//            switch (randomPlayer){
//                case 0:
//                    //player2 wins
//                    if(player2.getOccupation().contains(randomZone)){
//                        return null;
//                    }
//                    winner = player2;
//                    loser = player1;
//                    clearedSoldiers = player1.getDeployment().getSoldiersByArea(randomZone);
//                    player1.getDeployment().clearZone(randomZone);
//                    player1.removeSoldiers(clearedSoldiers);
//                    updateOccupation(player2, player1, randomZone);
//                    break;
//                case 1:
//                    //player1 wins
//                    if(player1.getOccupation().contains(randomZone)){
//                        return null;
//                    }
//                    winner = player1;
//                    loser = player2;
//                    clearedSoldiers = player2.getDeployment().getSoldiersByArea(randomZone);
//                    player2.getDeployment().clearZone(randomZone);
//                    player2.removeSoldiers(clearedSoldiers);
//                    updateOccupation(player1, player2, randomZone);
//                    break;
//            }
//        }
        //check if there is a zone where a player has no soldiers, if so, the other player wins

        Random random = new Random();
        Zone randomZone = Zone.values()[random.nextInt(Zone.values().length)];
        int randomPlayer = random.nextInt(2);
        switch (randomPlayer) {
            case 0:
                //player2 wins
                if (player2.getOccupation().contains(randomZone)) {
                    break;
                }
                winner = player2;
                loser = player1;
                clearedSoldiers = player1.getDeployment().getSoldiersByArea(randomZone);
                player1.getDeployment().clearZone(randomZone);
                player1.removeSoldiers(clearedSoldiers);
                updateOccupation(player2, player1, randomZone);
                break;
            case 1:
                //player1 wins
                if (player1.getOccupation().contains(randomZone)) {
                    break;
                }
                winner = player1;
                loser = player2;
                clearedSoldiers = player2.getDeployment().getSoldiersByArea(randomZone);
                player2.getDeployment().clearZone(randomZone);
                player2.removeSoldiers(clearedSoldiers);
                updateOccupation(player1, player2, randomZone);
                break;
        }
        return clearedSoldiers;
    }

    public void onAttack(View view) {
        if (!isRoundFinished) {
//            List<Soldier> clearedSoldiers = battle();
            List<Soldier> clearedSoldiers = null;


            while (clearedSoldiers == null){
                clearedSoldiers = battle();
            }
            Log.d("clearedSoldiers", String.valueOf(clearedSoldiers.size()));
//            if (clearedSoldiers != null) {
//                showResultDialog(clearedSoldiers);
//            }

            if (player1.getOccupation().size() >= 3 || player2.getOccupation().size() >= 3) {
//                Toast.makeText(this, "Jeu terminer", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle);
                builder.setTitle("Fin du jeu");
                String message = winner.getName() + " suis le gagnant";
                builder.setMessage(message);
                builder.setIcon(R.drawable.common_soldier);
                AlertDialog dialog = builder.create();
                dialog.show();
                view.setEnabled(false);
//                finish();
            }
            showResultDialog(clearedSoldiers);
            buttonText.setText("trêve");
            isRoundFinished = true;
        } else {
            buttonText.setText("trêve");
//            Toast.makeText(this, "Terminer", Toast.LENGTH_SHORT).show();
            int from = 6;
            Intent intent = new Intent();
            intent.putExtra("from", from);
            intent.putExtra("player1", player1);
            intent.putExtra("player2", player2);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    public void updateOccupation(Player winner, Player loser, Zone zone) {

        winner.getOccupation().add(zone);
        loser.getOccupation().remove(zone);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        setDeployText();
    }

    public void showResultDialog(List<Soldier> clearedSoldiers) {
        String clearedSoldiersText = "";
        for (Soldier soldier : clearedSoldiers) {
            clearedSoldiersText += soldier.toString() + ", ";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle);
        builder.setTitle("Fin de la série");
        String message = winner.getName() + " a gagné la série avec " + winner.getOccupation().size() + " zones occupées\n" +
                loser.getName() + " a perdu " + clearedSoldiersText;
        builder.setMessage(message);
        builder.setIcon(R.drawable.common_soldier);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
