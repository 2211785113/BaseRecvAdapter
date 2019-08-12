package com.example.ruru.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ruru.R;
import com.example.ruru.activity.ClickOne;
import com.example.ruru.activity.ClickTwo;
import com.example.ruru.activity.EmptyItem;
import com.example.ruru.activity.FootItem;
import com.example.ruru.activity.HeadAndFootItem;
import com.example.ruru.activity.HeadItem;
import com.example.ruru.activity.MultiItem;
import com.example.ruru.activity.MultiPageLoad;
import com.example.ruru.activity.PageLoad;
import com.example.ruru.activity.SingleItem;
import com.example.ruru.activity.SortHead;


public class MainActivity extends AppCompatActivity {

    private int btnId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void single(View v) {
        startActivity(new Intent(this, SingleItem.class));
    }

    public void multi(View v) {
        startActivity(new Intent(this, MultiItem.class));
    }

    public void head(View v) {
        startActivity(new Intent(this, HeadItem.class));
    }

    public void foot(View v) {
        startActivity(new Intent(this, FootItem.class));
    }

    public void headAndFoot(View v) {
        startActivity(new Intent(this, HeadAndFootItem.class));
    }

    public void emptyItem(View v) {
        startActivity(new Intent(this, EmptyItem.class));
    }

    public void sorthead(View v) {
        startActivity(new Intent(this, SortHead.class));
    }

    public void clickItemOne(View v) {
        startActivity(new Intent(this, ClickOne.class));
    }

    public void clickItemTwo(View v) {
        startActivity(new Intent(this, ClickTwo.class));
    }

    public void pageLoad(View v) {
        startActivity(new Intent(this, PageLoad.class));
    }

    public void multiPageLoad(View v) {
        startActivity(new Intent(this, MultiPageLoad.class));
    }
}
