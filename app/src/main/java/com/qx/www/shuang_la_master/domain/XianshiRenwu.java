package com.qx.www.shuang_la_master.domain;

import java.util.List;

/**
 * Created by pc on 2016/7/1.
 */
public class XianshiRenwu
{
    String status;
    List<Infos> infos;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public List<Infos> getInfos()
    {
        return infos;
    }

    public void setInfos(List<Infos> infos)
    {
        this.infos = infos;
    }

    public class Infos{

        String app_status;
        String title;
        String id;
        String packet;
        String icon;
        String status;
        String reward;
        String iscost;
        String moretask;
        String zs_reward;
        String sln;
        String stime;
        String isrun;
        String isfinish;

        public String getApp_status()
        {
            return app_status;
        }

        public void setApp_status(String app_status)
        {
            this.app_status = app_status;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
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

        public String getStatus()
        {
            return status;
        }

        public void setStatus(String status)
        {
            this.status = status;
        }

        public String getReward()
        {
            return reward;
        }

        public void setReward(String reward)
        {
            this.reward = reward;
        }

        public String getIscost()
        {
            return iscost;
        }

        public void setIscost(String iscost)
        {
            this.iscost = iscost;
        }

        public String getMoretask()
        {
            return moretask;
        }

        public void setMoretask(String moretask)
        {
            this.moretask = moretask;
        }

        public String getZs_reward()
        {
            return zs_reward;
        }

        public void setZs_reward(String zs_reward)
        {
            this.zs_reward = zs_reward;
        }

        public String getSln()
        {
            return sln;
        }

        public void setSln(String sln)
        {
            this.sln = sln;
        }

        public String getStime()
        {
            return stime;
        }

        public void setStime(String stime)
        {
            this.stime = stime;
        }

        public String getIsrun()
        {
            return isrun;
        }

        public void setIsrun(String isrun)
        {
            this.isrun = isrun;
        }

        public String getIsfinish()
        {
            return isfinish;
        }

        public void setIsfinish(String isfinish)
        {
            this.isfinish = isfinish;
        }
    }
}
