package com.darryl.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class bestFitActivity extends AppCompatActivity {


    Integer chunkSIZE, processSIZE;
    int noOfProcesses, noOfChunks , x;
    int[] testSizeArray = new int[100];
    int[] differenceArray = new int[100];
    int allocation[] = new int[100];
    public static ArrayList<Integer> chunkSizeArray = new ArrayList<>();
    public static ArrayList<Integer> processSizeArray = new ArrayList<>();
    private static ArrayList<String> bestSizeArray = new ArrayList<>();
    private static ArrayList<Integer> bestChunkArray = new ArrayList<>();
    ArrayAdapter<Integer> adapter;
    ArrayAdapter<String> adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_fit);

        ListView blockSize = (ListView) findViewById(R.id.blockSize);
        ListView finalProcessList = (ListView) findViewById(R.id.finalprocesslist);
        Button clearButton = (Button) findViewById(R.id.clearButton);



        Bundle bundle = getIntent().getExtras();
        chunkSizeArray = (ArrayList<Integer>) bundle.getIntegerArrayList("chunkSizeArray");
        Log.d("Manager", "" + chunkSizeArray);


        processSizeArray = (ArrayList<Integer>) bundle.getIntegerArrayList("processSizeArray");
        Log.d("Manager", "" + processSizeArray);


        noOfProcesses = getIntent().getExtras().getInt("numberOfProcesses");
        Log.d("Manager", " " + noOfProcesses);

        noOfChunks = getIntent().getExtras().getInt("numberOfChunks");
        Log.d("Manager", " " + noOfChunks);


        //for(Integer d = 0 ; d <= )
        for (int i = 0; i < noOfProcesses; i++) {
            for (int j = 0; j < noOfChunks; j++) {
                differenceArray[j] = chunkSizeArray.get(j) - processSizeArray.get(i);

                if (testSizeArray[j] == 1)
                    differenceArray[j] = -1;
            }
            int min = 32767, k = 0;
            for (int j = 0; j < noOfChunks; j++) {
                if (differenceArray[j] < min && differenceArray[j] >= 0) {
                    min = differenceArray[j];
                    k = j;
                }
            }
            testSizeArray[k] = 1;

            if (differenceArray[k] < 0) {
                Log.d("Manager", "Best Fit not found for process " + (i + 1) + ".");
                bestSizeArray.add(processSizeArray.get(i)+" - Not Allocated");
            } else {
                Log.d("Manager", "Best Fit for process " + processSizeArray.get(i) + " is " + chunkSizeArray.get(k) + " and Hole of " + differenceArray[k] + " is created.");
                Integer finalChunk = chunkSizeArray.get(k);
                bestChunkArray.add(finalChunk);
                String finalProcess = Integer.toString(processSizeArray.get(i));
                bestSizeArray.add(finalProcess);
            }
        }

        adapter = new ArrayAdapter<>(bestFitActivity.this , android.R.layout.simple_expandable_list_item_1 , bestChunkArray);
        blockSize.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter1 = new ArrayAdapter<>(bestFitActivity.this , android.R.layout.simple_expandable_list_item_1 , bestSizeArray);
        finalProcessList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chunkSizeArray.clear();
                processSizeArray.clear();

                bestSizeArray.clear();
                bestChunkArray.clear();

                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();

            }
        });
    }
}


