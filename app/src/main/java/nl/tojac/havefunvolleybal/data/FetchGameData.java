package nl.tojac.havefunvolleybal.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.tojac.havefunvolleybal.Calculate_Results;
import nl.tojac.havefunvolleybal.Utils;

/**
 * Created by Tonnie on 30-12-2015.
 */
public class    FetchGameData {


    ContentResolver mResolver;
    Context mContext;
    int mLog;

    public static final int LOG_ON = 1;
    public static final int LOG_OFF = 0;



    public FetchGameData(final Context context){
        mContext = context;
        mResolver = mContext.getContentResolver();
        mLog = LOG_ON;

    }



    public void GetGameDataFromExternalSheet(String query){

        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processGameJson(object, mContext);
            }
        }).execute(query);

    }



    public int RemoveGameData(String competitionID){

        String[] mSelectionArgs = {competitionID};

        String mSelection = CompetitieContract.GameEntry.COL_GAME_ID_COMP + " = ? ";

        int rowsDeleted = mResolver.delete(CompetitieContract.GameEntry.CONTENT_URI,mSelection , mSelectionArgs);

        return rowsDeleted;
    }


    public int RemoveResultData(String competitionID){

        String[] mSelectionArgs = {competitionID};

        String mSelection = CompetitieContract.ResultEntry.COL_RESULT_COMP_ID + " = ? ";

        int rowsDeleted = mResolver.delete(CompetitieContract.ResultEntry.CONTENT_URI,mSelection , mSelectionArgs);

        return rowsDeleted;
    }

    private void processGameJson(JSONObject object, Context context) {


        String competitieID = "";
        String wedstrijdExtID = "";
        String speelVeld = "";
        String poule = "";
        String speelDatum = "";
        String speeltijd = "";
        String teamIdTeam1 = "";
        String teamNaamTeam1 = "";
        String teamIdTeam2 = "";
        String teamNaamTeam2 = "";
        String teamFluitenID = "";
        String teamFluitenNaam = "";
        String teamNetOpID = "";
        String teamNetOpNaam = "";
        String teamStandNaamTeam1 = "";
        int team1Set1 = 0;
        int team1Set2 = 0;
        int team1Set3 = 0;
        int team1Set4 = 0;
        int team1Set5 = 0;
        String teamStandNaamTeam2 = "";
        int team2Set1 = 0;
        int team2Set2 = 0;
        int team2Set3 = 0;
        int team2Set4 = 0;
        int team2Set5 = 0;





        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length() ; ++r) {


                if (r == 0) {

                    // De eerste rij van de Jason tabel bevat de kolomtitels
                    // voorlopig gaan we daar nog niets mee doen


                } else {


                    team1Set1 = 0;
                    team1Set2 = 0;
                    team1Set3 = 0;
                    team1Set4 = 0;
                    team1Set5 = 0;

                    team2Set1 = 0;
                    team2Set2 = 0;
                    team2Set3 = 0;
                    team2Set4 = 0;
                    team2Set5 = 0;


                    JSONObject row = rows.getJSONObject(r);
                    JSONArray columns = row.getJSONArray("c");

                    if (!columns.isNull(0)) {
                        competitieID = columns.getJSONObject(0).getString("v");
                    }

                    if (!columns.isNull(1)) {
                        wedstrijdExtID = columns.getJSONObject(1).getString("v");

                    }

                    if (!columns.isNull(2)) {
                        speelVeld = columns.getJSONObject(2).getString("v");
                    }
                    if (!columns.isNull(3)) {
                        poule = columns.getJSONObject(3).getString("v");
                    }
                    if (!columns.isNull(4)) {
                        speelDatum = columns.getJSONObject(4).getString("f");
                    }

                    if (!columns.isNull(5)) {
                        speeltijd = columns.getJSONObject(5).getString("f");
                    }
                    if (!columns.isNull(6)) {
                        teamIdTeam1 = columns.getJSONObject(6).getString("v");
                    }
                    if (!columns.isNull(7)) {
                        teamNaamTeam1 = columns.getJSONObject(7).getString("v");
                    }
                    if (!columns.isNull(8)) {
                        teamIdTeam2 = columns.getJSONObject(8).getString("v");
                    }
                    if (!columns.isNull(9)) {
                        teamNaamTeam2 = columns.getJSONObject(9).getString("v");
                    }
                    if (!columns.isNull(10)) {
                        teamFluitenID = columns.getJSONObject(10).getString("v");
                    }
                    if (!columns.isNull(11)) {
                        teamFluitenNaam = columns.getJSONObject(11).getString("v");
                    }
                    if (!columns.isNull(12)) {
                        teamNetOpID = columns.getJSONObject(12).getString("v");
                    }
                    if (!columns.isNull(13)) {
                        teamNetOpNaam = columns.getJSONObject(13).getString("v");
                    }
                    if (!columns.isNull(14)) {
                        teamStandNaamTeam1 = columns.getJSONObject(14).getString("v");
                    }
                    if (!columns.isNull(15)) {
                        if (!columns.getJSONObject(14).isNull("v")) {
                            team1Set1 = columns.getJSONObject(15).getInt("v");
                        }
                    }
                    if (!columns.isNull(16)) {
                        if (!columns.getJSONObject(15).isNull("v")) {
                            team1Set2 = columns.getJSONObject(16).getInt("v");
                        }
                    }
                    if (!columns.isNull(17)) {
                        if (!columns.getJSONObject(16).isNull("v")) {
                            team1Set3 = columns.getJSONObject(17).getInt("v");
                        }
                    }
                    if (!columns.isNull(18)) {
                        if (!columns.getJSONObject(17).isNull("v")) {
                            team1Set4 = columns.getJSONObject(18).getInt("v");
                        }
                    }
                    if (!columns.isNull(19)) {
                        if (!columns.getJSONObject(18).isNull("v")) {
                            team1Set5 = columns.getJSONObject(19).getInt("v");
                        }
                    }
                    if (!columns.isNull(20)) {
                        teamStandNaamTeam2 = columns.getJSONObject(20).getString("v");
                    }
                    if (!columns.isNull(21)) {
                        if (!columns.getJSONObject(21).isNull("v")) {
                            team2Set1 = columns.getJSONObject(21).getInt("v");
                        }
                    }
                    if (!columns.isNull(22)) {

                        if (!columns.getJSONObject(22).isNull("v")) {
                            team2Set2 = columns.getJSONObject(22).getInt("v");
                        }
                    }
                    if (!columns.isNull(23)) {
                        if (!columns.getJSONObject(23).isNull("v")) {
                            team2Set3 = columns.getJSONObject(23).getInt("v");
                        }
                    }
                    if (!columns.isNull(24)) {
                        if (!columns.getJSONObject(24).isNull("v")) {
                            team2Set4 = columns.getJSONObject(24).getInt("v");
                        }
                    }
                    if (!columns.isNull(25)) {
                        if (!columns.getJSONObject(25).isNull("v")) {
                            team2Set5 = columns.getJSONObject(25).getInt("v");
                        }
                    }

                    Wedstrijd wedstrijd = new Wedstrijd(
                            competitieID,
                            wedstrijdExtID,
                            speelVeld,
                            poule,
                            speelDatum,
                            speeltijd,
                            teamIdTeam1,
                            teamNaamTeam1,
                            teamIdTeam2,
                            teamNaamTeam2,
                            teamFluitenID,
                            teamFluitenNaam,
                            teamNetOpID,
                            teamNetOpNaam

                    );

                    Result resultTeam1 = new Result(
                            competitieID,
                            wedstrijdExtID,
                            teamIdTeam1,
                            team1Set1,
                            team1Set2,
                            team1Set3,
                            team1Set4,
                            team1Set5
                    );

                    Result resultTeam2 = new Result(
                            competitieID,
                            wedstrijdExtID,
                            teamIdTeam2,
                            team2Set1,
                            team2Set2,
                            team2Set3,
                            team2Set4,
                            team2Set5
                    );


                    Calculate_Results calc_Results = new Calculate_Results(resultTeam1, resultTeam2);

                    resultTeam1.setTotaLGamePoints(calc_Results.getTotalGamePointsTeam1());
                    resultTeam2.setTotaLGamePoints(calc_Results.getTotalGamePointsTeam2());




                    insertWedstrijdInDB(wedstrijd);
                    insertResultsInDB(resultTeam1);
                    insertResultsInDB(resultTeam2);


                }

            }
        } catch (JSONException e) {
            Log.v("Tojac", e.getMessage());

            e.printStackTrace();

        }
    }

    private void insertResultsInDB(Result result) {

        ContentValues teamValues = new ContentValues();
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_COMP_ID, result.getCompetitionID());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_GAME_ID, result.getGameExtID());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_TEAM_ID, result.getTeamExtID());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_SET_1, result.getSet1Points());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_SET_2,result.getSet2Points());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_SET_3,result.getSet3Points());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_SET_4,result.getSet4Points());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_SET_5,result.getSet5Points());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_TOTAL_SET_POINTS,result.getTotalSetPoints());
        teamValues.put(CompetitieContract.ResultEntry.COL_RESULT_TOTAL_GAME_POINTS,result.getTotalGamePoints());

        mResolver.insert(CompetitieContract.ResultEntry.CONTENT_URI, teamValues);

    }

    private void insertWedstrijdInDB(Wedstrijd wedstrijd) {





        ContentValues gameValues = new ContentValues();
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_EXT_GAME_ID, wedstrijd.getWedstrijdExtID());
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_ID_COMP, wedstrijd.getCompetitionId());


        gameValues.put(CompetitieContract.GameEntry.COL_GAME_DATE,
                Utils.getMillisecondsFromString(
                        wedstrijd.getSpeelDatum(),
                        "dd/MM/yyyy"));

        long tst =  Utils.getMillisecondsFromString(
                wedstrijd.getSpeelDatum(),
                "dd/MM/yyyy");

        gameValues.put(CompetitieContract.GameEntry.COL_GAME_TIME, wedstrijd.getSpeelTijd());
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_ID_TEAM1, wedstrijd.getTeamIdTeam1().trim());
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_ID_TEAM2, wedstrijd.getTeamIdTeam2().trim());
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_ID_REFEREE, wedstrijd.getTeamFluitenID().trim());
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_ID_ORG_PLAYF, wedstrijd.getTeamNetOpID().trim());
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_ID_POULE, 400);
        gameValues.put(CompetitieContract.GameEntry.COL_GAME_POULE, wedstrijd.getPoule());

        mResolver.insert(CompetitieContract.GameEntry.CONTENT_URI, gameValues);

    }




}
