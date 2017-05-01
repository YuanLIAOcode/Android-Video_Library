package videoshow.videoshow;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import videoshow.videoshow.components.MovieInfo;
import videoshow.videoshow.fragments.ListFragment;
import videoshow.videoshow.fragments.ViewPagerFragment;

// 注意，使用的是 support activity, support fragment 类型的包！
public class MainActivity extends AppCompatActivity implements ListFragment.OnMovieSelectedInterface{
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";
    public static final String MOVIEW_FRAGMENT = "movie_fragment";
    private static final String MOVIE_INDEX = "movie_index";
    private MovieInfo mMovieinfo;
    public HttpActivity mHttpActivity = new HttpActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();                             //管理Fragment界面
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();            //Transaction 和 SharePreference 很像
        fragmentTransaction.add(R.id.activity_main, viewPagerFragment, VIEWPAGER_FRAGMENT); //确定位置，在activity_main这个Activity layout下添加一个ViewPagerFragment类型的fragment
        fragmentTransaction.commit();
    }

    @Override
    public void onListMovieSelected(int index) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_INDEX, index);
        intent.putExtras(bundle);
        intent.setClass(MainActivity.this, HttpActivity.class);
        startActivity(intent);

    }

}
