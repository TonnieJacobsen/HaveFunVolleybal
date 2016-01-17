package nl.tojac.havefunvolleybal;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import nl.tojac.havefunvolleybal.data.CompetitieContract;
import nl.tojac.havefunvolleybal.data.Competition;

/**
 * A placeholder fragment containing a simple view.
 */
public class CompetitionFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CompetitieAdapter mCompAdapter;
    private static final int COMP_LOADER = 0;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private Competition mCompetition;





    public CompetitionFragment() {
    }

    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(int compId);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v("To1jac - " + this.getClass().getSimpleName(), "OncreateView ");
        mCompAdapter = new CompetitieAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.competition_list_fragment, container, false);
        mListView = (ListView) rootView.findViewById(R.id.lv_competition);


        mListView.setAdapter(mCompAdapter);






        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if (cursor != null ) {
                    // Onderstaande Callback geeft de geselecteerde team  + _id terug aan
                    // de activity

                    ((Callback) getActivity())
                            .onItemSelected(cursor.getInt(
                                            cursor.getColumnIndex(
                                                    CompetitieContract.CompetitionEntry._ID)
                                    ));

                    mPosition = position;


                }else {
                    Log.e("Tojac", "Cursor is leeg in de OnItemClickListener ");
                }
                cursor.close();
            }
        });

        return rootView;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.v("To1jac - " + this.getClass().getSimpleName(), "OnActivityCreated ");


        getLoaderManager().initLoader(COMP_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (mPosition != ListView.INVALID_POSITION) {

            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Log.v("To1jac - " + this.getClass().getSimpleName(), "OncreateLoader ");


        // Sorteer volgordeCompetitieContract.CompetitionEntry.COMPETITION_COLUMNS
        String sortOrder = null;

        Uri compUri = CompetitieContract.CompetitionEntry.CONTENT_URI;

        CursorLoader cursor = new CursorLoader(getActivity(),

                compUri,
                null,
                null,
                null,
                null);



        return cursor;
    }




    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.v("To1jac - " + this.getClass().getSimpleName(), "onLoaderReset ");
        mCompAdapter.swapCursor(null);

    }




    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.v("To1jac - " + this.getClass().getSimpleName(), "onLoadFinished ");
        mCompAdapter.swapCursor(cursor);

        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore
            // to, do so now.
            mListView.smoothScrollToPosition(mPosition);
            mListView.setItemChecked(mPosition, true);
        }

    }


}
