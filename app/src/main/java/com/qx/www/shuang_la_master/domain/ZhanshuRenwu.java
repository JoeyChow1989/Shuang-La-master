package com.qx.www.shuang_la_master.domain;

import java.util.List;

/**
 * Created by pc on 2016/7/1.
 */
public class ZhanshuRenwu
{
    String status;
    String title;
    String packet;
    String icon;
    String status_info;
    String zs_reward;
    String tid;

    public String getTid()
    {
        return tid;
    }

    public void setTid(String tid)
    {
        this.tid = tid;
    }

    String[] tomorrow;
    String[] today;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPacket()
    {
        return packet;
    }

    public void setPacket(String packet)
    {
        this.packet = packet;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getStatus_info()
    {
        return status_info;
    }

    public void setStatus_info(String status_info)
    {
        this.status_info = status_info;
    }

    public String getZs_reward()
    {
        return zs_reward;
    }

    public void setZs_reward(String zs_reward)
    {
        this.zs_reward = zs_reward;
    }

    public String[] getTomorrow()
    {
        return tomorrow;
    }

    public void setTomorrow(String[] tomorrow)
    {
        this.tomorrow = tomorrow;
    }

    public String[] getToday()
    {
        return today;
    }

    public void setToday(String[] today)
    {
        this.today = today;
    }
}

