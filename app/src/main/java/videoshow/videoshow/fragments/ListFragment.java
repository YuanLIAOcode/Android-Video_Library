package videoshow.videoshow.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import videoshow.videoshow.adapters.ListAdapter;
import videoshow.videoshow.R;
import videoshow.videoshow.components.MovieInfo;
//import videoshow.videoshow.adapters.RecycleAdapter;


public class ListFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public interface OnMovieSelectedInterface {
        void onListMovieSelected(int index); //the activity will implement this methd
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OnMovieSelectedInterface listener = (OnMovieSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView); // hook up the fragment_list layout
        ListAdapter listAdapter = new ListAdapter(listener); //因为adapter处理List item, 当我们点金屏幕选择一个Item时，lister程序将会交给对应的adapter处理。所以赋值给adpater
        mRecyclerView.setAdapter(listAdapter); //make ListAdapter attach to the recycleView
        RecyclerView.LayoutManager layoutManager= new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); //LinearLayoutManager make our recycleView act like a vertical list
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
