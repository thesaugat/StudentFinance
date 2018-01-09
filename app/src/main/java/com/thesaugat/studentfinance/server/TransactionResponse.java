package com.thesaugat.studentfinance.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionResponse {

@SerializedName("symbolno")
@Expose
private String symbolno;
@SerializedName("paidby")
@Expose
private String paidby;
@SerializedName("paiddatetime")
@Expose
private String paiddatetime;
@SerializedName("tutionfee")
@Expose
private String tutionfee;
@SerializedName("semesterfee")
@Expose
private String semesterfee;
@SerializedName("extras")
@Expose
private String extras;
@SerializedName("total")
@Expose
private String total;
@SerializedName("due_amount")
@Expose
private String dueAmount;

public String getSymbolno() {
return symbolno;
}

public void setSymbolno(String symbolno) {
this.symbolno = symbolno;
}

public String getPaidby() {
return paidby;
}

public void setPaidby(String paidby) {
this.paidby = paidby;
}

public String getPaiddatetime() {
return paiddatetime;
}

public void setPaiddatetime(String paiddatetime) {
this.paiddatetime = paiddatetime;
}

public String getTutionfee() {
return tutionfee;
}

public void setTutionfee(String tutionfee) {
this.tutionfee = tutionfee;
}

public String getSemesterfee() {
return semesterfee;
}

public void setSemesterfee(String semesterfee) {
this.semesterfee = semesterfee;
}

public String getExtras() {
return extras;
}

public void setExtras(String extras) {
this.extras = extras;
}

public String getTotal() {
return total;
}

public void setTotal(String total) {
this.total = total;
}

public String getDueAmount() {
return dueAmount;
}

public void setDueAmount(String dueAmount) {
this.dueAmount = dueAmount;
}

}