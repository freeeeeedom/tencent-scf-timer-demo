package priv.freeeeeedom.utils.data;


import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * @author Nevernow
 */
abstract class BaseResult implements Serializable {
    private String state = "1";
    private String meg = "处理成功";
    private String des = "";

    public BaseResult() {
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMeg() {
        return this.meg;
    }

    public void setMeg(String meg) {
        this.meg = meg;
    }


    public String getDes() {
        return this.des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public BaseResult setError(String meg) {
        this.meg = meg;
        this.state = "0";
        return this;
    }

    public BaseResult setError(String meg, Exception ex) {
        this.meg = meg;
        this.state = "0";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        this.des = sw.toString();
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}