package inclass02.poorna.com.group4_inclass07;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by poorn on 2/26/2018.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<NewsItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //ArrayList<NewsItem> newslist;
        NewsItem news=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.layout_news,parent,false);
        }
        TextView timg=convertView.findViewById(R.id.tvtitle);
        TextView tdate=convertView.findViewById(R.id.tvdate);
        ImageView imv=convertView.findViewById(R.id.tvimg);

        timg.setText(news.getTitle().toString());
        tdate.setText(news.getPubdate().toString());
        Picasso.with(getContext()).load(news.getImageurl()).into(imv);
        return convertView;
    }
}