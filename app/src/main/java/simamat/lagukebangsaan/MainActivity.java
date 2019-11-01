package simamat.lagukebangsaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

import simamat.lagukebangsaan.database.DatabaseAccess;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    private ListView rvCatalog;
    private InterstitialAd interstitialAd;

//    public static final int REQUEST_CODE_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();
        mAdView.loadAd(adRequest);

        createInterstitial();

        CustomIntent.customType(MainActivity.this, "left-to-right");


        rvCatalog = (ListView) findViewById(R.id.rv_judul_catalog);
        rvCatalog.setDivider(null);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> allJudul = databaseAccess.getAllJudul();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_judul, R.id.tv_list_judul, allJudul);
        rvCatalog.setAdapter(adapter);

        rvCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();

                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            // not call show interstitial ad from here
                        }

                        @Override
                        public void onAdClosed() {
                            loadInterstitial();

                            /////////////////////////
                            Intent intent = new Intent(MainActivity.this, LirikActivity.class);
                            String pos = (rvCatalog.getItemAtPosition(position).toString());
                            intent.putExtra("judul", pos);
                            startActivity(intent);
                            /////////////////////////
                        }
                    });
                } else {
                    loadInterstitial();

                    /////////////////////////
                    Intent intent = new Intent(MainActivity.this, LirikActivity.class);
                    String pos = (rvCatalog.getItemAtPosition(position).toString());
                    intent.putExtra("judul", pos);
                    startActivity(intent);
                    /////////////////////////
                }
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

    }

    public void createInterstitial() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // Ganti sesuai dengan kode interstitial ads anda
        loadInterstitial();
    }

    public void loadInterstitial() {
        AdRequest interstitialRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(interstitialRequest);
    }

}
