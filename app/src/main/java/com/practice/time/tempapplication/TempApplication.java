package com.practice.time.tempapplication;

import android.support.multidex.MultiDexApplication;

import com.apkfuns.log2file.LogFileEngineFactory;
import com.apkfuns.logutils.LogUtils;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication
 * @类名: TempApplication
 * @日期: 2017/8/16
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class TempApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("MyAppName")
                .configShowBorders(false);
 //               .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");

        //支持写入日志到文件
        LogUtils.getLog2FileConfig().configLog2FileEnable(true)
                // targetSdkVersion >= 23 需要确保有写sdcard权限
                .configLog2FilePath("/sdcard/项目文件夹/logs/")
                .configLog2FileNameFormat("%d{yyyyMMdd}.txt")
                .configLogFileEngine(new LogFileEngineFactory());
    }
}
