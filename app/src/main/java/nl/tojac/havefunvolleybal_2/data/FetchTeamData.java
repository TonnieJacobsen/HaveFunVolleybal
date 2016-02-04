package nl.tojac.havefunvolleybal_2.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.tojac.havefunvolleybal_2.R;

/**
 * Created by Tonnie on 30-12-2015.
 */
public class FetchTeamData {

   ContentResolver mResolver;
   Context mContext;
   int mLog;

    public static final int LOG_ON = 1;
    public static final int LOG_OFF = 0;


    public static final String TEAM_TABEL_NAME = CompetitieContract.TeamEntry.TABLE_NAME;

   public FetchTeamData( Context context){
       mContext = context;
       mResolver = mContext.getContentResolver();
       mLog = LOG_ON;




   }


    public void GetTeamDataFromExternalSheet(String query){

        new DownloadWebpageTask(new AsyncResult() {
           @Override
           public void onResult(JSONObject object) {
               processTeamJson(object, mContext);
           }
       }).execute(query);

    }




    public int RemoveTeamData(String competitionID){

       String[] mSelectionArgs = {competitionID};

       String mSelection = CompetitieContract.TeamEntry.COL_TEAM_COMPETITION_ID + " = ? ";

        int rowsDeleted = mResolver.delete(CompetitieContract.TeamEntry.CONTENT_URI,mSelection , mSelectionArgs);

        return rowsDeleted;
    }












    public void processTeamJson(JSONObject object, Context context) {


        int colNumber;
        String colInDb;
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



                    colNumber = 0;
                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_NAME;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Extern competition id is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }

                    colNumber++;
                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_EXT_TEAM_ID;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Extern competition id is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }

                    colNumber++;
                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_LEADER_NAME;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Extern competition id is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }


                    colNumber++;
                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_POULE;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Extern competition id is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }



                    colNumber++;
                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_COMPETITION_ID;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Extern competition id is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }



                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_1;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 1 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }

                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_2;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 2 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }


                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_3;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 3 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }



                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_4;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 4 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }



                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_5;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 5 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }


                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_6;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 6 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }


                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_7;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 7 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }


                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_8;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 8 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }


                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_9;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 9 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
                    }

                    colNumber++;

                    colInDb = CompetitieContract.TeamEntry.COL_TEAM_TEAMLID_10;

                    if (!columns.isNull(colNumber)) {
                        if (!columns.getJSONObject(colNumber).isNull("v")) {
                            teamValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                            }
                        } else {
                            teamValues.put(colInDb, "");
                            if (mLog == LOG_ON) {
                                Log.v("To1jac", "Teamlid 10 is niet gevuld");
                            }
                        }

                    } else {
                        teamValues.put(colInDb, "");
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
