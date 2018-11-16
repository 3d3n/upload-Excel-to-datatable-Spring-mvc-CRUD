package com.esc.calllog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        Application application = new Application();
//        application.timeFormatter();
    }

//    public Date dateFormatter()  {
//        String date="31/12/1998";
//        //date = null;
//        Date date1= null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(date+"\t"+date1);
//
//        return date1;
//    }

//    //format time
//    public  Time timeFormatter() {
//        String time="4:00:00";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//        try {
//            java.util.Date d1 =(java.util.Date)dateFormat.parse(time);
//            java.sql.Time ppstime = new java.sql.Time(d1.getTime());
//            //dtime = new Time(dateFormat.parse(time).getTime());
//            System.out.println(time+"\n"+ppstime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
