package com.qx.www.shuang_la_master.domain;

import java.util.List;

/**
 * Created by pc on 2016/7/1.
 */
public class Detail
{
    String status;
    List<Infos> infos;
    String type;
    List<Panems> pnameList;
    List<Txs> txt_list;
    String[] ytx;

    public String[] getYtx()
    {
        return ytx;
    }

    public void setYtx(String[] ytx)
    {
        this.ytx = ytx;
    }

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public class Infos
    {
        String type;
        String money;
        String taskid;
        String packet;
        String time;

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getMoney()
        {
            return money;
        }

        public void setMoney(String money)
        {
            this.money = money;
        }

        public String getTaskid()
        {
            return taskid;
        }

        public void setTaskid(String taskid)
        {
            this.taskid = taskid;
        }

        public String getPacket()
        {
            return packet;
        }

        public void setPacket(String packet)
        {
            this.packet = packet;
        }

        public String getTime()
        {
            return time;
        }

        public void setTime(String time)
        {
            this.time = time;
        }
    }

    private class Panems
    {

    }

    private class Txs
    {

    }
}
