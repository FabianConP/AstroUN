package com.unas.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import Util.Util;
import modelo.Element;


public class infoActivity extends Activity {

    private int scale;
    private String[] ARRAY_SCALE_NAME;
    private SeekBar sbRange;
    private TextView tvValueRange;
    private ImageButton ibMapInfo;
    private ListView lvElements;

    private ArrayList<Element> getElements(Context context) {
        String[] elementsArray = context.getResources().getStringArray(Util.ARRAY_ELEMENTS_ID[scale]);
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
        ibMapInfo = (ImageButton) findViewById(R.id.ibMapInfo);
        lvElements = (ListView) findViewById(R.id.lvElements);

        ARRAY_SCALE_NAME = getResources().getStringArray(R.array.scales);

        scale = getScale();

        updateElements(getApplicationContext());

        sbRange.setMax(Util.ARRAY_ELEMENTS_ID.length - 1);
        sbRange.setProgress(scale);
        sbRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                scale = progress;
                setScale(scale);
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

    private int getScale() {
        SharedPreferences sharedPref = getSharedPreferences(Util.MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getInt(Util.LAST_SCALE, 0);
    }

    private void setScale(int scale) {
        SharedPreferences sharedpreferences = getSharedPreferences(Util.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(Util.LAST_SCALE, scale);
        editor.commit();
    }

    private void updateElements(Context context) {
        tvValueRange.setText("10^" + ARRAY_SCALE_NAME[scale]);
        ibMapInfo.setVisibility(existsMap() ? View.VISIBLE : View.INVISIBLE);
        lvElements.setAdapter(new ElementBaseAdapter(context, getElements(context)));
    }

    private boolean existsMap() {
        return scale >= 18 && scale <= 24;
    }

    public void onClickGoogleMaps(View view) {
        final Intent intent = new Intent(getApplication(), GoogleMapsActivity.class);
        String title = getApplicationContext().getString(R.string.mapType);
        String question = getApplicationContext().getString(R.string.questionMapType);
        String normal = getApplicationContext().getString(R.string.normalVar);
        String hybrid = getApplicationContext().getString(R.string.hybridVar);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        setTypeMap(intent, "NORMAL");
                        startActivity(intent);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        setTypeMap(intent, "HYBRID");
                        startActivity(intent);
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(question)
                .setTitle(title)
                .setPositiveButton(normal, dialogClickListener)
                .setNegativeButton(hybrid, dialogClickListener).show();
    }

    private void setTypeMap(Intent intent, String type) {
        intent.putExtra("TYPE_MAP", type);
    }

    static class ViewHolder {
        ImageButton ibDetailElement;
        TextView tvNameElement;
        ImageButton ibWikiElement;
        ImageButton ibImagesElement;
    }

    private class ElementBaseAdapter extends BaseAdapter {

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

            Element theElement = elementList.get(position);

            holder.tvNameElement.setText(theElement.getName());

            holder.tvNameElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("IND_ELEMENT", position);
                    startActivity(intent);
                }
            });

            holder.ibDetailElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("IND_ELEMENT", position);
                    startActivity(intent);
                }
            });

            if (theElement.getWikiLink().equals("None"))
                holder.ibWikiElement.setVisibility(View.INVISIBLE);
            else
                holder.ibWikiElement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(elementList.get(position).getWikiLink());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            if (theElement.getImagesLink().equals("None"))
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
