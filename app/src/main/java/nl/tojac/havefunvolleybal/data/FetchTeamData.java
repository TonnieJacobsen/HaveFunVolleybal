package nl.tojac.havefunvolleybal.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.tojac.havefunvolleybal.R;

/**
 * Created by Tonnie on 30-12-2015.
 */
public class FetchTeamData {

   ContentResolver mResolver;

   public FetchTeamData(final Context context, String competitioId){
       mResolver = context.getContentResolver();
       mResolver.delete(CompetitieContract.TeamEntry.CONTENT_URI, null, null);


       String query = Queries.selectTeams;
//    matches.clear();
       new DownloadWebpageTask(new AsyncResult() {
           @Override
           public void onResult(JSONObject object) {
               processTeamJson(object, context);
           }
       }).execute(query);


   }


    public void processTeamJson(JSONObject object, Context context) {




        try {
            JSONArray rows = object.getJSONArray("rows");

            ContentValues teamValues = new ContentValues();

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                // De eerste rij van de Jason tabel bevat de kolomtitels
                // voorlopig gaan we daar nog niets mee doen

                if (r > 0) {





                    // Voorlopig even gevuld met fakedata

                    teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_LEADER_ID, 10);
                    teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_PICTURE_ID, R.mipmap.ic_launcher);


                    if (!columns.isNull(0)) {
                        if (!columns.getJSONObject(0).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_NAME, columns.getJSONObject(0).getString("v"));
                        }
                    }
                    if (!columns.isNull(1)) {
                        if (!columns.getJSONObject(1).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_EXT_TEAM_ID, columns.getJSONObject(1).getString("v"));
                        }
                    }


                    if (!columns.isNull(2)) {
                        if (!columns.getJSONObject(2).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_LEADER_NAME, columns.getJSONObject(2).getString("v"));

                        }
                    }

                    if (!columns.isNull(3)) {
                        if (!columns.getJSONObject(3).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_POULE, columns.getJSONObject(3).getString("v"));

                        }
                    }


                    if (!columns.isNull(4)) {
                        if (!columns.getJSONObject(4).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_COMPETITION_ID, columns.getJSONObject(4).getString("v"));

                        }
                    }



                    // Teamlid 1 ophalen indien aanwezig
                    if (!columns.isNull(5)) {
                        if (!columns.getJSONObject(5).isNull("v")) {

                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_1, columns.getJSONObject(5).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_1, "");
                        }

                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_1, "");
                    }


                    // Teamlid 2 ophalen indien aanwezig
                    if (!columns.isNull(6)) {
                        if (!columns.getJSONObject(6).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_2, columns.getJSONObject(6).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_2, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_2, "");
                    }


                    // Teamlid 3 ophalen indien aanwezig
                    if (!columns.isNull(7)) {
                        if (!columns.getJSONObject(7).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_3, columns.getJSONObject(7).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_3, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_3, "");
                    }

                    // Teamlid 4 ophalen indien aanwezig
                    if (!columns.isNull(8)) {
                        if (!columns.getJSONObject(8).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_4, columns.getJSONObject(8).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_4, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_4, "");
                    }
                    // Teamlid 5 ophalen indien aanwezig
                    if (!columns.isNull(9)) {
                        if (!columns.getJSONObject(9).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_5, columns.getJSONObject(9).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_5, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_5, "");
                    }
                    // Teamlid 6 ophalen indien aanwezig
                    if (!columns.isNull(10)) {
                        if (!columns.getJSONObject(10).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_6, columns.getJSONObject(10).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_6, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_6, "");
                    }
                    // Teamlid 7 ophalen indien aanwezig
                    if (!columns.isNull(11)) {
                        if (!columns.getJSONObject(11).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_7, columns.getJSONObject(11).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_7, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_7, "");
                    }


                    // Teamlid 8 ophalen indien aanwezig
                    if (!columns.isNull(12)) {
                        if (!columns.getJSONObject(12).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_8, columns.getJSONObject(12).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_8, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_8, "");
                    }

                    // Teamlid 9 ophalen indien aanwezig
                    if (!columns.isNull(13)) {
                        if (!columns.getJSONObject(13).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_9, columns.getJSONObject(13).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_9, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_9, "");
                    }

                    // Teamlid 10 ophalen indien aanwezig
                    if (!columns.isNull(14)) {
                        if (!columns.getJSONObject(14).isNull("v")) {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_10, columns.getJSONObject(14).getString("v"));

                        } else {
                            teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_10, "");
                        }
                    }else {
                        teamValues.put(CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_10, "");
                    }






                    mResolver.insert(CompetitieContract.TeamEntry.CONTENT_URI, teamValues);


                }
            }


        } catch (JSONException e) {

            e.printStackTrace();
            Log.v("Tojac", "Gaat fouttttttt  ");

        }
    }



}
