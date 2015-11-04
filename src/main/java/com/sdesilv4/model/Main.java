package com.sdesilv4.model;

import java.util.Date;

/**
 * Created by Nicolas on 03/11/15.
 */
public class Main {
    public static void main(String[] args)
    {
        Action a = new Action("ESILV", "FR24512154651", -5.5, 5122121, new Date(),5,41,1);
        System.out.println(a.toString());
    }
}
