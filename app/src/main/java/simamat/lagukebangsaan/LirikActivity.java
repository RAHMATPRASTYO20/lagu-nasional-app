package simamat.lagukebangsaan;

import android.content.Intent;

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
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import maes.tech.intentanim.CustomIntent;
import simamat.lagukebangsaan.database.DatabaseAccess;

public class LirikActivity extends AppCompatActivity {

//    public static final String EXTRA_JUDUL = "judul";

    private TextView tvJudulLagu;
    private TextView tvPencipta;
    private TextView tvLirik;
    private WebView wvYouTube;

    private ImageButton ibBack, ibPlay;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lirik);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();
        mAdView.loadAd(adRequest);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Intent intent = getIntent();
        final String judul = intent.getStringExtra("judul");

        String judulLagu = databaseAccess.getJudul(judul);
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
//        tvJudulLagu = (TextView) findViewById(R.id.tv_judul_lagu);
//        ibBack = (ImageButton) findViewById(R.id.btn_back_activity);
//        ibPlay = (ImageButton) findViewById(R.id.btn_play);

        tvPencipta.setText(pencipta);
        tvLirik.setText(lirik);
        String videoID = "4TCU2mKmi-U";

        wvYouTube.getSettings().setJavaScriptEnabled(true);
        wvYouTube.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvYouTube.loadUrl("http://www.youtube.com/embed/" + linkLagu + "?autoplay=1&vq=small");
        wvYouTube.setWebChromeClient(new WebChromeClient());
//        tvJudulLagu.setText(judulLagu);
//        ibBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        ibPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String video_id = linkLagu;
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video_id));
//                startActivity(intent);
//            }
//        });

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
}
