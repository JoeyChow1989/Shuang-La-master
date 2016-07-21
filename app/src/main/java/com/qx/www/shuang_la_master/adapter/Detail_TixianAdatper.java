package com.qx.www.shuang_la_master.adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.qx.www.shuang_la_master.utils.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/1.
 */
public class Detail_TixianAdatper extends RecyclerView.Adapter
{

    private Detail detail;
    private Context context;
    SharedPreferences sp;

    public Detail_TixianAdatper(Detail detail, Context context)
    {
        this.detail = detail;
        this.context = context;

        sp = context.getSharedPreferences("UserInfo", context.MODE_PRIVATE);
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

    protected void setUpItemEvent(final TixianitemViewHolder holder)
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tixian_detail, parent, false);
        TixianitemViewHolder holder = new TixianitemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        TixianitemViewHolder allitemViewHolder = (TixianitemViewHolder) holder;
        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(allitemViewHolder.idAllItemImg);

        if (detail.getInfos().get(position).getTaskid().equals("3"))
        {
            allitemViewHolder.idAllItemMsg.setText("支付宝提现");
        } else if (detail.getInfos().get(position).getTaskid().equals("1"))
        {
            allitemViewHolder.idAllItemMsg.setText("手机充值");
        } else
        {
            allitemViewHolder.idAllItemMsg.setText("微信提现");
        }
        String date_month = detail.getInfos().get(position).getTime().substring(5, 7);
        String date_day = detail.getInfos().get(position).getTime().substring(8, 10);
        String date_time = detail.getInfos().get(position).getTime().substring(11, 16);
        allitemViewHolder.idAllItemTime.setText(date_month + "月" + date_day + "  " + date_time);
        allitemViewHolder.idItemtixianMoeny.setText(AppUtils.numZhuanHuan(detail.getInfos().get(position).getPacket().substring(0, 4)));


        allitemViewHolder.idAllItemTime.setText(date_month + "月" + date_day + "  " + date_time);
        allitemViewHolder.idAllItemNickname.setText(sp.getString("nickname", ""));
        setUpItemEvent(allitemViewHolder);
    }

    @Override
    public int getItemCount()
    {
        return detail.getInfos().size();
    }

    static class TixianitemViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_itemtixian_img)
        ImageView idAllItemImg;
        @Bind(R.id.id_itemtixian_nickname)
        TextView idAllItemNickname;
        @Bind(R.id.id_itemtixian_time)
        TextView idAllItemTime;
        @Bind(R.id.id_itemtixian_msg)
        TextView idAllItemMsg;
        @Bind(R.id.id_itemtixian_moeny)
        TextView idItemtixianMoeny;
        @Bind(R.id.id_itemtixian_stay)
        TextView idItemtixianStay;

        public TixianitemViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
