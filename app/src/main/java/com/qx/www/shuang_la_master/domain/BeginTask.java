package com.qx.www.shuang_la_master.domain;

/**
 * Created by pc on 2016/7/14.
 */
public class BeginTask
{
    String status;
    Infos infos;
    String code;

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

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public class Infos
    {

        String title;
        String url;
        String pid;
        String reward;
        String processName;
        String fengmian;
        String zs_reward;
        String size;
        String moretask;
        String sln;

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getPid()
        {
            return pid;
        }

        public void setPid(String pid)
        {
            this.pid = pid;
        }

        public String getReward()
        {
            return reward;
        }

        public void setReward(String reward)
        {
            this.reward = reward;
        }

        public String getProcessName()
        {
            return processName;
        }

        public void setProcessName(String processName)
        {
            this.processName = processName;
        }

        public String getFengmian()
        {
            return fengmian;
        }

        public void setFengmian(String fengmian)
        {
            this.fengmian = fengmian;
        }

        public String getZs_reward()
        {
            return zs_reward;
        }

        public void setZs_reward(String zs_reward)
        {
            this.zs_reward = zs_reward;
        }

        public String getSize()
        {
            return size;
        }

        public void setSize(String size)
        {
            this.size = size;
        }

        public String getMoretask()
        {
            return moretask;
        }

        public void setMoretask(String moretask)
        {
            this.moretask = moretask;
        }

        public String getSln()
        {
            return sln;
        }

        public void setSln(String sln)
        {
            this.sln = sln;
        }
    }


}
