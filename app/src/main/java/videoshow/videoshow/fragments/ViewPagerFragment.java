package videoshow.videoshow.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import videoshow.videoshow.R;
import videoshow.videoshow.SearchActivity;


public class ViewPagerFragment extends Fragment { //使用的是support activity 对应的 Fragment 包
    public static final String KEY_MOVIE_INDEX = "movie_index";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        final ListFragment listFragment = new ListFragment();
        final SearchFragment searchFragment = new SearchFragment();
        final ProfileFragment profileFragment = new ProfileFragment();

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) { //Fragment 中嵌套 Fragment, 需要使用getChildFragmentManager()
            @Override
            public Fragment getItem(int position) { // return the information of movie
                if (position == 0) {
                    return listFragment;
                }
                else if (position == 1) {
                    return searchFragment;
                }
                else {
                    return profileFragment;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "Accueil";
                }
                else if (position == 1) {
                    return "Search";
                }
                else {
                    return "Profile";
                }
            }

            @Override
            public int getCount() { //把一个interface分成几份，因为有三个，所以返回 3
                return 3;
            }
        });

        TabLayout tableLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tableLayout.setupWithViewPager(viewPager);

        return view;
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
//    }
    //    protected Activity mActivity;
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.mActivity = (Activity) context;
//    }
}
