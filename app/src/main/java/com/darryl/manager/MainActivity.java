package com.darryl.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    int i = 0 , j = 0 , getChunkSIze1;
    Integer getChunkSize , getProcessSize;
    ArrayList<Integer> chunkSizeArray = new ArrayList<>();
    ArrayList<Integer> processSizeArray = new ArrayList<>();
    EditText chunkSize;
    EditText processSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button chunkSave = (Button)findViewById(R.id.chunkSave);
        Button processSave = (Button)findViewById(R.id.processSave);
        Button bestFitDirector = (Button)findViewById(R.id.bestFitdirector);
        Button worstFitDirector = (Button)findViewById(R.id.worstFitdirector);
        Button firstFitDirector = (Button)findViewById(R.id.firstFitDirector);
        Button clearButton = (Button)findViewById(R.id.clearButton);


        processSize = (EditText)findViewById(R.id.processSize);
        chunkSize = (EditText)findViewById(R.id.blockSize);


        chunkSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                i++;
                Log.d("Manager" , " " +i);

                if(chunkSize.length() == 0){
                    chunkSize.setError("Enter Chunks");
                }

                else{
                    getChunkSize = Integer.parseInt(chunkSize.getText().toString());

                    String test = Integer.toString(getChunkSize);
                    chunkSizeArray.add(getChunkSize);
                    chunkSize.setText("");
                }
            }
        });

        processSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j++;

                if(processSize.length() == 0){
                    processSize.setError("Enter Processes");
                }

                else{
                    getProcessSize = Integer.parseInt(processSize.getText().toString())  ;
                    processSizeArray.add(getProcessSize);
                    Log.d("Manager" , " " +j);

                    processSize.setText("");
                }


            }
        });



        bestFitDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(processSizeArray.isEmpty() && chunkSizeArray.isEmpty() )
                {
                    Toast.makeText(getApplicationContext(),"Enter the Chunks & Processes!!",Toast.LENGTH_SHORT).show();
                }

                else if(chunkSizeArray.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Chunks!!",Toast.LENGTH_SHORT).show();
                }

                else if(processSizeArray.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Processes!!",Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent myIntent = new Intent(MainActivity.this, bestFitActivity.class);
                    myIntent.putExtra("chunkSizeArray", chunkSizeArray);
                    myIntent.putExtra("processSizeArray", processSizeArray);
                    myIntent.putExtra("numberOfProcesses", j);
                    myIntent.putExtra("numberOfChunks", i);
                    startActivity(myIntent);
                }
            }
        });

        worstFitDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(processSizeArray.isEmpty() && chunkSizeArray.isEmpty() )
                {
                    Toast.makeText(getApplicationContext(),"Enter the Chunks & Processes!!",Toast.LENGTH_SHORT).show();
                }

                else if(chunkSizeArray.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Chunks!!",Toast.LENGTH_SHORT).show();
                }

                else if(processSizeArray.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Processes!!",Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent myIntent3 = new Intent(MainActivity.this, worstFitActivity.class);
                    myIntent3.putExtra("chunkSizeArray", chunkSizeArray);
                    myIntent3.putExtra("processSizeArray", processSizeArray);
                    myIntent3.putExtra("numberOfProcesses", j);
                    myIntent3.putExtra("numberOfChunks", i);
                    startActivity(myIntent3);
                }
            }
        });

        firstFitDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(processSizeArray.isEmpty() && chunkSizeArray.isEmpty() )
                {
                    Toast.makeText(getApplicationContext(),"Enter the Chunks & Processes!!",Toast.LENGTH_SHORT).show();
                }

                else if(chunkSizeArray.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Chunks!!",Toast.LENGTH_SHORT).show();
                }

                else if(processSizeArray.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Processes!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent myIntent2 = new Intent(MainActivity.this, firstFitActivity.class);
                    myIntent2.putExtra("chunkSizeArray", chunkSizeArray);
                    myIntent2.putExtra("processSizeArray", processSizeArray);
                    myIntent2.putExtra("numberOfProcesses", j);
                    myIntent2.putExtra("numberOfChunks", i);
                    startActivity(myIntent2);
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chunkSizeArray.clear();
                processSizeArray.clear();

                i = 0;
                j = 0 ;

                Toast.makeText(getApplicationContext(),"Input Cleared!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
