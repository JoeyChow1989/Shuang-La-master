package com.qx.www.shuang_la_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.domain.XianshiRenwu;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xianshirenwu, parent, false);
        ZhuanshuViewHolder holder = new ZhuanshuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ZhuanshuViewHolder xianshiViewHolder = (ZhuanshuViewHolder) holder;

        // TODO: 2016/7/1 adapter
        Glide.with(context)
                .load(mList.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(xianshiViewHolder.idXianshirenwuImg);

        Glide.with(context)
                .load(mList.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(xianshiViewHolder.idXianshirenwuPics);

        xianshiViewHolder.idXianshirenwuTitle.setText("");
        xianshiViewHolder.idXianshirenwuCount.setText("");
        xianshiViewHolder.idXianshirenwuMsg.setText("");

    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class ZhuanshuViewHolder extends RecyclerView.ViewHolder
    {

        @Bind(R.id.id_xianshirenwu_img)
        ImageView idXianshirenwuImg;
        @Bind(R.id.id_xianshirenwu_title)
        TextView idXianshirenwuTitle;
        @Bind(R.id.id_xianshirenwu_count)
        TextView idXianshirenwuCount;
        @Bind(R.id.id_xianshirenwu_msg)
        TextView idXianshirenwuMsg;
        @Bind(R.id.id_xianshirenwu_pics)
        ImageView idXianshirenwuPics;

        public ZhuanshuViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
