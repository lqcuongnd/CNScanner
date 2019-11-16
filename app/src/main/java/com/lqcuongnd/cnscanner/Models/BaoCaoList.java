package com.lqcuongnd.cnscanner.Models;

import com.lqcuongnd.cnscanner.UserActivities.ReportListActivity;

import java.util.ArrayList;
import java.util.List;

public class BaoCaoList {
    private List<BaoCao> baoCaoList;

    public BaoCaoList(List<BaoCao> baoCaoList) {
        this.baoCaoList = baoCaoList;
    }

    private int partition(int low, int high) {
        BaoCao pivot = baoCaoList.get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (baoCaoList.get(j).getThoiGian().compareTo(pivot.getThoiGian()) > 0) {
                i++;
                // swap arr[i] and arr[j]
                BaoCao temp = baoCaoList.get(i);
                baoCaoList.set(i, baoCaoList.get(j));
                baoCaoList.set(j, temp);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        BaoCao temp = baoCaoList.get(i + 1);
        baoCaoList.set(i + 1, baoCaoList.get(high));
        baoCaoList.set(high, temp);

        return i + 1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    private void sort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    public void reverse() {
        sort(0, baoCaoList.size() - 1);
    }

    public List<BaoCao> getBaoCaoList() {
        return baoCaoList;
    }

    public void setBaoCaoList(List<BaoCao> baoCaoList) {
        this.baoCaoList = baoCaoList;
    }

    public void updateOne(BaoCao bc, ReportListActivity reportListActivity) {
        for (int i = 0; i < baoCaoList.size(); i++) {
            if (baoCaoList.get(i).getMa().compareTo(bc.getMa()) == 0) {
                baoCaoList.set(i, bc);
                break;
            }
        }
        reportListActivity.getList(2, baoCaoList);
    }

    public List<BaoCao> filter(String filt){
        List<BaoCao> list = new ArrayList<>();
        for (int i = 0; i < baoCaoList.size(); i++) {
            if (baoCaoList.get(i).getTrangThai().compareTo(filt) == 0) {
                list.add(baoCaoList.get(i));
            }
        }
        return list;
    }
}
