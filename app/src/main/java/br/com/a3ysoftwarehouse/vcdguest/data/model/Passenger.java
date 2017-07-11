package br.com.a3ysoftwarehouse.vcdguest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class Passenger extends RealmObject implements Parcelable {
    public static final Parcelable.Creator<Passenger> CREATOR = new Parcelable.Creator<Passenger>() {
        @Override
        public Passenger createFromParcel(Parcel source) {
            return new Passenger(source);
        }

        @Override
        public Passenger[] newArray(int size) {
            return new Passenger[size];
        }
    };
    @PrimaryKey
    private String COD;
    private String DATA;
    private String PAX;
    private String QUARTO;
    private String VOO;
    private String LOC;
    private String ETKT;
    private String CIA;
    private String GRUPO;
    private String EMISSOR;
    private String AGENCIA;
    private String ESCOLA;
    private String SEXO;
    private String DATANASCIMENTO;
    private String CELPAX;
    private String EMAILPAX;
    private String RESPPAX;
    private String FONERESP;
    private String EMAILRESP;
    private String ROMMATES;
    private String ROMMATE1;
    private String ROMMATE2;
    private String ROMMATE3;
    private String CODR1;
    private String CODR2;
    private String CODR3;
    private String REFEICOES;
    private String OPCIONAIS;
    private String OPCIONAL1;
    private String OPCIONAL2;
    private String OPCIONAL3;
    private String OPCIONAL4;
    private String OPCIONAL5;
    private String OPCIONAL6;
    private String OPCIONAL7;
    private RealmList<Tag> tagList;

    public Passenger() {
        tagList = new RealmList<>();
    }

    protected Passenger(Parcel in) {
        this.tagList = new RealmList<>();
        in.readList(this.tagList, Tag.class.getClassLoader());
        this.COD = in.readString();
        this.DATA = in.readString();
        this.PAX = in.readString();
        this.QUARTO = in.readString();
        this.VOO = in.readString();
        this.LOC = in.readString();
        this.ETKT = in.readString();
        this.CIA = in.readString();
        this.GRUPO = in.readString();
        this.EMISSOR = in.readString();
        this.AGENCIA = in.readString();
        this.ESCOLA = in.readString();
        this.SEXO = in.readString();
        this.DATANASCIMENTO = in.readString();
        this.CELPAX = in.readString();
        this.EMAILPAX = in.readString();
        this.RESPPAX = in.readString();
        this.FONERESP = in.readString();
        this.EMAILRESP = in.readString();
        this.ROMMATES = in.readString();
        this.ROMMATE1 = in.readString();
        this.ROMMATE2 = in.readString();
        this.ROMMATE3 = in.readString();
        this.CODR1 = in.readString();
        this.CODR2 = in.readString();
        this.CODR3 = in.readString();
        this.REFEICOES = in.readString();
        this.OPCIONAIS = in.readString();
        this.OPCIONAL1 = in.readString();
        this.OPCIONAL2 = in.readString();
        this.OPCIONAL3 = in.readString();
        this.OPCIONAL4 = in.readString();
        this.OPCIONAL5 = in.readString();
        this.OPCIONAL6 = in.readString();
        this.OPCIONAL7 = in.readString();
    }

    public String getCOD() {
        return COD;
    }

    public void setCOD(String COD) {
        this.COD = COD;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getPAX() {
        return PAX;
    }

    public void setPAX(String PAX) {
        this.PAX = PAX;
    }

    public String getQUARTO() {
        return QUARTO;
    }

    public void setQUARTO(String QUARTO) {
        this.QUARTO = QUARTO;
    }

    public String getVOO() {
        return VOO;
    }

    public void setVOO(String VOO) {
        this.VOO = VOO;
    }

    public String getLOC() {
        return LOC;
    }

    public void setLOC(String LOC) {
        this.LOC = LOC;
    }

    public String getETKT() {
        return ETKT;
    }

    public void setETKT(String ETKT) {
        this.ETKT = ETKT;
    }

    public String getCIA() {
        return CIA;
    }

    public void setCIA(String CIA) {
        this.CIA = CIA;
    }

    public String getGRUPO() {
        return GRUPO;
    }

    public void setGRUPO(String GRUPO) {
        this.GRUPO = GRUPO;
    }

    public String getEMISSOR() {
        return EMISSOR;
    }

    public void setEMISSOR(String EMISSOR) {
        this.EMISSOR = EMISSOR;
    }

    public String getAGENCIA() {
        return AGENCIA;
    }

    public void setAGENCIA(String AGENCIA) {
        this.AGENCIA = AGENCIA;
    }

    public String getESCOLA() {
        return ESCOLA;
    }

    public void setESCOLA(String ESCOLA) {
        this.ESCOLA = ESCOLA;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getDATANASCIMENTO() {
        return DATANASCIMENTO;
    }

    public void setDATANASCIMENTO(String DATANASCIMENTO) {
        this.DATANASCIMENTO = DATANASCIMENTO;
    }

    public String getCELPAX() {
        return CELPAX;
    }

    public void setCELPAX(String CELPAX) {
        this.CELPAX = CELPAX;
    }

    public String getEMAILPAX() {
        return EMAILPAX;
    }

    public void setEMAILPAX(String EMAILPAX) {
        this.EMAILPAX = EMAILPAX;
    }

    public String getRESPPAX() {
        return RESPPAX;
    }

    public void setRESPPAX(String RESPPAX) {
        this.RESPPAX = RESPPAX;
    }

    public String getFONERESP() {
        return FONERESP;
    }

    public void setFONERESP(String FONERESP) {
        this.FONERESP = FONERESP;
    }

    public String getEMAILRESP() {
        return EMAILRESP;
    }

    public void setEMAILRESP(String EMAILRESP) {
        this.EMAILRESP = EMAILRESP;
    }

    public String getROMMATES() {
        return ROMMATES;
    }

    public void setROMMATES(String ROMMATES) {
        this.ROMMATES = ROMMATES;
    }

    public String getROMMATE1() {
        return ROMMATE1;
    }

    public void setROMMATE1(String ROMMATE1) {
        this.ROMMATE1 = ROMMATE1;
    }

    public String getROMMATE2() {
        return ROMMATE2;
    }

    public void setROMMATE2(String ROMMATE2) {
        this.ROMMATE2 = ROMMATE2;
    }

    public String getROMMATE3() {
        return ROMMATE3;
    }

    public void setROMMATE3(String ROMMATE3) {
        this.ROMMATE3 = ROMMATE3;
    }

    public String getCODR1() {
        return CODR1;
    }

    public void setCODR1(String CODR1) {
        this.CODR1 = CODR1;
    }

    public String getCODR2() {
        return CODR2;
    }

    public void setCODR2(String CODR2) {
        this.CODR2 = CODR2;
    }

    public String getCODR3() {
        return CODR3;
    }

    public void setCODR3(String CODR3) {
        this.CODR3 = CODR3;
    }

    public String getREFEICOES() {
        return REFEICOES;
    }

    public void setREFEICOES(String REFEICOES) {
        this.REFEICOES = REFEICOES;
    }

    public String getOPCIONAIS() {
        return OPCIONAIS;
    }

    public void setOPCIONAIS(String OPCIONAIS) {
        this.OPCIONAIS = OPCIONAIS;
    }

    public String getOPCIONAL1() {
        return OPCIONAL1;
    }

    public void setOPCIONAL1(String OPCIONAL1) {
        this.OPCIONAL1 = OPCIONAL1;
    }

    public String getOPCIONAL2() {
        return OPCIONAL2;
    }

    public void setOPCIONAL2(String OPCIONAL2) {
        this.OPCIONAL2 = OPCIONAL2;
    }

    public String getOPCIONAL3() {
        return OPCIONAL3;
    }

    public void setOPCIONAL3(String OPCIONAL3) {
        this.OPCIONAL3 = OPCIONAL3;
    }

    public String getOPCIONAL4() {
        return OPCIONAL4;
    }

    public void setOPCIONAL4(String OPCIONAL4) {
        this.OPCIONAL4 = OPCIONAL4;
    }

    public String getOPCIONAL5() {
        return OPCIONAL5;
    }

    public void setOPCIONAL5(String OPCIONAL5) {
        this.OPCIONAL5 = OPCIONAL5;
    }

    public String getOPCIONAL6() {
        return OPCIONAL6;
    }

    public void setOPCIONAL6(String OPCIONAL6) {
        this.OPCIONAL6 = OPCIONAL6;
    }

    public String getOPCIONAL7() {
        return OPCIONAL7;
    }

    public void setOPCIONAL7(String OPCIONAL7) {
        this.OPCIONAL7 = OPCIONAL7;
    }

    public RealmList<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(RealmList<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.tagList);
        dest.writeString(this.COD);
        dest.writeString(this.DATA);
        dest.writeString(this.PAX);
        dest.writeString(this.QUARTO);
        dest.writeString(this.VOO);
        dest.writeString(this.LOC);
        dest.writeString(this.ETKT);
        dest.writeString(this.CIA);
        dest.writeString(this.GRUPO);
        dest.writeString(this.EMISSOR);
        dest.writeString(this.AGENCIA);
        dest.writeString(this.ESCOLA);
        dest.writeString(this.SEXO);
        dest.writeString(this.DATANASCIMENTO);
        dest.writeString(this.CELPAX);
        dest.writeString(this.EMAILPAX);
        dest.writeString(this.RESPPAX);
        dest.writeString(this.FONERESP);
        dest.writeString(this.EMAILRESP);
        dest.writeString(this.ROMMATES);
        dest.writeString(this.ROMMATE1);
        dest.writeString(this.ROMMATE2);
        dest.writeString(this.ROMMATE3);
        dest.writeString(this.CODR1);
        dest.writeString(this.CODR2);
        dest.writeString(this.CODR3);
        dest.writeString(this.REFEICOES);
        dest.writeString(this.OPCIONAIS);
        dest.writeString(this.OPCIONAL1);
        dest.writeString(this.OPCIONAL2);
        dest.writeString(this.OPCIONAL3);
        dest.writeString(this.OPCIONAL4);
        dest.writeString(this.OPCIONAL5);
        dest.writeString(this.OPCIONAL6);
        dest.writeString(this.OPCIONAL7);
    }
}
