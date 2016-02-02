package nl.tojac.havefunvolleybal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.tojac.havefunvolleybal.data.CompetitieContract;
import nl.tojac.havefunvolleybal.data.Wedstrijd;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameDetailActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public GameDetailActivityFragment() {
    }

    private TextView mSpeelDatumView;
    private TextView mSpeelTijdView;
    private TextView mSpeelVeldView;
    private TextView mTeam1View;
    private TextView mTeam2View;
    private TextView mSet1Team1;
    private TextView mSet2Team1;
    private TextView mSet3Team1;
    private TextView mSet4Team1;
    private TextView mSet5Team1;
    private TextView mSet1Team2;
    private TextView mSet2Team2;
    private TextView mSet3Team2;
    private TextView mSet4Team2;
    private TextView mSet5Team2;


    private static final int GAME_DETAIL_LOADER = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Bundle arguments = getArguments();
//        if (arguments != null){
//
//            mUri = arguments.getParcelable(TeamDetailFragment.DETAIL_URI);
//
//        }

        Log.v("To3jac", "onCreatview  ");

        View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);


        mSpeelDatumView = (TextView) rootView.findViewById(R.id.gd_tv_speeldatum);
        mSpeelTijdView = (TextView) rootView.findViewById(R.id.gd_tv_speeltijd);
        mSpeelVeldView = (TextView) rootView.findViewById(R.id.gd_tv_speelveld);
        mTeam1View = (TextView) rootView.findViewById(R.id.gd_tv_team1);
        mTeam2View = (TextView) rootView.findViewById(R.id.gd_tv_team2);
        mSet1Team1 = (TextView) rootView.findViewById(R.id.gd_tv_set1_team1);
        mSet2Team1 = (TextView) rootView.findViewById(R.id.gd_tv_set2_team1);
        mSet3Team1 = (TextView) rootView.findViewById(R.id.gd_tv_set3_team1);
        mSet4Team1 = (TextView) rootView.findViewById(R.id.gd_tv_set4_team1);
        mSet5Team1 = (TextView) rootView.findViewById(R.id.gd_tv_set5_team1);
        mSet1Team2 = (TextView) rootView.findViewById(R.id.gd_tv_set1_team2);
        mSet2Team2 = (TextView) rootView.findViewById(R.id.gd_tv_set2_team2);
        mSet3Team2 = (TextView) rootView.findViewById(R.id.gd_tv_set3_team2);
        mSet4Team2 = (TextView) rootView.findViewById(R.id.gd_tv_set4_team2);
        mSet5Team2 = (TextView) rootView.findViewById(R.id.gd_tv_set5_team2);
        return rootView;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(GAME_DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

       CursorLoader cursor = new CursorLoader(getActivity());

        if (id == GAME_DETAIL_LOADER) {

            String mSelection = "G1." + CompetitieContract.GameEntry._ID + " = ?";

            String[] mSelectionArgs = new String[1];
            mSelectionArgs[0] = "3";

            cursor.setUri(CompetitieContract.GameEntry.CONTENT_URI);
            cursor.setProjection(CompetitieContract.GameEntry.GAME_COLUMNS_WITH_TEAMS_AND_RESULTS);
            cursor.setSelection(mSelection);
            cursor.setSelectionArgs(mSelectionArgs);
            cursor.setSortOrder(null);

        }

        return cursor;
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {

            Wedstrijd wedstrijd = new Wedstrijd(data);

            mSpeelDatumView.setText(wedstrijd.getSpeelDatum());
            mSpeelTijdView.setText(wedstrijd.getSpeelTijd());
            mSpeelVeldView.setText(wedstrijd.getSpeelVeld());
            mTeam1View.setText(wedstrijd.getTeamNaamTeam1());
            mTeam2View.setText(wedstrijd.getTeamNaamTeam2());
            mSet1Team1.setText(wedstrijd.getTeam1Set1());
            mSet2Team1.setText(wedstrijd.getTeam1Set2());
            mSet3Team1.setText(wedstrijd.getTeam1Set3());
            mSet4Team1.setText(wedstrijd.getTeam1Set4());
            mSet5Team1.setText(wedstrijd.getTeam1Set5());
            mSet1Team2.setText(wedstrijd.getTeam2Set1());
            mSet2Team2.setText(wedstrijd.getTeam2Set2());
            mSet3Team2.setText(wedstrijd.getTeam2Set3());
            mSet4Team2.setText(wedstrijd.getTeam2Set4());
            mSet5Team2.setText(wedstrijd.getTeam2Set5());

        }
    }


}





