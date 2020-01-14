package com.example.chenxunliu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chenxunliu.constant.SQLCommand;
import com.example.chenxunliu.util.DBOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * CheckoutReturnActivity
 *
 * @date 2019-11-26
 */
public class CheckoutReturnActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_checkout;
    private Button btn_return;
    private Button btn_go_back;
    private Button btn_summay;

    TextView add_day;
    TextView add_month;
    TextView add_year;
    TextView minus_day;
    TextView minus_month ;
    TextView minus_year;

    TextView tv_day;
    TextView tv_month ;
    TextView tv_year;

    int[] YEAR = new int[100];
    String[] MONTH = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] DAY = new String[31];

    int yearIndex = 2019-1970;
    int monthIndex = 11;
    int dayIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_return);
        initDates();
        initView();
        initData();
    }

    private void initDates() {
        for (int i = 0; i < 100; i++) {
            YEAR[i] = i+1970;
        }

        for (int i = 0; i < 31; i++) {
            DAY[i] = (i+1)<10? ("0"+(i+1)):(i+1)+"";
        }

    }

    private void initView() {

        btn_checkout = findViewById(R.id.btn_checkout);
        btn_return = findViewById(R.id.btn_return);
        btn_go_back = findViewById(R.id.btn_go_back);
        btn_summay = findViewById(R.id.btn_summay);



        add_day = findViewById(R.id.add_day);
        add_month = findViewById(R.id.add_month);
        add_year = findViewById(R.id.add_year);
        minus_day = findViewById(R.id.minus_day);
        minus_month = findViewById(R.id.minus_month);
        minus_year = findViewById(R.id.minus_year);
        tv_day = findViewById(R.id.tv_day);
        tv_month = findViewById(R.id.tv_month);
        tv_year = findViewById(R.id.tv_year);


        btn_checkout.setOnClickListener(this);
        btn_return.setOnClickListener(this);
        btn_go_back.setOnClickListener(this);
        btn_summay.setOnClickListener(this);

        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_day:
                        dayIndex++;
                        break;
                    case R.id.add_month:
                        monthIndex++;
                        if (monthIndex >= MONTH.length) {
                            monthIndex =0;
                        }
                        break;
                    case R.id.add_year:
                        yearIndex++;
                        if (yearIndex >= YEAR.length) {
                            yearIndex =0;
                        }
                        break;
                    case R.id.minus_day:
                        dayIndex--;
                        break;
                    case R.id.minus_month:
                        monthIndex--;
                        if (monthIndex == -1) {
                            monthIndex = MONTH.length-1;
                        }
                        break;
                    case R.id.minus_year:
                        yearIndex--;
                        if (yearIndex == -1) {
                            yearIndex = YEAR.length-1;
                        }
                        break;

                     default:
                         return;
                }
                checkDays();
            }
        };
        add_day.setOnClickListener(l);
        add_month.setOnClickListener(l);
        add_year.setOnClickListener(l);
        minus_day.setOnClickListener(l);
        minus_month.setOnClickListener(l);
        minus_year.setOnClickListener(l);

        updateDateView();
    }

    private void checkDays() {
        //获取当月有多少天
        int toalDayOfMonth = days(YEAR[yearIndex],monthIndex+1);
        //当前的Index是否在天中
        if(dayIndex==-1){
            dayIndex = toalDayOfMonth-1;
        }else if(dayIndex>=toalDayOfMonth){
            dayIndex= 0;
        }

        updateDateView();
    }

    public int days(int year, int month) {
        int days = 0;
        if (month != 2) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;
                    break;
                default:
                    return -1;
            }
        } else {
            //闰年
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                days = 29;
            }
            else{
                days = 28;
            }

        }
        return days;

    }


    private void initData() {

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.btn_checkout:
                i.setClass(this, CheckoutRecordActivity.class);
                break;
            case R.id.btn_return:
                this.finish();
                return;
            case R.id.btn_go_back:
                i.setClass(this, ChenXunLiuActivity.class);
                break;
            case R.id.btn_summay:
                i.setClass(this, CheckoutSummaryActivity.class);
                break;
            default:
                return;
        }
        startActivity(i);
    }

    void updateDateView(){
        tv_day.setText(DAY[dayIndex]+"");
        tv_month.setText(MONTH[monthIndex]+"");
        tv_year.setText(YEAR[yearIndex]+"");
    }
}
