package videoshow.videoshow.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import videoshow.videoshow.HttpActivity;
import videoshow.videoshow.R;
import videoshow.videoshow.SearchActivity;


public class SearchFragment extends Fragment {

    private Button searchButton;
    private Button toastButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchButton = (Button) view.findViewById(R.id.searchButton);
        toastButton = (Button) view.findViewById(R.id.toastButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "works", Toast.LENGTH_LONG).show();
            }
        });
//        private void inflateListView(Cursor cursor)
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("DO NOT CRASH", "OK");
    }
}
