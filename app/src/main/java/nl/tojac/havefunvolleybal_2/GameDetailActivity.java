package nl.tojac.havefunvolleybal_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class GameDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_detail_acti);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        GameDetailActivityFragment gameDetailActivityFragment = new GameDetailActivityFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_detail_container, gameDetailActivityFragment)
                .commit();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
