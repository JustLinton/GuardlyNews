package com.berrysdu.guardly;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FormatManager {

    public static final int TYPE_TIME=0;
    public static final int TYPE_NUMBER=1;

    long curTimeMil;
    DecimalFormat decimalFormat;
    SimpleDateFormat simpleDateFormat;

    public FormatManager(){
        curTimeMil=System.currentTimeMillis();}

    public FormatManager(String format,int type){
        curTimeMil=System.currentTimeMillis();

        switch (type){
            case TYPE_TIME:
                simpleDateFormat=new SimpleDateFormat(format);
                break;
            case TYPE_NUMBER:
                decimalFormat=new DecimalFormat(format);
                break;
        }
    }

    public String getCurMonthMMM(){
        SimpleDateFormat format=new SimpleDateFormat("MMM");
        return format.format(curTimeMil);
    }

    public String getCurDayDD(){
        SimpleDateFormat format=new SimpleDateFormat("dd");
        return format.format(curTimeMil);
    }
}
