package com.example.covidtracer;

import java.util.ArrayList;

public class model {
    String flag,counrtyName,recovery,death,todaydeath,critical,active,todayscases;

    public model(String flag, String counrtyName, String recovery, String death,
                 String todaydeath, String critical, String active, String todayscases) {
        this.flag = flag;
        this.counrtyName = counrtyName;
        this.recovery = recovery;
        this.death = death;
        this.todaydeath = todaydeath;
        this.critical = critical;
        this.active = active;
        this.todayscases = todayscases;
    }




    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCounrtyName() {
        return counrtyName;
    }

    public void setCounrtyName(String counrtyName) {
        this.counrtyName = counrtyName;
    }

    public String getRecovery() {
        return recovery;
    }

    public void setRecovery(String recovery) {
        this.recovery = recovery;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getTodaydeath() {
        return todaydeath;
    }

    public void setTodaydeath(String todaydeath) {
        this.todaydeath = todaydeath;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTodayscases() {
        return todayscases;
    }

    public void setTodayscases(String todayscases) {
        this.todayscases = todayscases;
    }
}
