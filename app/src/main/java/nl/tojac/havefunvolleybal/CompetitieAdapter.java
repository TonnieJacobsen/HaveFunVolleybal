package nl.tojac.havefunvolleybal;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import nl.tojac.havefunvolleybal.data.CompetitieContract;

/**
 * {@link CompetitieAdapter} exposes a list of competitions
 * from a {@link Cursor} to a {@link android.widget.ListView}.
 */
public class CompetitieAdapter extends CursorAdapter {

    private static final int VIEW_TYPE_COUNT = 1;
    private static final int VIEW_TYPE_COMPETITIONS = 0;


    public CompetitieAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public static class ViewHolder {

        public final ImageView compIconView;
        public final TextView  compNameView;
        public final TextView  compStartDate;
        public final TextView  compEndDate;
        public final TextView  compStatus;

        public ViewHolder(View view) {
            compIconView        = (ImageView) view.findViewById(R.id.li_comp_image);
            compNameView        = (TextView) view.findViewById(R.id.li_comp_name);
            compStartDate       = (TextView) view.findViewById(R.id.li_comp_start_date);
            compEndDate         = (TextView) view.findViewById(R.id.li_comp_end_date);
            compStatus          = (TextView) view.findViewById(R.id.li_comp_status);
        }
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.competition_listitem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setTag(viewHolder);

        Log.v("To1jac - " + this.getClass().getSimpleName(), "New view aangemaakt  ");

        return view;
    }





    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        long dmilli;

        ViewHolder viewHolder = (ViewHolder) view.getTag();



        viewHolder.compIconView.setImageResource(R.mipmap.comp_logo);



        viewHolder.compNameView.setText(cursor.getString(
                cursor.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_NAME)
        ));

        // Converteer de startdatum competitie van milliseconden naar een formaat
        // dat voor normale stervelingen leesbaar is

        dmilli = cursor.getLong(
                cursor.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_START_DATE)
        );

        viewHolder.compStartDate.setText(Utils.getDateStringFromMilliseconds(dmilli, "dd-MM-yyyy"));


        // Converteer de einddatum competitie van milliseconden naar een formaat
        // dat voor normale stervelingen leesbaar is

        dmilli = cursor.getLong(
                cursor.getColumnIndex(CompetitieContract.CompetitionEntry.COL_COMP_END_DATE)
        );

        viewHolder.compEndDate.setText(Utils.getDateStringFromMilliseconds(dmilli, "dd-MM-yyyy"));


        viewHolder.compStatus.setText(CompetitieContract.CompetitionEntry.COL_COMP_STATUS);



    }




    @Override
    public int getItemViewType(int position) {

        return  VIEW_TYPE_COMPETITIONS ;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }
}