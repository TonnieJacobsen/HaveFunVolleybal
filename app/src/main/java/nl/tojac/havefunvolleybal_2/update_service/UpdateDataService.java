package nl.tojac.havefunvolleybal_2.update_service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import nl.tojac.havefunvolleybal_2.data.CompetitieContract;
import nl.tojac.havefunvolleybal_2.data.FetchGameData;
import nl.tojac.havefunvolleybal_2.data.FetchTeamData;

/**
 * Created by Tonnie on 7-2-2016.
 */
public class UpdateDataService extends IntentService {

    public UpdateDataService(){

        super("UpdateDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {






        // Ondestaande instantie zorgt dat alle wedstrijddata
        // wordt opgehaald en weg geschreven in de daarvoor
        // bestemde tabellen


        ContentResolver contentResolver = this.getContentResolver();


        Cursor compData = contentResolver.query(
                CompetitieContract.CompetitionEntry.CONTENT_URI,
                null,
                null,
                null,
                null);




        FetchTeamData fetchTeamData = new FetchTeamData(this);
        FetchGameData fetchGameData = new FetchGameData(this);

        int recordsRemoved;
        String compExtId;

if (compData.getCount()==0){
    Log.v("To1jac", " Comp data is leeg ");
}


        while (compData.moveToNext()) {

            compExtId = CompetitieContract.CompetitionEntry.COL_COMP_EXT_COMP_ID;

            // Verwijderen van alle teamrecords die horen bij het competitie id wat
            // wordt mee gegeven.

            recordsRemoved = fetchTeamData.RemoveTeamData(compData.getString(compData.getColumnIndex(compExtId)));

            Log.v("To1jac", "Er zijn " + String.valueOf(recordsRemoved) + " team regels verwijderd met id " + compData.getString(compData.getColumnIndex(compExtId)));


            // als link is mee gegeven voor de teamdata

            fetchTeamData.GetTeamDataFromExternalSheet(
                    compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_TEAM_SHEET))
            );

            // Verwijderen van alle gamerecords die horen bij het competitie id wat
            // wordt mee gegeven.

            recordsRemoved = fetchGameData.RemoveGameData(compData.getString(compData.getColumnIndex(compExtId)));



            Log.v("To1jac", "Er zijn " + String.valueOf(recordsRemoved) + " wedstrijdregels verwijderd met id " +
                    compData.getString(compData.getColumnIndex(compExtId)));


            // Verwijderen van alle resultrecorde die horen bij het competitie id dat
            // wordt mee gegeven.

            recordsRemoved = fetchGameData.RemoveResultData(compData.getString(compData.getColumnIndex(compExtId)));


            Log.v("To1jac", "Er zijn " + String.valueOf(recordsRemoved) + " resultaatregels verwijderd met id " +
                    compData.getString(compData.getColumnIndex(compExtId)));




            // Haal alle wedstrijddata op van het spreadsheet wat in de de oompetitire record
            // als link is mee gegeven voor de wedstrijddata


            fetchGameData.GetGameDataFromExternalSheet(
                    compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_GAME_SHEET))
            );

        }


        compData.close();

        Log.v("To1jac", "Update van competitie content is uitgevoerd");

    }

    static public class AlarmReciever extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            Intent sendIntent = new Intent(context,UpdateDataService.class);
            context.startService(sendIntent);


        }
    }

}
