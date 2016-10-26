package com.example.ou.chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private boolean isWhite=true;
    private GridView gridView;
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private static final int  rowCount= 15;
    private static final int colomnCount= 15;
    private int[] icon=new int[rowCount*colomnCount];
    private String[] iconname=new String[rowCount*colomnCount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i< rowCount *colomnCount;i++){
            iconname[i]=""+i;
            icon[i]=R.drawable.chess_board;
        }
        gridView=(GridView) findViewById(R.id.gridView);
        data_list=new ArrayList<Map<String, Object>>();
        getData();
        //String[] from={"image","text"};
        //int[] to={R.id.image, R.id.text};
        String[] from={"image"};
        int[] to={R.id.image};
        simpleAdapter=new SimpleAdapter(this,data_list,R.layout.item,from,to);
        Log.d("MainActivity", "contens: "+data_list);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this,"You clicked item: "+ i +"  ",Toast.LENGTH_SHORT).show();
                //updateChessBoard(simpleAdapter,data_list,i);
                long selectedIconName= Long.parseLong(data_list.get(i).get("image").toString());
                Log.d("MainActivity", "selectedIconName: "+selectedIconName);
                if(selectedIconName == R.drawable.chess_black || selectedIconName == R.drawable.chess_white){
                //if(true){
                    Log.d("MainAcitivity","selectedIconName: "+ selectedIconName);
                    Toast.makeText(MainActivity.this,"Chessman exists!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,Object> modifiedMap=new HashMap<String,Object>();
                long modifiedId;
                if(isWhite) {
                    modifiedId = R.drawable.chess_white;
                }else {
                    modifiedId = R.drawable.chess_black;
                }
                isWhite = !isWhite;
                modifiedMap.put("text",iconname[i]);
                modifiedMap.put("image",modifiedId);
                Log.d("MainActivity","before: "+data_list);
                Map<String,Object> ma= data_list.get(i);
                ma=modifiedMap;
                data_list.set(i,modifiedMap);
                /*if(data_list.size()>40||data_list.size()<25) {
                    data_list.add(ma);
                }else {
                    data_list.remove(i);
                }*/
                simpleAdapter.notifyDataSetChanged();
                Log.d("MainActivity","after: "+data_list);
                Log.d("MainActivity","ma: "+ma);
                Log.d("MainActivity","data_list["+i+"]"+data_list.get(i));
                Log.d("MainActivity","data_list size: "+data_list.size());
            }
        });

        /*ListAdapter adapter=gridView.getAdapter();
        int widthItem =adapter.getView(1,null,gridView).getWidth();
        int heightItem=widthItem;
        Log.d("MainAcitivity","view: "+ adapter.getView(1,null,gridView)+"  heightItem: "+widthItem);
        LinearLayout.LayoutParams linearParams=new LinearLayout.LayoutParams(widthItem,heightItem);
        Log.d("MainAcitivity"," parmas: "+linearParams);*/
        //gridView.setLayoutParams(linearParams);

    }
    public void updateChessBoard(SimpleAdapter sa, List<Map<String,Object>> itemList,int itemId){
        Map<String,Object> modifiedMap=new HashMap<String,Object>();
        long modifiedId=R.drawable.chess_white;
        modifiedMap.put("text",iconname[0]);
        modifiedMap.put("image",modifiedId);
        Map<String,Object> ma= data_list.get(itemId);//=modifiedMap;
        ma=modifiedMap;
        sa.notifyDataSetChanged();
    }
    public List<Map<String,Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("text",iconname[i]);
            map.put("image",icon[i]);
            data_list.add(map);
        }
        return data_list;
    }
}
