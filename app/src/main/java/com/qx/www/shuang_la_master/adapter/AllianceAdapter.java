package com.qx.www.shuang_la_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.domain.AllianceRenwu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/1.
 */
public class AllianceAdapter extends BaseAdapter
{
    private List<AllianceRenwu> mList_alliance;
    private Context context;

    public AllianceAdapter(List<AllianceRenwu> mList_alliance, Context context)
    {
        this.mList_alliance = mList_alliance;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return mList_alliance.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mList_alliance.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        AllianceViewHolder holder = null;

        if (convertView == null)
        {
            holder = new AllianceViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_alliance, parent, false);

            holder.itemImg = (ImageView) convertView.findViewById(R.id.item_img);
            holder.itemTitle = (TextView) convertView.findViewById(R.id.item_title);
            holder.itemMsg = (TextView) convertView.findViewById(R.id.item_msg);

            convertView.setTag(holder);
        } else
        {
            holder = (AllianceViewHolder) convertView.getTag();
        }

        Glide.with(context)
                .load(mList_alliance.get(position).getImgs())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemImg);

        holder.itemTitle.setText(mList_alliance.get(position).getTitle());
        holder.itemMsg.setText(mList_alliance.get(position).getMsgs());

        return convertView;
    }

    class AllianceViewHolder
    {
        ImageView itemImg;
        TextView itemTitle;
        TextView itemMsg;
    }
}
