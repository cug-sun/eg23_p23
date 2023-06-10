package fr.eg23.wargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

public class MainActivity6 extends AppCompatActivity {

    private Player player1;
    private Player player2;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Intent intent = getIntent();
        player1 = (Player) intent.getSerializableExtra("player1");
        player2 = (Player) intent.getSerializableExtra("player2");
        TextView nameText1 = findViewById(R.id.textView8);
        TextView nameText2 = findViewById(R.id.textView14);
        initView();
        gymButton1.setOnClickListener(buttonClickListener);


        nameText1.setText(player1.getName());
        nameText2.setText(player2.getName());

        setImageButtonListener();
    }

    public void initView(){

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
    }

    public void showPopupWindow(List<Soldier> soldiers) {
        View view = LayoutInflater.from(this).inflate(R.layout.popup,null);
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
            if(id == R.id.imageButton17){
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

    public void setImageButtonListener(){
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
}