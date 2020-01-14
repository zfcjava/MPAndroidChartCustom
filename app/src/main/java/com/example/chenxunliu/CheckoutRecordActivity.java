package com.example.chenxunliu;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
 * CheckoutRecordActivity
 *
 * @date 2019-11-26
 */
public class CheckoutRecordActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    List<RecordBean> data = new ArrayList<>();
    private RecordAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_record);
        initView();
        initData();
    }

    private void initView() {

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("Library_CHENXUNLIU");
        TextView tv_table_name = findViewById(R.id.tv_table_name);
        tv_table_name.setText("Check Out Record");

        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        mAdapter = new RecordAdapter(data);
        mAdapter.setListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void onClick(RecordBean r) {
                Toast.makeText(CheckoutRecordActivity.this, r.getInfos(), Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private Handler h = new Handler();
    private void initData() {
//        for (int i = 0; i < 10; i++) {
//            RecordBean r = new RecordBean();
//            r.setName("12321");
//            r.setDueDate("2019-02-02 19:00:00");
//            r.setBookTitle("平凡的世界");
//            data.add(r);
//        }

        new Thread(){
            @Override
            public void run() {
                final List<RecordBean> list = new ArrayList<>();
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
                Log.e("listxx", list.toString());
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        data.addAll(list);
                        mAdapter.notifyDataSetChanged();

                    }
                });
            }
        }.start();


        mAdapter.notifyDataSetChanged();
    }


}
