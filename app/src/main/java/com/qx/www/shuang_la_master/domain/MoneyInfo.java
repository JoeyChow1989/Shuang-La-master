package com.qx.www.shuang_la_master.domain;

/**
 * Created by pc on 2016/7/14.
 */
public class MoneyInfo
{
    String status;
    Infos infos;

    public class Infos{

        String avatar;
        String todaymoney;
        String totalmoney;
        String money;
        String zsnum;
        String tag;

        public String getAvatar()
        {
            return avatar;
        }

        public void setAvatar(String avatar)
        {
            this.avatar = avatar;
        }

        public String getTodaymoney()
        {
            return todaymoney;
        }

        public void setTodaymoney(String todaymoney)
        {
            this.todaymoney = todaymoney;
        }

        public String getTotalmoney()
        {
            return totalmoney;
        }

        public void setTotalmoney(String totalmoney)
        {
            this.totalmoney = totalmoney;
        }

        public String getMoney()
        {
            return money;
        }

        public void setMoney(String money)
        {
            this.money = money;
        }

        public String getZsnum()
        {
            return zsnum;
        }

        public void setZsnum(String zsnum)
        {
            this.zsnum = zsnum;
        }

        public String getTag()
        {
            return tag;
        }

        public void setTag(String tag)
        {
            this.tag = tag;
        }
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Infos getInfos()
    {
        return infos;
    }

    public void setInfos(Infos infos)
    {
        this.infos = infos;
    }
}
