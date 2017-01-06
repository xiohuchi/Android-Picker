package com.app.ybpick.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.app.ybpick.R;
import com.app.ybpick.utils.DateUtils;
import com.app.ybpick.wheelview.OnYbWheelChangedListener;
import com.app.ybpick.wheelview.StrericYbWheelAdapter;
import com.app.ybpick.wheelview.WheelYbView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * author: Yangbin
 * time  : 2017/1/6 15:09
 * desc  :
 */
public class YbDateSelectorWheelView extends RelativeLayout implements
        OnYbWheelChangedListener {
    private final String flag = "PfpsDateWheelView";
    private WheelYbView wvYear;
    private WheelYbView wvMonth;
    private WheelYbView wvDay;
    private String[] years = new String[100];
    private String[] months = new String[12];
    private String[] tinyDays = new String[28];
    private String[] smallDays = new String[29];
    private String[] normalDays = new String[30];
    private String[] bigDays = new String[31];
    private StrericYbWheelAdapter yearsAdapter;
    private StrericYbWheelAdapter monthsAdapter;
    private StrericYbWheelAdapter tinyDaysAdapter;
    private StrericYbWheelAdapter smallDaysAdapter;
    private StrericYbWheelAdapter bigDaysAdapter;
    private StrericYbWheelAdapter normalDaysAdapter;

    String mYear = "";
    String mMonth = "";
    String mDay = "";

    public YbDateSelectorWheelView(Context context, AttributeSet attrs,
                                   int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    public YbDateSelectorWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public YbDateSelectorWheelView(Context context) {
        super(context);
        initLayout(context);
    }

    private void initLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.dete_time_layout_yb, this,
                true);
        wvYear = (WheelYbView) findViewById(R.id.wv_date_of_year);
        wvMonth = (WheelYbView) findViewById(R.id.wv_date_of_month);
        wvDay = (WheelYbView) findViewById(R.id.wv_date_of_day);
        wvYear.addChangingListener(this);
        wvMonth.addChangingListener(this);
        wvDay.addChangingListener(this);
        setData();
    }

    private void setData() {
        for (int i = 0; i < years.length; i++) {
            years[i] = 1960 + i + " 年";
        }
        for (int i = 0; i < months.length; i++) {
            if (i < 9) {
                months[i] = "0" + (1 + i) + " 月";
            } else {
                months[i] = (1 + i) + " 月";
            }
        }
        for (int i = 0; i < tinyDays.length; i++) {
            if (i < 9) {
                tinyDays[i] = "0" + (1 + i) + " 日";
            } else {
                tinyDays[i] = (1 + i) + " 日";
            }
        }
        for (int i = 0; i < smallDays.length; i++) {
            if (i < 9) {
                smallDays[i] = "0" + (1 + i) + " 日";
            } else {
                smallDays[i] = (1 + i) + " 日";
            }
        }
        for (int i = 0; i < normalDays.length; i++) {
            if (i < 9) {
                normalDays[i] = "0" + (1 + i) + " 日";
            } else {
                normalDays[i] = (1 + i) + " 日";
            }
        }
        for (int i = 0; i < bigDays.length; i++) {
            if (i < 9) {
                bigDays[i] = "0" + (1 + i) + " 日";
            } else {
                bigDays[i] = (1 + i) + " 日";
            }
        }
        yearsAdapter = new StrericYbWheelAdapter(years);
        monthsAdapter = new StrericYbWheelAdapter(months);
        tinyDaysAdapter = new StrericYbWheelAdapter(tinyDays);
        smallDaysAdapter = new StrericYbWheelAdapter(smallDays);
        normalDaysAdapter = new StrericYbWheelAdapter(normalDays);
        bigDaysAdapter = new StrericYbWheelAdapter(bigDays);
        wvYear.setAdapter(yearsAdapter);
        wvYear.setCurrentItem(getTodayYear());
        wvYear.setCyclic(true);
        wvMonth.setAdapter(monthsAdapter);
        wvMonth.setCurrentItem(getTodayMonth());
        wvMonth.setCyclic(true);
        if (isBigMonth(getTodayMonth() + 1)) {
            wvDay.setAdapter(bigDaysAdapter);
        } else if (getTodayMonth() == 1
                && isLeapYear(wvYear.getCurrentItemValue().subSequence(0, 4)
                .toString().trim())) {
            wvDay.setAdapter(smallDaysAdapter);
        } else if (getTodayMonth() == 1) {
            wvDay.setAdapter(tinyDaysAdapter);
        } else {
            wvDay.setAdapter(normalDaysAdapter);
        }
        wvDay.setCurrentItem(getTodayDay());
        wvDay.setCyclic(true);
    }

    /**
     * 获取当前日期的天数的日子
     *
     * @return
     */
    private int getTodayDay() {
        // 2015年12月01日
        int position = 0;
        String today = getToday();
        String day = today.substring(8, 10);
        day = day + " 日";
        for (int i = 0; i < bigDays.length; i++) {
            if (day.equals(bigDays[i])) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * 获取当前日期的月数的位置
     *
     * @return
     */
    private int getTodayMonth() {
        // 2015年12月01日
        int position = 0;
        String today = getToday();
        String month = today.substring(5, 7);
        month = month + " 月";
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * 获取当天的年份
     *
     * @return
     */
    private int getTodayYear() {
        int position = 0;
        String today = getToday();
        String year = today.substring(0, 4);
        year = year + " 年";
        for (int i = 0; i < years.length; i++) {
            if (year.equals(years[i])) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * 设置当前显示的年份
     *
     * @param year
     */
    public void setCurrentYear(String year) {
        boolean overYear = true;
        year = year + " 年";
        for (int i = 0; i < years.length; i++) {
            if (year.equals(years[i])) {
                wvYear.setCurrentItem(i);
                overYear = false;
                break;
            }
        }
        if (overYear) {
            Log.e(flag, "设置的年份超出了数组的范围");
        }
    }

    /**
     * 设置当前显示的月份
     *
     * @param month
     */
    public void setCurrentMonth(String month) {
        month = month + " 月";
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                wvMonth.setCurrentItem(i);
                break;
            }
        }
    }

    /**
     * 设置当前显示的日期号
     *
     * @param day 14
     */
    public void setCurrentDay(String day) {
        day = day + " 日";
        for (int i = 0; i < smallDays.length; i++) {
            if (day.equals(smallDays[i])) {
                wvDay.setCurrentItem(i);
                break;
            }
        }
    }

    /**
     * 获取选择的日期的值
     *
     * @return
     */
    public String getSelectedDate() {
        return mYear + "-"
                + mMonth + "-"
                + mDay;
    }

    /**
     * 判断是否是闰年
     *
     * @param year
     * @return
     */
    private boolean isLeapYear(String year) {
        int temp = Integer.parseInt(year);
        return temp % 4 == 0 && (temp % 100 != 0 || temp % 400 == 0) ? true
                : false;
    }

    /**
     * 判断是否是大月
     *
     * @param month
     * @return
     */
    private boolean isBigMonth(int month) {
        boolean isBigMonth = false;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                isBigMonth = true;
                break;

            default:
                isBigMonth = false;
                break;
        }
        return isBigMonth;
    }

    int currentMonth = 1;

    @Override
    public void onChanged(WheelYbView wheel, int oldValue, int newValue) {
        String trim = null;
        switch (wheel.getId()) {
            case R.id.wv_date_of_year:
                trim = DateUtils.splitDateString(wvYear.getCurrentItemValue())
                        .trim();
                mYear = trim;
                if (isLeapYear(trim)) {
                    if (currentMonth == 2) {
                        wvDay.setAdapter(smallDaysAdapter);
                    } else if (isBigMonth(currentMonth)) {
                        wvDay.setAdapter(bigDaysAdapter);
                    } else {
                        wvDay.setAdapter(normalDaysAdapter);
                    }
                } else if (currentMonth == 2) {
                    wvDay.setAdapter(tinyDaysAdapter);
                } else if (isBigMonth(currentMonth)) {
                    wvDay.setAdapter(bigDaysAdapter);
                } else {
                    wvDay.setAdapter(normalDaysAdapter);
                }
                break;
            case R.id.wv_date_of_month:
                trim = DateUtils.splitDateString(wvMonth.getCurrentItemValue())
                        .trim();
                currentMonth = Integer.parseInt(trim);
                mMonth = trim;
                switch (currentMonth) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        wvDay.setAdapter(bigDaysAdapter);
                        break;
                    case 2:
                        String yearString = DateUtils.splitDateString(
                                wvYear.getCurrentItemValue()).trim();
                        if (isLeapYear(yearString)) {
                            wvDay.setAdapter(smallDaysAdapter);
                        } else {
                            wvDay.setAdapter(tinyDaysAdapter);
                        }
                        break;
                    default:
                        wvDay.setAdapter(normalDaysAdapter);
                        break;
                }
                wvDay.setCurrentItem(0);
                break;
            case R.id.wv_date_of_day:
                mDay = DateUtils.splitDateString(wvDay.getCurrentItemValue())
                        .trim();
                break;
        }
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    private String getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

}
