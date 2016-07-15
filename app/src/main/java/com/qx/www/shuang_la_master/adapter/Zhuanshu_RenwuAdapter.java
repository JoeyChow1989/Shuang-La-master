package com.qx.www.shuang_la_master.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.activity.RenwuDetailActivity;
import com.qx.www.shuang_la_master.domain.ZhanshuRenwu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/1.
 */
public class Zhuanshu_RenwuAdapter extends RecyclerView.Adapter
{

    private List<ZhanshuRenwu> mList;
    private Context context;

    public Zhuanshu_RenwuAdapter(List<ZhanshuRenwu> mList, Context context)
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhuanshurenwu, parent, false);
        ZhuanshuViewHolder holder = new ZhuanshuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ZhuanshuViewHolder zhuanshuViewHolder = (ZhuanshuViewHolder) holder;

        // TODO: 2016/7/1 adapter
        Glide.with(context)
                .load(mList.get(position).getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(zhuanshuViewHolder.idzhuanshurenwuImg);

        zhuanshuViewHolder.idzhuanshurenwuTitle.setText(mList.get(position).getTitle());
        setUpItemEvent(zhuanshuViewHolder);


        this.setOnItemClickListener(new Zhuanshu_RenwuAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                // TODO: 2016/7/15 专属点击
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    protected void setUpItemEvent(final ZhuanshuViewHolder holder)
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

    static class ZhuanshuViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_zhuanshurenwu_time)
        TextView idZhuanshurenwuTime;
        @Bind(R.id.id_zhuanshurenwu_img)
        ImageView idzhuanshurenwuImg;
        @Bind(R.id.id_zhuanshurenwu_title)
        TextView idzhuanshurenwuTitle;
        @Bind(R.id.id_zhuanshurenwu_msg)
        TextView idzhuanshurenwuMsg;
        @Bind(R.id.id_zhuanshurenwu_pics)
        ImageView idzhuanshurenwuPics;

        public ZhuanshuViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
