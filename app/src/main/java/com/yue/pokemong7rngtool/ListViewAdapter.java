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
            holder.status = (TextView) v.findViewById(R.id.expected_status);
            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }

        holder.status.setText(getItem(position).status);

        return v;
    }

    /**
     * View holder for the views we need access to
     */
    private class Holder {
        public TextView status;
    }

    public static class SampleItem {
        public String status;

        public SampleItem(String status) {
            this.status = status;
        }
    }
}