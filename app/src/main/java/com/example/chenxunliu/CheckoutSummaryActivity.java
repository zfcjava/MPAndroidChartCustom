package com.example.chenxunliu;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chenxunliu.constant.SQLCommand;
import com.example.chenxunliu.util.DBOperator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * CheckoutSummaryActivity
 *
 * @date 2019-11-26
 */
public class CheckoutSummaryActivity extends AppCompatActivity {


    private BarChart barChart;
    //左侧Y轴
    private YAxis leftAxis;
    //右侧Y轴
    private YAxis rightAxis;
    //X轴
    private XAxis xAxis;
    //图例
    private Legend legend;
    private LimitLine limitLine;

    private List<RecordBean> list;
    private List<CheckoutSummary> data;

    private String[] months = {" ","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_summary);
        initView();
        initData();
    }

    private void initView() {

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("Library_CHENXUNLIU");
        TextView tv_table_name = findViewById(R.id.tv_table_name);
        tv_table_name.setText("Check Out Summary");

        barChart = findViewById(R.id.chart);
        initChart();

    }



    private void initChart() {
        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setDrawValueAboveBar(true);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        //显示边框
        barChart.setDrawBorders(true);
        //设置动画效果

//        barChart.animateY(1000, Easing.EasingOption.Linear);
//        barChart.animateX(1000, Easing.EasingOption.Linear);

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(12);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(0f);
//        xAxis.setValueFormatter(new IAxisValueFormatter(){
//
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return months[(int)value];
//            }
//        });

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
//        rightAxis.setAxisMinimum(0f);

        /***折线图例 标签 设置***/
        legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);

        ////不显示X轴网格线
        xAxis.setDrawGridLines(false);

        // 取消 左边 Y轴 坐标线
        leftAxis.setDrawAxisLine(false);
        // 取消 横向 网格线
        leftAxis.setDrawGridLines(false);
        // 设置 Y轴 的刻度数量
//        leftAxis.setLabelCount(2);

        //X轴自定义值
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return value+"";
//            }
//        });
        ////右侧Y轴网格线设置为虚线
//        rightAxis.enableGridDashedLine(10f, 10f, 0f);
    }


    Handler h = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            if (list != null) {
                statistics(list);
                showBarChart(data,"checkout summary",Color.BLUE);

            }

        }
    };

    private void statistics(List<RecordBean> list) {
        for (RecordBean r : list) {
            String[] a = r.getDueDate().trim().split("-");
            int month = Integer.parseInt(a[1])-1;
            int tmp = data.get(month).getBookNum();
            tmp++;
            data.get(month).setBookNum(tmp);
        }
    }

    private void initData() {

        data = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            CheckoutSummary cs = new CheckoutSummary();
            cs.setMonth(i);
            cs.setBookNum(0);
            data.add(cs);
        }

        new Thread(){
            @Override
            public void run() {
                list = new ArrayList<>();
                Cursor cursor= DBOperator.getInstance().execQuery(SQLCommand.QUERY_CHECKOUT);
                while (cursor.moveToNext()) {
                    RecordBean r = new RecordBean();
                    r.setStid(cursor.getString(cursor.getColumnIndex("stid")));
                    r.setName(cursor.getString(cursor.getColumnIndex("stname")));
                    r.setBookTitle(cursor.getString(cursor.getColumnIndex("lbtitle")));
                    r.setDueDate(cursor.getString(cursor.getColumnIndex("coduedate")));
                    r.setReturnState(cursor.getString(cursor.getColumnIndex("coreturned")));
                    r.setFine(cursor.getString(cursor.getColumnIndex("cofine")));

                    list.add(r);
                }
                cursor.close();
                h.sendEmptyMessage(1);
            }
        }.start();


    }

    public void showBarChart(List<CheckoutSummary> dateValueList, String name, int color) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < dateValueList.size(); i++) {
            /**
             * 此处还可传入Drawable对象 BarEntry(float x, float y, Drawable icon)
             * 即可设置柱状图顶部的 icon展示
             */
            BarEntry barEntry = new BarEntry(dateValueList.get(i).getMonth(), dateValueList.get(i).getBookNum(),dateValueList.get(i).getBookNum());
            entries.add(barEntry);
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, name);
        initBarDataSet(barDataSet, color);

//        // 添加多个BarDataSet时
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(barDataSet);
//        BarData data = new BarData(dataSets);

        barDataSet.setDrawValues(true);//是否显示柱子上面的数值
//
//        barDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                int n = (int) value;
//                return n+"";
//            }
//        });

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
    }


    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     *
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setValueTextSize(10f);
        barDataSet.setValueTextColor(color);
    }



}
