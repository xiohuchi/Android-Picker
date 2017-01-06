package com.app.ybpick.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.app.ybpick.R;
import com.app.ybpick.widget.YbDateSelectorWheelView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Yangbin
 * time  : 2016/12/9 17:55
 * desc  : 选择体重
 */

public class DialogChooseDate {
    Context context;
    Dialogcallback dialogcallback;
    Dialog dialog;
    int mSex;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.pdwv_date_selector_wheelView)
    YbDateSelectorWheelView pdwvDateSelectorWheelView;


    public DialogChooseDate(Context con, int sex, Dialogcallback callback) {
        this.context = con;
        this.dialogcallback = callback;
        this.mSex = sex;
        dialog = new AlertDialog.Builder(con).create();
        dialog.show();
        dialog.getWindow().setContentView(R.layout.dialog_pickdate);
        ButterKnife.bind(this, dialog);
        tvTitle.setText("出生日期");
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        Calendar c = Calendar.getInstance();
        pdwvDateSelectorWheelView.setCurrentYear(c.get(Calendar.YEAR) + "");
        pdwvDateSelectorWheelView.setCurrentMonth(c.get(Calendar.MONTH) + "");
        pdwvDateSelectorWheelView.setCurrentDay(c.get(Calendar.DAY_OF_MONTH) + "");
    }

    @OnClick({R.id.tv_cancle, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                dialog.dismiss();
                break;
            case R.id.tv_sure:
                dialogcallback.pickWeightResult(pdwvDateSelectorWheelView.getSelectedDate());
                dismiss();
                break;
        }
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }


    public interface Dialogcallback {
        void pickWeightResult(String sex);
    }

    public void show() {
        dialog.show();
    }

    public void hide() {
        dialog.hide();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
