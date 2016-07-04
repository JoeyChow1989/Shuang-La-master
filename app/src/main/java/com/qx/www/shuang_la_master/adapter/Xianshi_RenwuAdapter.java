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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/1.
 */
public class Xianshi_RenwuAdapter extends RecyclerView.Adapter
{

    private List<XianshiRenwu> mList;
    private Context context;

    //Onclik接口
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public Xianshi_RenwuAdapter(List<XianshiRenwu> mList, Context context)
    {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xianshirenwu, parent, false);
        XianshiViewHolder holder = new XianshiViewHolder(view);
        return holder;
    }

    protected void setUpItemEvent(final XianshiViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutpostion);
                }
            });

            //longclick

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutpostion);

                    return false;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        XianshiViewHolder xianshiViewHolder = (XianshiViewHolder) holder;

        // TODO: 2016/7/1 adapter
        Glide.with(context)
                .load(mList.get(position).getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(xianshiViewHolder.idXianshirenwuImg);

        Glide.with(context)
                .load(mList.get(position).getPic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(xianshiViewHolder.idXianshirenwuPics);

        xianshiViewHolder.idXianshirenwuTitle.setText(mList.get(position).getTitle());
        xianshiViewHolder.idXianshirenwuCount.setText(mList.get(position).getCount());
        xianshiViewHolder.idXianshirenwuMsg.setText(mList.get(position).getMsg());

        setUpItemEvent(xianshiViewHolder);

    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class XianshiViewHolder extends RecyclerView.ViewHolder
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

        public XianshiViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
