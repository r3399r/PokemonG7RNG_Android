package com.yue.pokemong7rngtool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<ListViewAdapter.SampleItem> {

    public ListViewAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        final Holder holder;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.data_list, null);
            holder = new Holder();
            holder.frameDisplay = (TextView) v.findViewById(R.id.col_frame);
            holder.frameAdvDisplay = (TextView) v.findViewById(R.id.col_frames_adv);
            holder.hpDisplay = (TextView) v.findViewById(R.id.col_hp);
            holder.atkDisplay = (TextView) v.findViewById(R.id.col_atk);
            holder.defDisplay = (TextView) v.findViewById(R.id.col_def);
            holder.spaDisplay = (TextView) v.findViewById(R.id.col_spa);
            holder.spdDisplay = (TextView) v.findViewById(R.id.col_spd);
            holder.speDisplay = (TextView) v.findViewById(R.id.col_spe);
            holder.genderDisplay = (TextView) v.findViewById(R.id.col_gender);
            holder.abilityDisplay = (TextView) v.findViewById(R.id.col_ability);
            holder.natureDisplay = (TextView) v.findViewById(R.id.col_nature);

            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }

        holder.frameDisplay.setText("" + getItem(position).frameDisplay);
        holder.frameAdvDisplay.setText("" + getItem(position).frameAdvDisplay);
        holder.hpDisplay.setText("" + getItem(position).hpDisplay);
        holder.atkDisplay.setText("" + getItem(position).atkDisplay);
        holder.defDisplay.setText("" + getItem(position).defDisplay);
        holder.spaDisplay.setText("" + getItem(position).spaDisplay);
        holder.spdDisplay.setText("" + getItem(position).spdDisplay);
        holder.speDisplay.setText("" + getItem(position).speDisplay);
        holder.genderDisplay.setText(getItem(position).genderDisplay);
        holder.abilityDisplay.setText("" + getItem(position).abilityDisplay);
        holder.natureDisplay.setText(getItem(position).natureDisplay);

        return v;
    }

    /**
     * View holder for the views we need access to
     */
    private class Holder {
        public TextView frameDisplay;
        public TextView frameAdvDisplay;
        public TextView hpDisplay;
        public TextView atkDisplay;
        public TextView defDisplay;
        public TextView spaDisplay;
        public TextView spdDisplay;
        public TextView speDisplay;
        public TextView genderDisplay;
        public TextView abilityDisplay;
        public TextView natureDisplay;
    }

    public static class SampleItem {
        public int frameDisplay;
        public int frameAdvDisplay;
        public int hpDisplay;
        public int atkDisplay;
        public int defDisplay;
        public int spaDisplay;
        public int spdDisplay;
        public int speDisplay;
        public String genderDisplay;
        public String abilityDisplay;
        public String natureDisplay;

        public SampleItem(int frame, int frameAdv, int hp, int atk, int def, int spa, int spd, int spe, String gender, String ability, String nature) {
            this.frameDisplay = frame;
            this.frameAdvDisplay = frameAdv;
            this.hpDisplay = hp;
            this.atkDisplay = atk;
            this.defDisplay = def;
            this.spaDisplay = spa;
            this.spdDisplay = spd;
            this.speDisplay = spe;
            this.genderDisplay = gender;
            this.abilityDisplay = ability;
            this.natureDisplay = nature;
        }
    }
}