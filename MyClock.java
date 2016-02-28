/**
 * This code is a simple analog clock, my first code using View class and might contain a lot of errors
 * Created by - Amrutansu Garanaik
 * Date       - 27th Feb 2016
 * Copyright  - Feel free to use for anything, just remember I am not responsible for anything
 */
package com.example.amrutansu.myanalogclock_ag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyClock(this));
    }
}



class  MyClock extends View {
    public MyClock(Context context) {

        super(context);
        paint = new Paint();
    }
    int r=0,g=0,b=0;    //back ground color
    Paint paint;
    int width, height;  //of the screen
    protected void onDraw(Canvas canvas)
    {
        width = canvas.getWidth();
        height = canvas.getHeight();
        float cent_x = width/2;
        float cent_y = height/2;


        //Following for initial background on which we will draw the clock
        newRGB();               //Get New Color
        canvas.drawRGB(r,g,b);  //set new color and clears the screen







        //draw the circle indicating the entire clock
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(width/2, height/2, (width/2)-50, paint);


        //draws outer ring of the circle
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(width/2, height/2, (width/2)-50, paint);


        //draws the inner ring of the circle
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(width/2, height/2, (width/2)-100, paint);



        //center dot of the circle
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(width / 2, height / 2, 5, paint);



        //draws my name on the screen
        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);
        paint.setAlpha(155);
        canvas.drawText("Amrutansu",cent_x, cent_y - 50, paint );



        paint.setColor(Color.BLACK);


        //draws the numbers on the face of the clock
        float x = (width/2);
        float y;    //value is assigned in the loop below
        paint.setTextSize(30);
        float ang = 0;

        paint.setTextAlign(Paint.Align.CENTER);
        for(int i=12;i>0;i--)   //printing the numbers
        {
                if(i>=4 && i<=9)
                    y=(height/2) - (width/2)  + 75;     //just some adjustments without which it looked odd
                else
                    y=(height/2) - (width/2)  + 85;
                //Got the following formulae from the Internet
                double temp_x = (x-cent_x) * Math.cos(ang) - (y-cent_y) * Math.sin(ang) + cent_x;
                double temp_y = (y-cent_y) * Math.cos(ang) + (x-cent_x) * Math.sin(ang) + cent_y;


                String str = i + "";
                canvas.drawText(str,(float)temp_x,(float)temp_y,paint);
                ang-=0.523599;  //30 degrees
        }

        //Getting present time to print
        long a = System.currentTimeMillis(); //Gets UTC time, so if anytime is wrong below just add 5 h 30 m to time
        a/=1000;
        long sec = a%60;
        a/=60;
        long min = a%60;
        a/=60;
        min+=30;
        if(min>=60)
            a++;
        min%=60;

        long hour= a%24;
        hour+=5;
        hour%=12;

        //Drawing second hand
        float second_angle;
        second_angle = (sec * 6) * (float) Math.PI / 180;
        x = (width/2) ;
        y = (height/2) - (width/2) + 100;
        double temp_x = (x-cent_x) * Math.cos(second_angle) - (y-cent_y) * Math.sin(second_angle) + cent_x;
        double temp_y = (y-cent_y) * Math.cos(second_angle) + (x-cent_x) * Math.sin(second_angle) + cent_y;
        paint.setStrokeWidth(2);    //second hand
        canvas.drawLine(cent_x,cent_y,(float)temp_x,(float)temp_y,paint);


        //Drawing hour hand
        float minute_angle;
        minute_angle = (min * 6) * (float) Math.PI / 180;
        x = (width/2) ;
        y = (height/2) - (width/2) + 100;
        temp_x = (x-cent_x) * Math.cos(minute_angle) - (y-cent_y) * Math.sin(minute_angle) + cent_x;
        temp_y = (y-cent_y) * Math.cos(minute_angle) + (x-cent_x) * Math.sin(minute_angle) + cent_y;
        paint.setStrokeWidth(5);    //minute hand
        canvas.drawLine(cent_x,cent_y,(float)temp_x,(float)temp_y,paint);


        float hour_angle;
        hour_angle = (((60 * hour) + min)/2) * (float) Math.PI / 180;
        x = (width/2) ;
        y = (height/2) - (width/2) + 150;
        temp_x = (x-cent_x) * Math.cos(hour_angle) - (y-cent_y) * Math.sin(hour_angle) + cent_x;
        temp_y = (y-cent_y) * Math.cos(hour_angle) + (x-cent_x) * Math.sin(hour_angle) + cent_y;
        paint.setStrokeWidth(5);    //hour hand
        canvas.drawLine(cent_x,cent_y,(float)temp_x,(float)temp_y,paint);




        invalidate();
    }

    private void newRGB()   //This function will return new color for onDraw to print
    {



       Random rand = new Random(System.currentTimeMillis());
       int a = rand.nextInt();
        if(a%3==0)
                r++;
        else if(a%3==1)
                g++;
        else
                b++;

    }
}