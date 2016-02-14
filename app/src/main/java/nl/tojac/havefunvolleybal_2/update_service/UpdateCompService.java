package nl.tojac.havefunvolleybal_2.update_service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import nl.tojac.havefunvolleybal_2.data.FetchCompetitionData;

/**
 * Created by Tonnie on 8-2-2016.
 */
public class UpdateCompService extends IntentService {

    public UpdateCompService(){


        super("UpdateCompService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        new FetchCompetitionData(this);
    }



    static public class AlarmReciever extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {

            Intent sendIntent = new Intent(context,UpdateCompService.class);
            context.startService(sendIntent);


        }
    }
}
