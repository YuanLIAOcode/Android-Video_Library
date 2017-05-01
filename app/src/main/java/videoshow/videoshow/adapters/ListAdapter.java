package videoshow.videoshow.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import videoshow.videoshow.components.MovieInfo;
import videoshow.videoshow.R;
import videoshow.videoshow.fragments.ListFragment;
import videoshow.videoshow.HttpActivity;


public class ListAdapter extends RecyclerView.Adapter{
//    private HttpActivity mHttpActivity = new HttpActivity();

    private final ListFragment.OnMovieSelectedInterface mListerner;
    private MovieInfo movieInfo = new MovieInfo();

    public ListAdapter(ListFragment.OnMovieSelectedInterface listener) {
        mListerner = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) { //call the bindView method from OnbindView holder
        ((ListViewHolder) holder).bindView(position);
//        ((ListViewHolder) holder).mImageView.setImageResource();
    }

    @Override
    public int getItemCount() {
        return movieInfo.resourceIDList.length * 4; //这里返回 需要 list 的数量
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private ImageView mImageView;
        private int mIndex;

        public ListViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) { //Inside this method, we update the TextView and ImageView to display the right movie
            mIndex = position;
            mImageView.setImageResource(MovieInfo.imageIds[position % movieInfo.getResourceIDList().length]);

        }
        @Override
        public void onClick(View v) { //处理从ListFragment里的ListAdapter类型变量的用户tap 选择Movie的活动
            mListerner.onListMovieSelected(mIndex);
        }
    }
}
