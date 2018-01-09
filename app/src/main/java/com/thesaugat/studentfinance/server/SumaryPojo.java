package com.thesaugat.studentfinance.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SumaryPojo {

@SerializedName("faculty")
@Expose
private String faculty;
@SerializedName("due")
@Expose
private String due;
@SerializedName("deadline")
@Expose
private String deadline;

public String getFaculty() {
return faculty;
}

public void setFaculty(String faculty) {
this.faculty = faculty;
}

public String getDue() {
return due;
}

public void setDue(String due) {
this.due = due;
}

public String getDeadline() {
return deadline;
}

public void setDeadline(String deadline) {
this.deadline = deadline;
}

}