package com.vito.tools.utils;

import java.time.LocalTime;

public final class DateUtils {

    /**
     * 判断当前时间是否在今天指定的小时范围内（不支持 startHour >= endHour）
     * @param startHour 开始小时（0-23）
     * @param endHour 结束小时（0-24，24 表示第二天 0 点）
     * @return 如果当前时间在范围内返回 true，否则返回 false（包括参数非法的情况）
     */
    public static boolean isCurrentTimeInTodayRange(int startHour, int endHour) {
        // 检查参数是否合法
        if (startHour < 0 || startHour > 23 || endHour < 0 || endHour > 24) {
            return false; // 小时不在 0-23（startHour）或 0-24（endHour）范围内
        }
        if (startHour >= endHour) {
            return false; // 不允许 startHour >= endHour（如 [22:00, 22:00] 或 [18:00, 10:00]）
        }

        // 获取当前时间
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();

        // 如果 endHour=24，视为第二天 0 点
        if (endHour == 24) {
            return currentHour >= startHour; // 只要 >= startHour 就算在范围内（因为 endHour=24 无上限）
        } else {
            return currentHour >= startHour && currentHour < endHour;
        }
    }
}
