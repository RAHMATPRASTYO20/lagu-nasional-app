package simamat.lagukebangsaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private Button btnIndonesiaRaya,btnHymnePahlawan,btnGarudaPancasila,btnBangunPemudiPemuda,
            btnHariMerdeka,btnGugurBunga,btnIndonesiaPusaka,btnTanahAirku,btnBerkibarlahBenderaku,
            btnBagimuNegeri,btnMajuTakGentar,btnSyukur;


//    public static final int REQUEST_CODE_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext() , getString(R.string.banner_app_id));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();
        mAdView.loadAd(adRequest);

        CustomIntent.customType(MainActivity.this, "left-to-right");

        btnIndonesiaRaya = (Button) findViewById(R.id.btn_indonesia_raya);
        btnHymnePahlawan = (Button) findViewById(R.id.btn_hymne_pahlawan);
        btnGarudaPancasila = (Button) findViewById(R.id.btn_garuda_pancasila);
        btnBangunPemudiPemuda = (Button) findViewById(R.id.btn_bangun_pemuda_pemudi);
        btnHariMerdeka = (Button) findViewById(R.id.btn_hari_merdeka);
        btnGugurBunga = (Button) findViewById(R.id.btn_gugur_bunga);
        btnIndonesiaPusaka = (Button) findViewById(R.id.btn_indonesia_pusaka);
        btnTanahAirku = (Button) findViewById(R.id.btn_tanah_airku);
        btnBerkibarlahBenderaku = (Button) findViewById(R.id.btn_berkibarlah_benderaku);
        btnBagimuNegeri = (Button) findViewById(R.id.btn_bagimu_negeri);
        btnMajuTakGentar = (Button) findViewById(R.id.btn_maju_tak_gentar);
        btnSyukur = (Button) findViewById(R.id.btn_syukur);

        btnIndonesiaRaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "1");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnHymnePahlawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "2");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnGarudaPancasila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "3");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnBangunPemudiPemuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "4");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnHariMerdeka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "5");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnGugurBunga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "6");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnIndonesiaPusaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "7");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnTanahAirku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "8");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnBerkibarlahBenderaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "9");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnBagimuNegeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "10");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnMajuTakGentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "11");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });
        btnSyukur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, LirikActivity.class);
                move.putExtra("id", "12");
                startActivity(move);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

    }


}
