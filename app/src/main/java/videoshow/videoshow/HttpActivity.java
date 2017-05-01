package videoshow.videoshow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import videoshow.videoshow.MainActivity;
import videoshow.videoshow.components.MovieInfo;


public class HttpActivity extends Activity{
    private static final String MOVIE_INDEX = "movie_index";

    private int movieIndex;
    String resourceIDList[] = new String[]{"nm0000115", "nm0000237", "nm0000151", "nm00001382",
            "nm0000314", "nm00001665", "nm0000173"};

    private TextView title;
    private TextView description;
    private ImageView mImageView;

    private MovieInfo mMovieinfo = new MovieInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie);

        title = (TextView) findViewById(R.id.actorName);
        description = (TextView) findViewById(R.id.movieDescription);
        mImageView = (ImageView) findViewById(R.id.itemImage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        movieIndex = bundle.getInt(MOVIE_INDEX);

        getMovie(resourceIDList[movieIndex % mMovieinfo.getResourceIDList().length]); //for testing http


        Toast.makeText(HttpActivity.this, "httpWorks", Toast.LENGTH_SHORT).show();
    }

    public void getMovie(String resourceId) {
        String apiKey = "api_key=51f9dfd8-c29c-4ddd-ba33-00ebb39e1043";
        String IMDBUrl = "http://imdb.wemakesites.net/api/" + resourceId + "?" + apiKey;

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();  //OkHttpClient 组件需要在 build.gradle里加东西
            Request request = new Request.Builder()
                    .url(IMDBUrl)
                    .build();
            Call call = client.newCall(request); //execute the call method and returns a response object
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jasonData = response.body().string();
                        String data;
                        if (response.isSuccessful()) {
                            getMovieDetails(jasonData);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "Network is unavailable!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void getMovieDetails(String jsonData) throws JSONException {
        final MovieInfo movieInfo = new MovieInfo();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject data = jsonObject.getJSONObject("data");

            movieInfo.setTitle(data.getString("title"));
            movieInfo.setDescription(data.getString("description"));
//            movieInfo.setImage(data.getString("image")); //返回的是图片的超链接，不是图片格式
//            movieInfo.setMovieLinks((String[]) data.get("mediaLinks")); //返回的是一个链接数组

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    title.setText(movieInfo.getTitle());
                    description.setText(movieInfo.getDescription());
                    mImageView.setImageResource(movieInfo.getImage(movieIndex));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
