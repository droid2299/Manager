package com.darryl.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class firstFitActivity extends AppCompatActivity {

    Integer chunkSIZE , processSIZE ;
    int noOfProcesses , noOfChunks ;
    int[] testSizeArray = new int[100];
    private static ArrayList<Integer> chunkSizeArray = new ArrayList<>();
    private static ArrayList<Integer> processSizeArray = new ArrayList<>();
    private static ArrayList<String> bestSizeArray = new ArrayList<>();
    private static ArrayList<String> bestChunkArray = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_fit);

        ListView blockSize = (ListView)findViewById(R.id.blockSize);
        ListView finalProcessList  = (ListView)findViewById(R.id.finalprocesslist);
        Button clearButton = (Button) findViewById(R.id.clearButton);

        Bundle bundle = getIntent().getExtras();
        chunkSizeArray = (ArrayList<Integer>) bundle.getIntegerArrayList("chunkSizeArray");
        Log.d("Manager" , ""+chunkSizeArray);

        processSizeArray = (ArrayList<Integer>) bundle.getIntegerArrayList("processSizeArray");
        Log.d("Manager" , ""+processSizeArray);

        noOfProcesses = getIntent().getExtras().getInt("numberOfProcesses");
        Log.d("Manager", " " + noOfProcesses);

        noOfChunks = getIntent().getExtras().getInt("numberOfChunks");
        Log.d("Manager", " " + noOfChunks);


        for(Integer i = 0 ; i < noOfProcesses ; i++){
            Integer k = -1;
            for(Integer j = 0 ; j < noOfChunks ; j++){
                if(chunkSizeArray.get(j) >= processSizeArray.get(i)){
                    if(testSizeArray[j] != 1){
                        k = j ;
                        testSizeArray[k] = 1;
                        break;

                    }
                }


            }
            if(k == -1){
                Log.d("Manager" , "Not found");

                String finalProcess2 = Integer.toString(processSizeArray.get(i));
                bestSizeArray.add(finalProcess2+" - Not Allocated");

            }
            else{
                Log.d("Manager" , ""+(chunkSizeArray.get(k)+"  "+processSizeArray.get(i)));
                String finalChunk = Integer.toString(chunkSizeArray.get(k)) ;
                String finalProcess = Integer.toString(processSizeArray.get(i));
                bestSizeArray.add(finalProcess);
                bestChunkArray.add(finalChunk);
            }
        }

        Log.d("Manager" , ""+bestSizeArray);

        adapter = new ArrayAdapter<>(firstFitActivity.this , android.R.layout.simple_expandable_list_item_1 , bestChunkArray);
        blockSize.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter1 = new ArrayAdapter<>(firstFitActivity.this , android.R.layout.simple_expandable_list_item_1 , bestSizeArray);
        finalProcessList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                bestSizeArray.clear();
                bestChunkArray.clear();

                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();

            }
        });
    }
}
