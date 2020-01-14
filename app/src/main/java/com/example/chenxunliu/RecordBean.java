package com.example.chenxunliu;

/**
 *
 * @date 2019-11-26
 */
public class RecordBean {

    private String name;

    private String dueDate;

    private String bookTitle;

    private String stid;

    private String returnState;

    private String fine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "RecordBean{" +
                "name='" + name + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", stid='" + stid + '\'' +
                ", returnState='" + returnState + '\'' +
                ", fine='" + fine + '\'' +
                '}';
    }

    String getInfos(){
        StringBuilder sb = new StringBuilder();
        sb.append("StudentId:").append(stid).append("\r\n").
                append("StudentName:").append(name).append("\r\n").
                append("BookTitle:").append(bookTitle).append("\r\n").
                append("Due Date:").append(dueDate).append("\r\n").
                append("Return State:").append(returnState.equals("Y")).append("\r\n").
                append("Fine:").append("$").append(fine).append("\r\n");

        return sb.toString();
    }
}
