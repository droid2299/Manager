package com.darryl.manager;

import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class worstFitActivity extends AppCompatActivity {

    Integer chunkSIZE, processSIZE;
    int noOfProcesses, noOfChunks , i , size , worst , j , used , c = 0;
    int[] testSizeArray = new int[100];
    int[] differenceArray = new int[100];
    int allocation[] = new int[100];
    private static ArrayList<Integer> chunkSizeArray = new ArrayList<>();
    private static ArrayList<Integer> processSizeArray = new ArrayList<>();
    private static ArrayList<String> bestSizeArray = new ArrayList<>();
    private static ArrayList<String> bestChunkArray = new ArrayList<>();
    private static ArrayList<Integer> processBest = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worst_fit);

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


        int[] usage = new int[noOfChunks];


        for(i = 0 ; i < noOfProcesses ; i++)
        {
            size = 0;
            worst = -1;
            for(j = 0 ; j < noOfChunks ; j++)
            {
                if(processSizeArray.get(i) <= chunkSizeArray.get(j) && usage[j] == 0 && (chunkSizeArray.get(j) - processSizeArray.get(i)) > size)
                {
                    size = chunkSizeArray.get(j) - processSizeArray.get(i);
                    worst = j;
                }
            }
            if(worst!=-1) //Ensuring a worst fit.
            {
                usage[worst]=1;
                used = used + chunkSizeArray.get(worst);
                c++;
                Log.d("Manager" , "Process "+(i+1)+" is in block "+(worst+1));
                String temp2 = Integer.toString(chunkSizeArray.get(worst));
                bestChunkArray.add(temp2);
                String temp = Integer.toString(processSizeArray.get(i));
                bestSizeArray.add(temp);
                Log.d("Manager" , ""+bestChunkArray);
            }
            else
            {
                String temp3 = Integer.toString(processSizeArray.get(i));
                bestChunkArray.add(" - Empty Chunk");
                bestSizeArray.add(temp3 + " - Not Allocated");
            }


        }

        adapter = new ArrayAdapter<>(worstFitActivity.this , android.R.layout.simple_expandable_list_item_1 , bestChunkArray);
        blockSize.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter1 = new ArrayAdapter<>(worstFitActivity.this , android.R.layout.simple_expandable_list_item_1 , bestSizeArray);
        finalProcessList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Manager" , "Button pressed");

                bestSizeArray.clear();
                bestChunkArray.clear();

                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();

            }
        });


    }
}
