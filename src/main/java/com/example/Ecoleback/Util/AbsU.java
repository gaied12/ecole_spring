package com.example.Ecoleback.Util;

import java.util.Date;

public class AbsU {
    private String idStud;
    private Long idSubj;
    private String dateAbs;
    private int hStart;
    private int Mstart;
    private int hEnd ;

    public String getIdStud() {
        return idStud;
    }

    public void setIdStud(String idStud) {
        this.idStud = idStud;
    }

    public Long getIdSubj() {
        return idSubj;
    }

    public void setIdSubj(Long idSubj) {
        this.idSubj = idSubj;
    }

    public String getDateAbs() {
        return dateAbs;
    }

    public void setDateAbs(String dateAbs) {
        this.dateAbs = dateAbs;
    }

    public int gethStart() {
        return hStart;
    }

    public void sethStart(int hStart) {
        this.hStart = hStart;
    }

    public int getMstart() {
        return Mstart;
    }

    public void setMstart(int mstart) {
        Mstart = mstart;
    }

    public int gethEnd() {
        return hEnd;
    }

    public void sethEnd(int hEnd) {
        this.hEnd = hEnd;
    }

    public int getMend() {
        return mend;
    }

    public void setMend(int mend) {
        this.mend = mend;
    }

    private int mend;

}
