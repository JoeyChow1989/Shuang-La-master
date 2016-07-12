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
import com.qx.www.shuang_la_master.domain.Detail;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/1.
 */
public class DetailAdatper extends RecyclerView.Adapter
{

    private List<Detail> mList;
    private Context context;

    public DetailAdatper(List<Detail> mList, Context context)
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

    protected void setUpItemEvent(final AllitemViewHolder holder)
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allitem_detail, parent, false);
        AllitemViewHolder holder = new AllitemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        AllitemViewHolder allitemViewHolder = (AllitemViewHolder) holder;
        Glide.with(context)
                .load(mList.get(position).getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(allitemViewHolder.idAllItemImg);

        allitemViewHolder.idAllItemNickname.setText(mList.get(position).getTitle());
        allitemViewHolder.idAllItemMsg.setText(mList.get(position).getMsgs());
        allitemViewHolder.idAllItemTime.setText(mList.get(position).getTime());

        setUpItemEvent(allitemViewHolder);
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class AllitemViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_all_item_img)
        ImageView idAllItemImg;
        @Bind(R.id.id_all_item_nickname)
        TextView idAllItemNickname;
        @Bind(R.id.id_all_item_time)
        TextView idAllItemTime;
        @Bind(R.id.id_all_item_msg)
        TextView idAllItemMsg;

        public AllitemViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
