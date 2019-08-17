package com.example.ruru.data;

import android.support.v4.app.Fragment;

import com.example.ruru.Fragment.AFragment;
import com.example.ruru.Fragment.BFragment;
import com.example.ruru.library.listener.LoadDataStatus;
import com.example.ruru.listener.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.example.ruru.constant.ServeConstant.PAGE_SIZE;

public class DataModel {

    public static List<String> getData() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 16; i++) {
            list.add(i + "");
        }
        return list;
    }

    /**
     * 上拉加载获取更多数据
     */
    public static List<String> getPageData(int pageNum) {
        List<String> list = new ArrayList<>();
        switch (pageNum) {
            case 1:
                for (int i = 0; i < PAGE_SIZE; i++) {
                    list.add(i + "");
                }
                break;
            case 2:
                for (int i = PAGE_SIZE; i < PAGE_SIZE * 2; i++) {
                    list.add(i + "");
                }
                break;
        }
        return list;
    }

    /**
     * 分页获取数据
     */
    public static List<String> getPurePageData(int pageNum, int pageSize) {
        List<String> list = new ArrayList<>();
        switch (pageNum) {
            case 1:
                for (int i = 0; i < pageSize; i++) {
                    list.add(i + "");
                }
                break;
            case 2:
                for (int i = pageSize; i < pageSize * 2 - 5; i++) { //这里-5是为了模拟数据
                    list.add(i + "");
                }
                break;
        }
        return list;
    }

    /**
     * 分页获取数据1
     */
    public static List<String> getPurePageData1(int pageNum, int pageSize) {
        List<String> list = new ArrayList<>();
        switch (pageNum) {
            case 1:
                for (int i = 0; i < pageSize; i++) {
                    list.add("A" + i);
                }
                break;
            case 2:
                for (int i = pageSize; i < pageSize * 2; i++) {
                    list.add("A" + i);
                }
                break;
        }
        return list;
    }

    public static List<Fragment> getFragmentList() {
        List<Fragment> list = new ArrayList<>();
        list.add(new AFragment());
        list.add(new AFragment());
        return list;
    }

    public static List<String> getTitlesList() {
        List<String> list = Arrays.asList("A1界面", "A2界面");
        return list;
    }
}
