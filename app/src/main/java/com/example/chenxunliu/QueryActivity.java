package com.example.chenxunliu;

import com.example.chenxunliu.constant.SQLCommand;
import com.example.chenxunliu.util.DBOperator;
import com.example.chenxunliu.view.TableView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QueryActivity extends AppCompatActivity implements OnClickListener {

    Button backBtn,resultBtn;
    Spinner querySpinner;
    ScrollView scrollView;
    private Button goCheckOutList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_chenxunliu);

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("Library_CHENXUNLIU");

        backBtn=(Button)this.findViewById(R.id.goBack_btn);
        goCheckOutList=(Button)this.findViewById(R.id.goCheckOutList);
        backBtn.setOnClickListener(this);
        goCheckOutList.setOnClickListener(this);
        resultBtn=(Button)this.findViewById(R.id.showresult_btn);
        resultBtn.setOnClickListener(this);
        querySpinner=(Spinner)this.findViewById(R.id.querylist_spinner);
        scrollView=(ScrollView)this.findViewById(R.id.scrollview_queryresults);
    }

    @Override
    public void onClick(View v)
    {
        String sql="";
        int id=v.getId();
        if (id==R.id.showresult_btn){
            //show query result
            int pos=querySpinner.getSelectedItemPosition();
            if (pos==Spinner.INVALID_POSITION){
                //User doesn't choose any query, show warning
                Toast.makeText(this.getBaseContext(), "Please choose a query!", Toast.LENGTH_SHORT).show();
                return;
            }
            scrollView.removeAllViews();
            if (pos==0){
                //show all books
                sql=SQLCommand.QUERY_1;
            }else if (pos==1){
                //list the call numbers of books with the title ‘Database Management’
                sql=SQLCommand.QUERY_2;
            }else if (pos==2){
                sql=SQLCommand.QUERY_3;
            }else if (pos==3){
                sql=SQLCommand.QUERY_4;
            }else if (pos==4){
                sql=SQLCommand.QUERY_5;
            }else if (pos==5){
                sql=SQLCommand.QUERY_6;
            }else if (pos==6){
                sql=SQLCommand.QUERY_7;
            }
            Log.e("SqlSentence", sql);

            Cursor cursor=DBOperator.getInstance().execQuery(sql);
            scrollView.addView(new TableView(this.getBaseContext(),cursor));
//            DBOperator.getInstance().closeDB();
        }else if (id==R.id.goBack_btn){
            //go back to main screen
            Intent intent = new Intent(this, ChenXunLiuActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.goCheckOutList) {
            Intent intent = new Intent(this, CheckoutRecordActivity.class);
            this.startActivity(intent);
        }
    }
}

