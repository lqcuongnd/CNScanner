package com.lqcuongnd.cnscanner.Models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.lqcuongnd.cnscanner.UserActivities.ReportDetailActivity;
import com.lqcuongnd.cnscanner.R;

public class ThongBaoBaoCao extends AppCompatActivity {

    private Activity activity1;
    private Activity activity2;

    private String label;
    private String bigText;

    private BaoCao baoCao;

    public ThongBaoBaoCao() {
    }

    public ThongBaoBaoCao(Activity activity1, Activity activity2) {
        this.activity1 = activity1;
        this.activity2 = activity2;
    }

    public void run() {

        createNotificationChannel();

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, ReportDetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("baocao", baoCao);
        intent.putExtras(bundle);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "CNScanner")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Cập nhật báo cáo")
                .setContentText("Haha")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Báo cáo của bạn đã được xử lý, click vào đây để xem chi tiết"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
                /*.addAction(R.mipmap.ic_launcher, getString(R.string.snooze),
                        snoozePendingIntent);*/


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1, mBuilder.build());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBigText() {
        return bigText;
    }

    public void setBigText(String bigText) {
        this.bigText = bigText;
    }

    public BaoCao getBaoCao() {
        return baoCao;
    }

    public void setBaoCao(BaoCao baoCao) {
        this.baoCao = baoCao;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Noti";
            String description = "Haizz";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CNScanner", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
