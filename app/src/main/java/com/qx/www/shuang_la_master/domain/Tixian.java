package com.qx.www.shuang_la_master.domain;

/**
 * Created by pc on 2016/7/19.
 */
public class Tixian
{
    String status;
    Infos infos;

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

    public class Infos
    {

        String nickname;
        String avatar;
        String id;
        String money;

        public String getNickname()
        {
            return nickname;
        }

        public void setNickname(String nickname)
        {
            this.nickname = nickname;
        }

        public String getAvatar()
        {
            return avatar;
        }

        public void setAvatar(String avatar)
        {
            this.avatar = avatar;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getMoney()
        {
            return money;
        }

        public void setMoney(String money)
        {
            this.money = money;
        }
    }

}
