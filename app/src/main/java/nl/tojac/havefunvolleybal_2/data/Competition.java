package nl.tojac.havefunvolleybal_2.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import nl.tojac.havefunvolleybal_2.Utils;

/**
 * Created by kstanoev on 1/14/2015.
 */
public class Competition {

    private String mCompetitionName, mCompetitionExtID, mCompStatus, mLinkToTeamSheet,mLinkToGameSheet, mLocation;
    private int mNumberOfPoules, mNumberOfFields;
    private long mStartDateInMilli, mEndDateInMilli;
    private Context mContext;

    public Competition(Context context, int CompDb_ID) {
        mContext = context;
        retrieveCompFromDB(CompDb_ID);
    }


    public Competition(Context context) {
        mContext = context;
    }



    public void retrieveCompFromDB(int CompDb_ID) {

        ContentResolver contentResolver = mContext.getContentResolver();

        String[] mSelectionArgs = {String.valueOf(CompDb_ID)};

        Cursor compData = contentResolver.query(
                CompetitieContract.CompetitionEntry.CONTENT_URI,
                null,
                CompetitieContract.CompetitionEntry._ID + " = ? ",
                mSelectionArgs,
                null);

        if (compData != null && compData.moveToFirst()){

        this.mCompetitionExtID  = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_EXT_COMP_ID));
        this.mCompStatus        = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_STATUS));
        this.mCompetitionName   = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_NAME));
        this.mLocation          = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LOCATION));
        this.mNumberOfPoules    = compData.getInt(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_NUMBER_OF_POULES));
        this.mNumberOfFields    = compData.getInt(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_NUMBER_OF_FIELDS));
        this.mLinkToTeamSheet   = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_TEAM_SHEET));
        this.mLinkToGameSheet   = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_GAME_SHEET));
        this.mStartDateInMilli  = compData.getLong(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_START_DATE));
        this.mEndDateInMilli    = compData.getLong(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_END_DATE));
        }

        compData.close();
    }

//    public void retrieveCompFromDB(Context context, String extComp_ID) {
//        ContentResolver contentResolver = context.getContentResolver();
//
//        String[] mSelectionArgs = {String.valueOf(extComp_ID)};
//
//        Cursor compData = contentResolver.query(
//                CompetitieContract.CompetitionEntry.CONTENT_URI,
//                null,
//                CompetitieContract.CompetitionEntry.COL_COMP_EXT_COMP_ID + " = ? ",
//                mSelectionArgs,
//                null);
//
//        this.mCompetitionExtID  = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_EXT_COMP_ID));
//        this.mCompStatus        = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_STATUS));
//        this.mCompetitionName   = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_NAME));
//        this.mLocation          = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LOCATION));
//        this.mNumberOfPoules    = compData.getInt(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_NUMBER_OF_POULES));
//        this.mNumberOfFields    = compData.getInt(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_NUMBER_OF_FIELDS));
//        this.mLinkToTeamSheet   = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_TEAM_SHEET));
//        this.mLinkToGameSheet   = compData.getString(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_LINK_TO_GAME_SHEET));
//        this.mStartDateInMilli  = compData.getLong(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_START_DATE));
//        this.mEndDateInMilli    = compData.getLong(compData.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_END_DATE));
//    }

    public String showTekst() {
        String verzamelText =
                "Comp id          :" + this.getCompetitionExtID()+ "\n" +
                "Comp naam        :" + this.getCompetitionName()+ "\n" +
                "Comp status      :" + this.getCompStatus()+ "\n" +
                "Comp locatie     :" + this.getLocation() + "\n" +
                "Comp # poules    :" + String.valueOf(this.getNumberOfPoules()) + "\n" +
                "Comp #velden     :" + String.valueOf(this.getNumberOfFields()) + "\n" +
                "Comp link team   :" + this.getLinkToTeamSheet() + "\n" +
                "Comp link game   :" + this.getLinkToGameSheet() + "\n" +
                "Comp start datum :" + this.getStartDate("dd-MM-yyyy") + "\n" +
                "Comp eind datum  :" + this.getEndDate("dd-MM-yyyy") + "\n"

                ;

        return verzamelText;
    }

    // Competition naam

    public String getCompetitionName() {

        return mCompetitionName;
    }



    // CompSheetID

    public String getCompetitionExtID() {

        return mCompetitionExtID;
    }

    public String getCompStatus(){
        return mCompStatus;
    }


    public String getLocation(){
        return mLocation;
    }


    public int getNumberOfPoules(){
        return mNumberOfPoules;
    }

    public int getNumberOfFields(){
        return mNumberOfFields;
    }

    public String getLinkToTeamSheet(){
        return mLinkToTeamSheet;
    }

    public String getLinkToGameSheet(){
        return mLinkToGameSheet;
    }

    public long getStartDateInMilli(){
        return mStartDateInMilli;
    }

    public long getEndDateInMilli(){
        return mEndDateInMilli;
    }

    public String getStartDate(String format){

        return Utils.getDateStringFromMilliseconds(getStartDateInMilli(),format);
    }

    public String getEndDate(String format){

        return Utils.getDateStringFromMilliseconds(getEndDateInMilli(),format);
    }

}
