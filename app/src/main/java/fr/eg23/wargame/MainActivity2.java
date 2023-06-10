package fr.eg23.wargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity2 extends AppCompatActivity {

    private List<String> branche1 = List.of("ISI", "RT", "A2I", "GI", "GM", "MTE", "MM");
    private List<String> branche2 = List.of("ISI", "RT", "A2I", "GI", "GM", "MTE", "MM");
    private ListIterator<String> it1 = branche1.listIterator();
    private ListIterator<String> it2 = branche2.listIterator();
    private TextView textView1;
    private TextView textView2;
    private EditText name1;
    private EditText name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView1 = (TextView) findViewById(R.id.branche1);
        textView2 = (TextView) findViewById(R.id.branche2);
        name1 = (EditText) findViewById(R.id.name1);
        name2 = (EditText) findViewById(R.id.name2);
        Typeface customTypeface =Typeface.createFromAsset(getAssets(), "font/vollkorn_sc.ttf");
        name1.setTypeface(customTypeface);
        name2.setTypeface(customTypeface);
    }

    public void onBranche1ClickLeft(View v){
        if(it1.hasPrevious()){
            textView1.setText(it1.previous());
        }

    }

    public void onBranche1ClickRight(View v){
        if(it1.hasNext()){
            textView1.setText(it1.next());
        }
    }

    public void onBranche2ClickLeft(View v){
        if(it2.hasPrevious()){
            textView2.setText(it2.previous());
        }
    }

    public void onBranche2ClickRight(View v){
        if(it2.hasNext()){
            textView2.setText(it2.next());
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity3.class);
        intent.putExtra("name1", name1.getText().toString());
        intent.putExtra("name2", name2.getText().toString());
        startActivity(intent);
    }
}