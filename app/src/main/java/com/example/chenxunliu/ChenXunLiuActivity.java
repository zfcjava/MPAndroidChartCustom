package com.example.chenxunliu;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ChenXunLiuActivity extends AppCompatActivity{


    private BarChart barChart;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        barChart = findViewById(R.id.year_bill_chart5);
        initData();

    }

    private void initData() {
        initBarChart();
    }

    private void initBarChart(){
        Description description = barChart.getDescription();
        description.setText("电梯故障统计柱状图");
        description.setTextSize(10f);
        barChart.setNoDataText("no data.");
        // 集双指缩放
        barChart.setPinchZoom(false);
        barChart.animateY(2000);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        //1.链式调用
        barEntries.add(new BarEntry(1, new float[]{ 0, 0.21111f })
                .setPresentOneData(true)
                .setmDes("12.3%"));
        barEntries.add(new BarEntry(2,new float[]{0.11111f,0.3f})
                .setPresentOneData(true)
                .setmDes("12.3%"));
        barEntries.add(new BarEntry(3, new float[]{3.10111f,0.01f})
                .setPresentOneData(true)
                .setmDes("12.3%"));
        barEntries.add(new BarEntry(4, new float[]{2.11111f,3.3f})
                .setPresentOneData(true)
                .setmDes("12.3%"));
        barEntries.add(new BarEntry(5, new float[]{0.11111f,0.3f})
                .setPresentOneData(true)
                .setmDes("12.3%"));
        barEntries.add(new BarEntry(6, new float[]{1,1.3f})
                .setPresentOneData(true)
                .setmDes("12.3%"));

        BarDataSet barDataSet = new BarDataSet(barEntries, "error times").setDrawDesEnable(true);
        //设置描述字体的颜色
        barDataSet.setmDesColor(Color.RED);

        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);//是否显示柱子的数值
        barData.setValueTextSize(10f);//柱子上面标注的数值的字体大小
        barData.setBarWidth(0.8f);//每个柱子的宽度
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(true);//是否显示x坐标的数据
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x坐标数据的位置
        xAxis.setDrawGridLines(false);//是否显示网格线中与x轴垂直的网格线
//        xAxis.setLabelCount(6, true);//设置x轴显示的标签的个数
        final List<String> xValue = new ArrayList<>();
        xValue.add("zero");//index = 0 的位置的数据在IndexAxisValueFormatter中时不显示的。
        xValue.add("one");
        xValue.add("two");
        xValue.add("three");
        xValue.add("four");
        xValue.add("five");
        xValue.add("six");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));//设置x轴标签格式化器

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setEnabled(true);//设置右侧的y轴是否显示。包括y轴的那一条线和上面的标签都不显示
        rightYAxis.setDrawLabels(false);//设置y轴右侧的标签是否显示。只是控制y轴处的标签。控制不了那根线。
        rightYAxis.setDrawAxisLine(false);//这个方法就是专门控制坐标轴线的

        YAxis leftYAxis = barChart.getAxisLeft();
        leftYAxis.setEnabled(true);
//        leftYAxis.setStartAtZero(false);
        leftYAxis.setDrawLabels(true);
        leftYAxis.setDrawAxisLine(true);
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftYAxis.setDrawGridLines(true);//只有左右y轴标签都设置不显示水平网格线，图形才不会显示网格线
        leftYAxis.setDrawGridLinesBehindData(true);//设置网格线是在柱子的上层还是下一层（类似Photoshop的层级）
        leftYAxis.setGranularity(1f);//设置最小的间隔，防止出现重复的标签。这个得自己尝试一下就知道了。
//        leftYAxis.setAxisMinimum(0);//设置左轴最小值的数值。如果IndexAxisValueFormatter自定义了字符串的话，那么就是从序号为2的字符串开始取值。
        leftYAxis.setSpaceBottom(0);//左轴的最小值默认占有10dp的高度，如果左轴最小值为0，一般会去除0的那部分高度
        //自定义左侧标签的字符串，可以是任何的字符串、中文、英文等
//        leftYAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{ "0", "1", "2", "3", "4", "5"}));

    }

}
