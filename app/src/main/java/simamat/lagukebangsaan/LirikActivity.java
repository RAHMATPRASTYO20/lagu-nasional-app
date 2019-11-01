package simamat.lagukebangsaan;

import android.content.Intent;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


import maes.tech.intentanim.CustomIntent;
import simamat.lagukebangsaan.database.DatabaseAccess;

public class LirikActivity extends AppCompatActivity {

    private TextView tvPencipta;
    private TextView tvLirik;
    private WebView wvYouTube;

    private FloatingActionButton fabKaraoke;

    private AdView mAdView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lirik);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();
        mAdView.loadAd(adRequest);
        createInterstitial();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Intent intent = getIntent();
        final String judul = intent.getStringExtra("judul");

        final String judulLagu = databaseAccess.getJudul(judul);
        String pencipta = databaseAccess.getPencipta(judul);
        String lirik = databaseAccess.getLirik(judul);
        final String linkLagu = databaseAccess.getUrlVideo(judul);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(judulLagu);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(judulLagu);
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary));

        tvPencipta = (TextView) findViewById(R.id.tv_pencipta_lagu);
        tvLirik = (TextView) findViewById(R.id.tv_lirik_lagu);
        wvYouTube = (WebView) findViewById(R.id.videoWebView);
        fabKaraoke = (FloatingActionButton) findViewById(R.id.btn_karaoke);

        tvPencipta.setText(pencipta);
        tvLirik.setText(lirik);

        wvYouTube.getSettings().setJavaScriptEnabled(true);
        wvYouTube.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvYouTube.loadUrl("http://www.youtube.com/embed/" + linkLagu + "?autoplay=1&vq=small");
        wvYouTube.setWebChromeClient(new WebChromeClient());

        fabKaraoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            Intent intent = new Intent(LirikActivity.this, KaraokeActivity.class);
                            intent.putExtra("judul", judulLagu);
                            startActivity(intent);
                            /////////////////////////
                        }
                    });
                } else {
                    loadInterstitial();

                    /////////////////////////
                    Intent intent = new Intent(LirikActivity.this, KaraokeActivity.class);
                    intent.putExtra("judul", judulLagu);
                    startActivity(intent);
                    /////////////////////////
                }

            }
        });

        databaseAccess.close();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            wvYouTube.destroy();
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void finish() {
        super.finish();
        CustomIntent.customType(LirikActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        wvYouTube.destroy();
        finish();
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
