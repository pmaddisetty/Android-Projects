package inclass02.poorna.com.threaddemo2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by poorn on 2/12/2018.
 */

public class GetAsyncImage extends AsyncTask<String,Integer,Bitmap> {
    ImageView imgv;

    HttpURLConnection connection = null;
    Bitmap bitmap = null;
    iImage intimg;

    public GetAsyncImage(ImageView imageView,iImage inter) {
        imgv = imageView;
        intimg=inter;
    }

    @Override
    protected void onPreExecute() {

        intimg.initprog();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
           // connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                publishProgress();
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        intimg.handleImage(bitmap);
        intimg.displaprog();
    }

    public static interface iImage{
        public void handleImage(Bitmap bitmap);
        public void initprog();
        public void displaprog();
    }
}
