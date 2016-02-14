package nl.tojac.havefunvolleybal_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Competition_Activity extends AppCompatActivity implements CompetitionFragment.Callback {

    private static final String COMP_INFO_FRAGMENT_TAG = "COMPINFOTAG";
    private static final String COMP_LIST_FRAGMENT_TAG = "COMPLISTTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competition_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Competition_Activity.this, "Binnenkort mogelijk om een mail te sturen naar competitie leiding", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





     CompetitionFragmentInfo infoFragment = new CompetitionFragmentInfo();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.competition_info_container, infoFragment, COMP_INFO_FRAGMENT_TAG)
                .commit();

        CompetitionFragment fragment = new CompetitionFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.competition_list_container, fragment, COMP_LIST_FRAGMENT_TAG)
                .commit();

    }

    public void startReserveLijst(View view) {

        Toast.makeText(Competition_Activity.this, "Binnenkort toegang tot de reservelijst", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(int comp_ID) {

        boolean mTwoPane = false;
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
//            // fragment transaction.
//            Bundle args = new Bundle();
//            args.putParcelable(DetailFragment.DETAIL_URI, contentUri);
//
//            DetailFragment fragment = new DetailFragment();
//            fragment.setArguments(args);
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.weather_detail_container, fragment, DETAILFRAGMENT_TAG)
//                    .commit();
        } else {



            Intent intent = new Intent(this, Tabbed_Games.class)
                    .putExtra("COMP_ID", comp_ID);

            startActivity(intent);

//          Toast.makeText(this, String.valueOf(comp_ID), Toast.LENGTH_SHORT).show();

        }
    }
}




