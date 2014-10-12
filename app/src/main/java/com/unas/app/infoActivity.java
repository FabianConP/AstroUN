package com.unas.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import modelo.Element;


public class infoActivity extends Activity {

    public static int scale;
    private static final int[] ARRAY_ELEMENTS_ID = {R.array.ind_0,R.array.ind_1,R.array.ind_2,R.array.ind_3};
    private static String[] ARRAY_SCALE_NAME;

    private static TextView tvValueRange;
    private static ListView lvElements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvValueRange = (TextView) findViewById(R.id.tvValueRange);
        lvElements = (ListView) findViewById(R.id.lvElements);

        ARRAY_SCALE_NAME = getResources().getStringArray(R.array.scales);

        Intent intent = getIntent();
        scale = intent.getIntExtra("SCALE",0);
        String strScale = ARRAY_SCALE_NAME[scale];

        tvValueRange.setText(strScale);

        lvElements.setAdapter(new ElementBaseAdapter(getApplicationContext(),getElements(getApplicationContext())));
    }

    private static ArrayList<Element> getElements(Context context){
        String[] elementsArray = context.getResources().getStringArray(ARRAY_ELEMENTS_ID[scale]);
        ArrayList<Element> listElements = new ArrayList<Element>();
        for (int i = 0; i<elementsArray.length;i++)
            listElements.add(new Element(i,elementsArray[i]));
        return listElements;
    }

    private class ElementBaseAdapter extends BaseAdapter{

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
                holder.ibMapElement = (ImageButton) convertView.findViewById(R.id.ibMap);
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

            return convertView;
        }

    }

    static class ViewHolder {
        ImageButton ibDetailElement;
        TextView tvNameElement;
        ImageButton ibWikiElement;
        ImageButton ibMapElement;
    }

}
