package inclass02.poorna.com.group4_hw04;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;
import java.util.ArrayList;

/**
 * Created by poorn on 2/22/2018.
 */

public class NewsParser {

    public static class NewsPullParser
    {
        static public ArrayList<NewsItem> parseNews(InputStream inputStream) throws XmlPullParserException, IOException {
            ArrayList<NewsItem> news=new ArrayList<>();
            NewsItem item=null;
           XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
           XmlPullParser parser=factory.newPullParser();
           parser.setInput(inputStream,"UTF_8");
           int event=parser.getEventType();
           boolean flag=false;
            while (event!=XmlPullParser.END_DOCUMENT)
            {
                switch (event){
                    case XmlPullParser.START_TAG:

                        if(parser.getName().equals("item"))
                        {
                            Log.d("demo","item entered");
                            item=new NewsItem();
                            flag=true;

                        }
                        else if(parser.getName().equals("title")&& flag==true)
                        {
                            item.setTitle(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("description")&& flag==true)
                        {
                            item.setDescription(parser.nextText().trim());

                        }

                        else if(parser.getName().equals("media:content")&& flag==true)
                        {
                            item.setImgurl(parser.getAttributeValue(null,"url"));
                        }
                        else if(parser.getName().equals("media:thumbnail")&& flag==true)
                        {
                            item.setImgurl(parser.getAttributeValue(null,"url"));
                        }
                        else if(parser.getName().equals("pubDate")&& flag==true)
                        {
                            item.setPubdate(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("link")&& flag==true)
                        {
                            item.setLinkurl(parser.nextText().trim());
                        }

                        break;
                    case XmlPullParser.END_TAG:
                         if(parser.getName().equals("item")&& flag==true)
                        {
                            news.add(item);
                        }


                        break;
                    default:
                        break;
                }
                event=parser.next();
            }
            return news;
        }
    }
}
