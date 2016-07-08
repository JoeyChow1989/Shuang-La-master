package com.qx.www.shuang_la_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.domain.News;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/6.
 */
public class NewsNoticeAdapter extends RecyclerView.Adapter
{
    private List<News> mList;
    private Context context;

    public NewsNoticeAdapter(List<News> mList, Context context)
    {
        this.mList = mList;
        this.context = context;
    }

    //Onclik接口
    public interface OnItemClickListener
    {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }

    protected void setUpItemEvent(final NewsViewHolder holder)
    {
        if (mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutpostion);
                }
            });

            //longclick

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutpostion);

                    return false;
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newsnotice, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
        newsViewHolder.idNewsnoticeItemMsg.setText(mList.get(position).getMsg());
        newsViewHolder.idNewsnoticeItemDate.setText(mList.get(position).getDate());

        setUpItemEvent(newsViewHolder);
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_newsnotice_item_msg)
        TextView idNewsnoticeItemMsg;
        @Bind(R.id.id_newsnotice_item_date)
        TextView idNewsnoticeItemDate;

        public NewsViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
