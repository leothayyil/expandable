package com.example.lintojohny.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expListView;
    private ListAdapterExpandible adapter;

    // declare array List for all headers in list
    ArrayList<String> headersArrayList = new ArrayList<String>();

    // Declare Hash map for all headers and their corresponding values
    HashMap<String, ArrayList<String>> childArrayList = new HashMap<String, ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.expListView);

        // add headers values
//        headersArrayList.add("Days Of Week");
//        headersArrayList.add("Festival Name");
//        headersArrayList.add("Colors Name");

        // add add child content
        final ArrayList<String> daysOfWeekArrayList = new ArrayList<String>();
//        daysOfWeekArrayList.add("Sunday");
//        daysOfWeekArrayList.add("Monday");
//        daysOfWeekArrayList.add("Tuesday");
//        daysOfWeekArrayList.add("Wednesday");
//        daysOfWeekArrayList.add("Thursday");
//        daysOfWeekArrayList.add("Friday");
//        daysOfWeekArrayList.add("Saturday");
//
//        childArrayList.put("Days Of Week", daysOfWeekArrayList);
//
//        ArrayList<String> festivalArrayList = new ArrayList<String>();
//        festivalArrayList.add("Diwali");
//        festivalArrayList.add("Holi");
//        festivalArrayList.add("Christmas");
//        festivalArrayList.add("Vijay Dashmi");
//
//        childArrayList.put("Festival Name", festivalArrayList);
//
//        ArrayList<String> colorsArrayList = new ArrayList<String>();
//        colorsArrayList.add("Red");
//        colorsArrayList.add("Blue");
//        colorsArrayList.add("Green");
//
//        childArrayList.put("Colors Name", colorsArrayList);

        String action="general_message";
        String user_id="1";
        String classa="7";
        String division="7";

        new RetrofitHelper(MainActivity.this).getApi().getGeneralMsg(action,user_id,classa,division)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                            String status=jsonObject.getString("status");

                            JSONArray jsonArray=jsonObject.getJSONArray("general_message");
                            for (int i=0;i<=jsonArray.length();i++){
                                JSONObject  jsonObject1=jsonArray.getJSONObject(i);

                                String title=jsonObject1.getString("title");
                                String message=jsonObject1.getString("message");


                                headersArrayList.add(title);
                                daysOfWeekArrayList.add(message);
                                childArrayList.put(title,daysOfWeekArrayList);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new ListAdapterExpandible(MainActivity.this, headersArrayList,
                                childArrayList);

                        expListView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                });


        // declare adapter



        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), "Child is clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO: Do your stuff
                Toast.makeText(getApplicationContext(), "Group is Clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // TODO: Do your stuff
                Toast.makeText(getApplicationContext(), "Child is Collapsed", Toast.LENGTH_LONG).show();
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                // TODO: Do your stuff

                Toast.makeText(getApplicationContext(), "Child is Expanded", Toast.LENGTH_LONG).show();
            }
        });



    }
}
