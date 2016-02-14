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

//        Bundle args1 = new Bundle();
//        args1.putCharSequence("Poule", "A");
//        args1.putCharSequence("Ext_Comp_ID", mCompetition.getCompetitionExtID());
//        fragment1.setArguments(args1);
//        Bundle extras = getIntent().getExtras();

        gameDetailActivityFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_detail_container, gameDetailActivityFragment)
                .commit();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
