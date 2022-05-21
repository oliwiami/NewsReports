package com.example.newsreports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsreports.Models.NewsApiResponse;
import com.example.newsreports.Models.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btnCountry;
    String country="pl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading articles...");
        dialog.show();

        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);

        btnCountry = findViewById(R.id.btn_country);
        btnCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCountry();
            }

            private void chooseCountry() {

                String countryCode;
                String[] countries = {"United Arab Emirates","Argentina","Austria","Australia","Belgium","Bulgaria","Brazil","Canada",
                        "Switzerland","China","Colombia","Cuba","Czechia","Germany","Egypt","France",
                        "United Kingdom","Greece","Hong Kong","Hungary","Indonesia","Ireland","Israel","India","Italy",
                        "Japan","South Korea","Lithuania","Latvia","Morocco","Mexico","Malaysia","Nigeria","Netherlands",
                        "Norway","New Zealand","Philippines","Poland","Portugal","Romania",
                        "Serbia","Saudi Arabia","Sweden","Singapore","Slovenia","Slovakia","Thailand","Turkey","Taiwan","Ukraine","USA","Venezuela","South Africa"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose country");
                builder.setSingleChoiceItems(countries, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(countries[which]){
                            case "United Arab Emirates" : country = "ae";
                                break;
                            case "Argentina" : country = "ar";
                                break;
                            case "Austria" : country = "at";
                                break;
                            case "Australia" : country = "au";
                                break;
                            case "Belgium" : country = "be";
                                break;
                            case "Bulgaria" : country = "bg";
                                break;
                            case "Brazil" : country = "br";
                                break;
                            case "Canada" : country = "ca";
                                break;
                            case "Switzerland" : country = "ch";
                                break;
                            case "China" : country = "cn";
                                break;
                            case "Colombia" : country = "co";
                                break;
                            case "Cuba" : country = "cu";
                                break;
                            case "Czechia" : country = "cz";
                                break;
                            case "Germany" : country = "de";
                                break;
                            case "Egypt" : country = "eg";
                                break;
                            case "France" : country = "fr";
                                break;
                            case "United Kingdom" : country = "gb";
                                break;
                            case "Greece" : country = "gr";
                                break;
                            case "Hong Kong" : country = "hk";
                                break;
                            case "Hungary" : country = "hu";
                                break;
                            case "Indonesia" : country = "id";
                                break;
                            case "Ireland" : country = "ie";
                                break;
                            case "Israel" : country = "il";
                                break;
                            case "India" : country = "in";
                                break;
                            case "Italy" : country = "it";
                                break;
                            case "Japan" : country = "jp";
                                break;
                            case "South Korea" : country = "kr";
                                break;
                            case "Lithuania" : country = "lt";
                                break;
                            case "Latvia" : country = "lv";
                                break;
                            case "Morocco" : country = "ma";
                                break;
                            case "Mexico" : country = "mx";
                                break;
                            case "Malaysia" : country = "my";
                                break;
                            case "Nigeria" : country = "ng";
                                break;
                            case "Netherlands" : country = "nl";
                                break;
                            case "Norway" : country = "no";
                                break;
                            case "New Zealand" : country = "nz";
                                break;
                            case "Philippines" : country = "ph";
                                break;
                            case "Poland" : country = "pl";
                                break;
                            case "Portugal" : country = "pt";
                                break;
                            case "Romania" : country = "ro";
                                break;
                            case "Serbia" : country = "rs";
                                break;
                            case "Saudi Arabia" : country = "sa";
                                break;
                            case "Sweden" : country = "se";
                                break;
                            case "Singapore" : country = "sg";
                                break;
                            case "Slovenia" : country = "si";
                                break;
                            case "Slovakia" : country = "sk";
                                break;
                            case "Thailand" : country = "th";
                                break;
                            case "Turkey" : country = "tr";
                                break;
                            case "Taiwan" : country = "tw";
                                break;
                            case "Ukraine" : country = "ua";
                                break;
                            case "USA" : country = "us";
                                break;
                            case "Venezuela" : country = "ve";
                                break;
                            case "South Africa" : country = "za";
                                break;
                        }

                       // country = countries[which];
                    }
                });
                builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                       //dialog = new ProgressDialog(MainActivity.this);
                        dialog.setTitle("Loading articles under " + country);
                        dialog.show();
                        Requests req = new Requests(MainActivity.this);
                        req.getNewsHeadlines(listner, "general", null, country);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        Requests req = new Requests(this);
        req.getNewsHeadlines(listner, "general", null, country);
    }

    private final OnFetchDataListener<NewsApiResponse> listner = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {

            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list){
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this, list,this );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClick(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, NewsActivity.class)
        .putExtra("data",headlines));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        dialog.setTitle("Loading articles under " + category);
        dialog.show();
        Requests req = new Requests(this);
        req.getNewsHeadlines(listner, category, null, country);
    }

}