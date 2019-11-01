package simamat.lagukebangsaan;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import simamat.lagukebangsaan.database.DatabaseAccess;

public class KaraokeActivity extends AppCompatActivity {

    private TextView tvPencipta;
    private TextView tvLirik;
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
        String pencipta = databaseAccess.getPencipta(judul);
        String lirik = databaseAccess.getLirik(judul);
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

        tvPencipta = (TextView) findViewById(R.id.tv_pencipta_lagu);
        tvLirik = (TextView) findViewById(R.id.tv_lirik_lagu);
        wvKaraokeYouTube = (WebView) findViewById(R.id.videoKaraokeWebView);

        tvPencipta.setText(pencipta);
        tvLirik.setText(lirik);

        wvKaraokeYouTube.getSettings().setJavaScriptEnabled(true);
        wvKaraokeYouTube.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvKaraokeYouTube.loadUrl("http://www.youtube.com/embed/" + linkLagu + "?autoplay=1&vq=small");
        wvKaraokeYouTube.setWebChromeClient(new WebChromeClient());

    }
}
