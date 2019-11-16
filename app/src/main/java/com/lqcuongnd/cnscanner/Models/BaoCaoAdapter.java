package com.lqcuongnd.cnscanner.Models;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lqcuongnd.cnscanner.UserActivities.ReportDetailActivity;
import com.lqcuongnd.cnscanner.R;

import java.util.List;

public class BaoCaoAdapter extends RecyclerView.Adapter<BaoCaoAdapter.BaoCaoViewHolder> {
    private List<BaoCao> baoCaoList;
    private Activity     activity;
    BaoCao baoCao;

    /**
     * Contructor
     */
    public BaoCaoAdapter(Activity activity, List<BaoCao> baoCaoList) {
        this.activity = activity;
        this.baoCaoList = baoCaoList;
    }

    /**
     * Create ViewHolder
     */
    public class BaoCaoViewHolder extends RecyclerView.ViewHolder {
        private TextView phong;
        private TextView loi;
        private TextView chiTiet;
        private TextView trangThai;

        public BaoCaoViewHolder(View itemView) {
            super(itemView);
            phong = (TextView) itemView.findViewById(R.id.lblPhong);
            loi = (TextView) itemView.findViewById(R.id.lblLoi);
            chiTiet = (TextView) itemView.findViewById(R.id.lblChiTiet);
            trangThai = (TextView) itemView.findViewById(R.id.lblTrangThai);
        }
    }

    @Override
    public BaoCaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Get layout */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new BaoCaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaoCaoViewHolder holder, final int position) {
        /** Set Value*/
        baoCao = baoCaoList.get(position);
        holder.phong.setText("Phòng " + baoCao.getTenPhong());
        holder.loi.setText(baoCao.getLoi());
        String ct = baoCao.getChiTiet();
        if(ct.length() > 25){
            ct = ct.substring(0,25) + " ...";
        }
        holder.chiTiet.setText(ct);
        holder.trangThai.setText(baoCao.getTrangThai());

        if (holder.trangThai.getText().toString().compareTo("Đã xử lý") == 0)
            holder.trangThai.setTextColor(Color.parseColor("#0f7a4a"));
        if (holder.trangThai.getText().toString().compareTo("Đang xử lý") == 0)
            holder.trangThai.setTextColor(Color.parseColor("#2640bf"));

        /*Sự kiện click vào item*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baoCao = baoCaoList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("baocao", baoCao);
                Intent intent = new Intent(activity, ReportDetailActivity.class);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return baoCaoList.size();
    }
}
