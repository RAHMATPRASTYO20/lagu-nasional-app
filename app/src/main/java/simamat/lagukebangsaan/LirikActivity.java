package simamat.lagukebangsaan;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;
import simamat.lagukebangsaan.database.DatabaseAccess;

public class LirikActivity extends AppCompatActivity {

    private TextView tvJudulLagu;
    private TextView tvPencipta;
    private TextView tvLirik;

    private ImageButton ibBack, ibPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lirik);



        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        String judul = databaseAccess.getJudul(id);
        String pencipta = databaseAccess.getPencipta(id);
        String lirik = databaseAccess.getLirik(id);
        final String linkLagu = databaseAccess.getUrlVideo(id);

        tvJudulLagu = (TextView) findViewById(R.id.tv_judul_lagu);
        tvPencipta = (TextView) findViewById(R.id.tv_pencipta_lagu);
        tvLirik = (TextView) findViewById(R.id.tv_lirik_lagu);
        ibBack = (ImageButton) findViewById(R.id.btn_back_activity);
        ibPlay = (ImageButton) findViewById(R.id.btn_play);

        tvJudulLagu.setText(judul);
        tvPencipta.setText(pencipta);
        tvLirik.setText(lirik);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String video_id = linkLagu;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video_id));
                startActivity(intent);
            }
        });

        databaseAccess.close();

    }

    public void finish() {
        super.finish();
        CustomIntent.customType(LirikActivity.this, "fadein-to-fadeout");
    }

}
