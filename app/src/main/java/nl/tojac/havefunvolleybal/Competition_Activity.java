package nl.tojac.havefunvolleybal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





//        CompetitionFragment infoFragment = new CompetitionFragment();
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.competition_info_container, infoFragment, COMP_INFO_FRAGMENT_TAG)
//                .commit();

        CompetitionFragment fragment = new CompetitionFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.competition_list_container, fragment, COMP_LIST_FRAGMENT_TAG)
                .commit();

    }

    @Override
    public void onItemSelected(Uri contentUri) {

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
            Intent intent = new Intent(this, Games_Activity.class)
                    .setData(contentUri);

            startActivity(intent);

//            Toast.makeText(this,contentUri.toString(),Toast.LENGTH_SHORT).show();

        }
    }
}




