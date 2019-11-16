package com.lqcuongnd.cnscanner.Firebase;

import com.lqcuongnd.cnscanner.UserActivities.InputActivity;
import com.lqcuongnd.cnscanner.UserActivities.LoginActivity;
import com.lqcuongnd.cnscanner.UserActivities.RegisterActivity;
import com.lqcuongnd.cnscanner.Firebase.Report.AddReportAsync;
import com.lqcuongnd.cnscanner.Firebase.User.LoginAsync;
import com.lqcuongnd.cnscanner.Firebase.User.RegisterAsync;
import com.lqcuongnd.cnscanner.Models.BaoCao;
import com.lqcuongnd.cnscanner.Models.NguoiDung;

public class Firebase {

    public Firebase() {
    }

    //NGƯỜI DÙNG ===================================================================
    public void dangNhap(NguoiDung nguoiDung, LoginActivity activity){
        LoginAsync loginAsync = new LoginAsync(nguoiDung, activity);
        loginAsync.execute();
    }

    public void dangKy(NguoiDung nguoiDung, RegisterActivity activity){
        RegisterAsync registerAsync = new RegisterAsync(nguoiDung, activity);
        registerAsync.execute();
    }

    //BÁO CÁO ======================================================================

    public void taoBaoCao(BaoCao baoCao, InputActivity activity) {
        AddReportAsync addReportAsync = new AddReportAsync(baoCao, activity);
        addReportAsync.execute();
    }


}
