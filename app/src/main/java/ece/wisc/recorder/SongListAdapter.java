package ece.wisc.recorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class SongListAdapter extends
        RecyclerView.Adapter<SongListAdapter.SongViewHolder> {
    private final LinkedList<Record> mSongList;
    private LayoutInflater mInflater;
    public SongListAdapter(Context context,
                           LinkedList<Record> songList) {
        mInflater = LayoutInflater.from(context);
        this.mSongList = songList;
    }
    class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView SongItemView;
        final SongListAdapter mAdapter;
        public SongViewHolder(View itemView, SongListAdapter adapter) {
            super(itemView);
            SongItemView = itemView.findViewById(R.id.song);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
// Use that to access the affected item in mSongList.
            Record element = mSongList.get(mPosition);
// Change the Song in the mSongList.
            mSongList.set(mPosition, element);
// Notify the adapter, that the data has changed so it can
// update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View mItemView = mInflater.inflate(R.layout.songlist_item,
                parent, false);
        return new SongViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Record mCurrent = mSongList.get(position);
        holder.SongItemView.setText(mCurrent.getTitle());
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }
}

