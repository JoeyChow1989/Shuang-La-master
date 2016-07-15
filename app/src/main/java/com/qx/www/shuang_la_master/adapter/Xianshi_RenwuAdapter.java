package com.qx.www.shuang_la_master.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseFragment;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.activity.MakeMoneyCenterActivity;
import com.qx.www.shuang_la_master.activity.RenwuDetailActivity;
import com.qx.www.shuang_la_master.domain.BeginTask;
import com.qx.www.shuang_la_master.domain.XianshiRenwu;
import com.qx.www.shuang_la_master.ui.CustemDialog;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/1.
 */
public class Xianshi_RenwuAdapter extends RecyclerView.Adapter
{

    private XianshiRenwu xianshiRenwu;
    private Context context;
    private String uid;
    private String semi;

    String tokenBeforeMd5_begintask;
    String token_begintask;
    CustemDialog dialog;

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

    public Xianshi_RenwuAdapter(XianshiRenwu xianshiRenwu, Context context, String uid, String semi)
    {
        this.xianshiRenwu = xianshiRenwu;
        this.context = context;
        this.uid = uid;
        this.semi = semi;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xianshirenwu, parent, false);
        XianshiViewHolder holder = new XianshiViewHolder(view);
        return holder;
    }

    protected void setUpItemEvent(final XianshiViewHolder holder)
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        XianshiViewHolder xianshiViewHolder = (XianshiViewHolder) holder;

        // TODO: 2016/7/1 adapter
        Glide.with(context)
                .load(Constants.BACKGROUDUrl + xianshiRenwu.getInfos().get(position).getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(xianshiViewHolder.idXianshirenwuImg);

        System.out.println(Constants.BACKGROUDUrl + xianshiRenwu.getInfos().get(position).getIcon());

        xianshiViewHolder.idXianshirenwuTitle.setText(xianshiRenwu.getInfos().get(position).getTitle());
        xianshiViewHolder.idXianshirenwuCount.setText("剩余" + xianshiRenwu.getInfos().get(position).getSln());

        if (xianshiRenwu.getInfos().get(position).getMoretask().equals("0"))
        {
            xianshiViewHolder.idXianshirenwuZhuanshu.setVisibility(View.INVISIBLE);
        }

        //点击抢夺任务;
        this.setOnItemClickListener(new Xianshi_RenwuAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                System.out.println("position--------------------" + position);

                tokenBeforeMd5_begintask = semi + uid + Constants.KEY + xianshiRenwu.getInfos().get(position).getPacket();
                token_begintask = AppUtils.getMd5Value(tokenBeforeMd5_begintask);
                System.out.println("token_begintask---------------------:" + token_begintask);
                String tid = xianshiRenwu.getInfos().get(position).getId();
                String packet = xianshiRenwu.getInfos().get(position).getPacket();
                QiangXianshiRenwu(tid, packet);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {

            }
        });

        setUpItemEvent(xianshiViewHolder);
    }

    private void QiangXianshiRenwu(final String tid, final String packet)
    {
        String url = Constants.BaseUrl + "/site/begintask";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("tid", tid);
        params.put("packet", packet);
        params.put("token", token_begintask);

        VolleyRequest.RequestPost(context, url, "begin", params, new VolleyInterface(context,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("result_qiangduo------------------------------:" + result);

                Gson gson = new Gson();
                BeginTask begintask = gson.fromJson(result, BeginTask.class);

                if (begintask.getStatus().equals("ok"))
                {
                    dialog = new CustemDialog(context, begintask.getInfos().getTitle(), Constants.BACKGROUDUrl + begintask.getInfos().getFengmian(), begintask.getInfos().getSize(), begintask.getInfos().getReward(), begintask.getInfos().getUrl(), semi, uid, tid, begintask.getInfos().getProcessName());
                    dialog.showDialog();
                } else
                {
                    AlertDialog alertdialog = new AlertDialog.Builder(context).create();
                    alertdialog.setMessage("对不起,任务没抢到..");
                    alertdialog.show();
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return xianshiRenwu.getInfos().size();
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
        @Bind(R.id.id_xianshirenwu_zhuanshu)
        TextView idXianshirenwuZhuanshu;

        public XianshiViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
