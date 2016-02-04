package nl.tojac.havefunvolleybal_2;

import nl.tojac.havefunvolleybal_2.data.Result;

/**
 * Created by Tonnie on 24-12-2015.
 */
public class Calculate_Results {

    private int mTotalSetPointsTeam1;
    private int mTotalSetPointsTeam2;
    private int mTotalGamePointsTeam1;
    private int mTotalGamePointsTeam2;

    public Calculate_Results(Result ResultTeam1,Result ResultTeam2){
        mTotalGamePointsTeam1 = Calculate_Total_SetPoints(ResultTeam1);
        mTotalGamePointsTeam2 = Calculate_Total_SetPoints(ResultTeam2);
        mTotalGamePointsTeam1 = Calculate_Total_GamePoints(ResultTeam1,ResultTeam2);
        mTotalGamePointsTeam2 = Calculate_Total_GamePoints(ResultTeam2,ResultTeam1);
    }

    private int Calculate_Total_SetPoints(Result resultTeam){

        int total_Set_Points =
                resultTeam.getSet1Points() +
                resultTeam.getSet2Points() +
                resultTeam.getSet2Points() +
                resultTeam.getSet2Points() +
                resultTeam.getSet2Points() ;
        return total_Set_Points;
    }

    private int Calculate_Total_GamePoints(Result teamToBeCalc, Result teamToBeComparedWith){
        int total_game_Points = 0;

        // Indien de setstand van beide teams 0 is dan hoef je niets te doen
        // Is dit niet het geval. Kijk dan of TeamToBeCalc meer setpoints heeft
        // teamToBeComparedWith. In dat geval heeft team to be Calculated gewonnen
        // En wordt er dus een punt opgeteld bij het aantal gamepoints

        if (teamToBeCalc.getSet1Points() != 0 & teamToBeComparedWith.getSet1Points()!= 0){
            if (teamToBeCalc.getSet1Points() > teamToBeComparedWith.getSet1Points()){
                total_game_Points = total_game_Points + 1;

            }
        }

        if (teamToBeCalc.getSet2Points() != 0 & teamToBeComparedWith.getSet2Points()!= 0){
            if (teamToBeCalc.getSet2Points() > teamToBeComparedWith.getSet2Points()){
                total_game_Points = total_game_Points + 1;

            }
        }

        if (teamToBeCalc.getSet3Points() != 0 & teamToBeComparedWith.getSet3Points()!= 0){
            if (teamToBeCalc.getSet3Points() > teamToBeComparedWith.getSet3Points()){
                total_game_Points = total_game_Points + 1;

            }
        }

        if (teamToBeCalc.getSet4Points() != 0 & teamToBeComparedWith.getSet4Points()!= 0){
            if (teamToBeCalc.getSet4Points() > teamToBeComparedWith.getSet4Points()){
                total_game_Points = total_game_Points + 1;

            }
        }

        if (teamToBeCalc.getSet5Points() != 0 & teamToBeComparedWith.getSet5Points()!= 0){
            if (teamToBeCalc.getSet5Points() > teamToBeComparedWith.getSet5Points()){
                total_game_Points = total_game_Points + 1;

            }
        }


        return total_game_Points;

    }

    public int getTotalSetPointsTeam1() {
        return mTotalSetPointsTeam1;
    }

    public int getTotalSetPointsTeam2() {
        return mTotalSetPointsTeam2;
    }

    public int getTotalGamePointsTeam1() {
        return mTotalGamePointsTeam1;
    }

    public int getTotalGamePointsTeam2() {
        return mTotalGamePointsTeam2;
    }

}
