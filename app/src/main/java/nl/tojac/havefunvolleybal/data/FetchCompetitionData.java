package nl.tojac.havefunvolleybal.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.tojac.havefunvolleybal.Utils;

/**
 * Created by Tonnie on 2-1-2016.
 */
public class FetchCompetitionData {

    public static final int LOG_ON = 1;
    public static final int LOG_OFF = 0;


    ContentResolver mResolver;
    int log;

    public FetchCompetitionData(final Context context, String competitioId) {

        log = LOG_ON;
        mResolver = context.getContentResolver();

        mResolver.delete(CompetitieContract.CompetitionEntry.CONTENT_URI, null, null);


        String query = Queries.selectCompetitions;

        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processCompetitionJson(object, context);
            }
        }).execute(query);


    }

    public void processCompetitionJson(JSONObject object, Context context) {



        try {
            JSONArray rows = object.getJSONArray("rows");
            JSONArray cols = object.getJSONArray("cols");

            ContentValues CompetitionValues = new ContentValues();
            Log.v("Tojac", "Ik ga nu de for loop in  ");

            // Even een uitprobeersel om te kijken of de kolommen
            // opgehaald kunnen worden. En dat is gelukt.
            // Wellicht iets om op een later tijdstip te gebruiken.


            for (int t = 0; t < cols.length(); ++t) {
                JSONObject row1 = cols.getJSONObject(t);

//                Log.v("To1jac", row1.getString("id"));
//                Log.v("To1jac", row1.getString("label"));
//                Log.v("To1jac", row1.getString("type"));
//
            }


            int colNumber;
            String colInDb;

            for (int r = 0; r < rows.length(); ++r) {


                JSONObject row = rows.getJSONObject(r);

                JSONArray columns = row.getJSONArray("c");


                // De eerste rij van de Jason tabel bevat de kolomtitels
                // voorlopig gaan we daar nog niets mee doen

//                if (r > 0) {

                colNumber = 0;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_EXT_COMP_ID;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Extern competition id is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");
                }

                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_STATUS;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Status is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }

                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_NAME ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Competitie naam is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }


                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_START_DATE ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("f")) {
                        CompetitionValues.put(colInDb,
                                Utils.getMillisecondsFromString(
                                        columns.getJSONObject(colNumber).getString("f"),
                                        "dd/MM/yyyy")
                );
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("f"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Competitie startdatum is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }

                colNumber ++;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_END_DATE ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("f")) {
                        CompetitionValues.put(colInDb,
                                Utils.getMillisecondsFromString(
                                        columns.getJSONObject(colNumber).getString("f"),
                                        "dd/MM/yyyy")
                        );
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("f"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Competitie eind datum is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }


                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_LOCATION ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Locatie is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }

                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_NUMBER_OF_POULES ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getInt("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Aantal poules is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }

                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_NUMBER_OF_FIELDS ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getInt("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Aantal velden is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }


                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_TEAM_SHEET ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Link naar teams is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }

                colNumber ++ ;
                colInDb = CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_GAME_SHEET ;

                if (!columns.isNull(colNumber)) {
                    if (!columns.getJSONObject(colNumber).isNull("v")) {
                        CompetitionValues.put(colInDb, columns.getJSONObject(colNumber).getString("v"));
                        if (log == LOG_ON) {
                            Log.v("To1jac", columns.getJSONObject(colNumber).getString("v"));
                        }
                    } else {
                        CompetitionValues.put(colInDb, "");
                        if (log == LOG_ON) {
                            Log.v("To1jac", "Link naar wedstrijden is niet gevuld");
                        }
                    }

                } else {
                    CompetitionValues.put(colInDb, "");

                }



                    mResolver.insert(CompetitieContract.CompetitionEntry.CONTENT_URI, CompetitionValues);


            }
            //          }


        } catch (JSONException e) {

            e.printStackTrace();
            Log.v("Tojac", "Gaat fouttttttt  ");

        }
    }


}
