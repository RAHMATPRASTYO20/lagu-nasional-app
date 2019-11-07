package simamat.lagukebangsaan;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import maes.tech.intentanim.CustomIntent;
import simamat.lagukebangsaan.database.DatabaseAccess;

import static android.widget.Toast.LENGTH_LONG;

public class KaraokeActivity extends AppCompatActivity {

    private TextView tvLirik, tvKetukan;
    private WebView wvKaraokeYouTube;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaoke);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();
        mAdView.loadAd(adRequest);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Intent intent = getIntent();
        final String judul = intent.getStringExtra("judul");

        final String judulLagu = databaseAccess.getJudul(judul);
        String lirik = databaseAccess.getLirik(judul);
        String ketukan = databaseAccess.getKetukan(judul);
        final String linkLagu = databaseAccess.getUrlVideoKaraoke(judul);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(judulLagu);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(judulLagu);
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary));

        tvKetukan = (TextView) findViewById(R.id.tv_ketukan_lagu);
        tvLirik = (TextView) findViewById(R.id.tv_lirik_lagu);
        wvKaraokeYouTube = (WebView) findViewById(R.id.videoKaraokeWebView);

        tvKetukan.setText(ketukan);
        tvLirik.setText(lirik);

        if (adaInternet()) {
            wvKaraokeYouTube.getSettings().setJavaScriptEnabled(true);
            wvKaraokeYouTube.getSettings().setPluginState(WebSettings.PluginState.ON);
            wvKaraokeYouTube.loadUrl("http://www.youtube.com/embed/" + linkLagu + "?autoplay=1&vq=small");
            wvKaraokeYouTube.setWebChromeClient(new WebChromeClient());
        } else {
            wvKaraokeYouTube.loadDataWithBaseURL("file:///android_asset/",
                    "<img src='no_internet.jpg' />",
                    "text/html", "utf-8", null);
            Toast.makeText(KaraokeActivity.this, "Tidak Ada Koneksi Internet!", LENGTH_LONG).show();
        }

        databaseAccess.close();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            wvKaraokeYouTube.destroy();
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void finish() {
        super.finish();
        CustomIntent.customType(KaraokeActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        wvKaraokeYouTube.destroy();
        finish();
    }

    private boolean adaInternet(){
        ConnectivityManager koneksi = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return koneksi.getActiveNetworkInfo() != null;
    }

}
