package com.unas.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Util.Util;
import modelo.Detail;


public class DetailActivity extends Activity {

    private ListView lvDetails;
    private int indElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail);

        lvDetails = (ListView) findViewById(R.id.lvDetails);

        Intent intent = getIntent();
        indElement = intent.getIntExtra("IND_ELEMENT", 0);

        lvDetails.setAdapter(new DetailBaseAdapter(getApplicationContext(), getDetails(getApplicationContext())));
    }

    private ArrayList<Detail> getDetails(Context context) {
        int scale = Util.getScale(context);
        String[] elementsDetailsArray = context.getResources().getStringArray(Util.ARRAY_ELEMENTS_DESC[scale]);
        String[] details = elementsDetailsArray[indElement].split(";");
        ArrayList<Detail> listDetails = new ArrayList<Detail>();
        for (String detail : details)
            listDetails.add(new Detail(detail));
        return listDetails;
    }

    private static class ViewHolderDetail {
        TextView tvTitleDetail;
        TextView tvDescDetail;
    }

    private static class DetailBaseAdapter extends BaseAdapter {

        private ArrayList<Detail> detailList;

        private LayoutInflater mInflater;

        private DetailBaseAdapter(Context context, ArrayList<Detail> detailList) {
            this.detailList = detailList;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return detailList.size();
        }

        @Override
        public Object getItem(int position) {
            return detailList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolderDetail holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.single_row_detail, null);
                holder = new ViewHolderDetail();
                holder.tvTitleDetail = (TextView) convertView.findViewById(R.id.tvTitleDetail);
                holder.tvDescDetail = (TextView) convertView.findViewById(R.id.tvDescDetail);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderDetail) convertView.getTag();
            }

            Detail theDetail = detailList.get(position);
            holder.tvTitleDetail.setText(theDetail.getTitle());
            holder.tvDescDetail.setText(theDetail.getDesc());
            return convertView;
        }

    }
}
