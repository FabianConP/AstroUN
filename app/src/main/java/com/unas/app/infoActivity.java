package com.unas.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import modelo.Element;


public class infoActivity extends Activity {

    private static final int[] ARRAY_ELEMENTS_ID = {R.array.ind_0,R.array.ind_1,R.array.ind_2,R.array.ind_3};
    public static int scale;
    private static String[] ARRAY_SCALE_NAME;

    private static SeekBar sbRange;
    private static TextView tvValueRange;
    private static ListView lvElements;

    private static String performRange(Context context, String strScale) {
        String[] range = strScale.split("-");
        String from = context.getResources().getString(R.string.from);
        String to = context.getResources().getString(R.string.to);
        if (range.length == 1)
            return range[0];
        else
            return from + " " + range[0] + " " + to + " " + range[1];
    }

    private static ArrayList<Element> getElements(Context context) {
        String[] elementsArray = context.getResources().getStringArray(ARRAY_ELEMENTS_ID[scale]);
        ArrayList<Element> listElements = new ArrayList<Element>();
        for (int i = 0; i < elementsArray.length; i++)
            listElements.add(new Element(i, elementsArray[i]));
        return listElements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_info);

        sbRange = (SeekBar) findViewById(R.id.sbRange);
        tvValueRange = (TextView) findViewById(R.id.tvValueRange);
        lvElements = (ListView) findViewById(R.id.lvElements);

        ARRAY_SCALE_NAME = getResources().getStringArray(R.array.scales);

        Intent intent = getIntent();
        scale = intent.getIntExtra("SCALE", 0);

        updateElements(getApplicationContext());

        sbRange.setMax(ARRAY_ELEMENTS_ID.length - 1);
        sbRange.setProgress(scale);
        sbRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                scale = progress;
                updateElements(getApplicationContext());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateElements(Context context) {
        tvValueRange.setText(performRange(context, ARRAY_SCALE_NAME[scale]));
        lvElements.setAdapter(new ElementBaseAdapter(context, getElements(context)));
    }

    static class ViewHolder {
        ImageButton ibDetailElement;
        TextView tvNameElement;
        ImageButton ibWikiElement;
        ImageButton ibImagesElement;
    }

    protected class ElementBaseAdapter extends BaseAdapter {

        private ArrayList<Element> elementList;

        private LayoutInflater mInflater;

        private ElementBaseAdapter(Context context, ArrayList<Element> elementList) {
            this.elementList = elementList;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return elementList.size();
        }

        @Override
        public Object getItem(int position) {
            return elementList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.single_row_element, null);
                holder = new ViewHolder();
                holder.ibDetailElement = (ImageButton) convertView.findViewById(R.id.ibDetail);
                holder.tvNameElement = (TextView) convertView.findViewById(R.id.tvName);
                holder.ibWikiElement = (ImageButton) convertView.findViewById(R.id.ibWiki);
                holder.ibImagesElement = (ImageButton) convertView.findViewById(R.id.ibImages);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Element theElement  = elementList.get(position);

            holder.tvNameElement.setText(theElement.getName());

            if(theElement.getWikiLink().equals("None"))
                holder.ibWikiElement.setVisibility(View.INVISIBLE);
            else
                holder.ibWikiElement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(elementList.get(position).getWikiLink());
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });
            if(theElement.getImagesLink().equals("None"))
                holder.ibImagesElement.setVisibility(View.INVISIBLE);
            else
                holder.ibImagesElement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(elementList.get(position).getImagesLink());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

            return convertView;
        }

    }

}
