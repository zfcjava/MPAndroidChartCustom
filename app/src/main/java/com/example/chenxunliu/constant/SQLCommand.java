
/**
 * SQL commands
 * Including select/delete/update/insert
 */
package com.example.chenxunliu.constant;

public abstract class SQLCommand

/**
 * SQL commands
 * Including select/delete/update/insert
 */

{
    //query all students
    public static String QUERY_STUDENT = "select stid, stname from Student";
    //list all data in books table
    public static String QUERY_1 = "select lbcallnum, lbtitle from LibBook";
    //List the call numbers of books with the title ‘Database Management’
    public static String QUERY_2 = "select lbcallnum from LibBook where lbtitle like '%Database Management%'";
    public static String QUERY_3 = "select * from Student";
    public static String QUERY_4 = "select * from LibBook";
    public static String QUERY_5 = "select * from CheckOut";
    public static String QUERY_6 = "select * from LibBook";
    public static String QUERY_7 = "select * from Student";
    public static String QUERY_CHECKOUT = "SELECT C.stid, stname,lbtitle,coduedate,coreturned,cofine FROM Checkout c,Student s,LibBook b WHERE s.stid=c.stid AND c.lbcallnum=b.lbcallnum";

}

