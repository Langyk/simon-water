package com.simon.water.handler.handler;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.google.common.base.Throwables;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.dto.model.EmailContentModel;
import com.simon.water.common.enums.ChannelType;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * creat by 郎亚坤
 * 2022/7/16 11:52
 *
 * 邮件发送消息处理
 */

@Component
@Slf4j
public class EmailHandler extends Handler{


    public EmailHandler() {
        channelCode = ChannelType.EMAIL.getCode();
    }


    @Override
    public boolean handler(TaskInfo taskInfo) {
        EmailContentModel emailContentModel = (EmailContentModel) taskInfo.getContentModel();
        MailAccount account = getAccout();
        try {
            MailUtil.send(account,taskInfo.getReceiver(),emailContentModel.getTitle(),
                    emailContentModel.getContent(),true,null);
        }catch (Exception e){
            log.error("EmailHandler#getAccount fail!{},params:{}", Throwables.getStackTraceAsString(e),taskInfo);
            return false;
        }
        return true;
    }


    /**
     * 获取账号信息
     * @return
     */
    private MailAccount getAccout(){
        MailAccount account = new MailAccount();
        try {
            //邮件服务器
            account.setHost("smtp.qq.com").setPort(465);
            account.setUser("1253193643@qq.com").setPass("langyk123456").setAuth(true);
            account.setFrom("1253193643@qq.com");

            // 使用加密工厂，ssl加密
            MailSSLSocketFactory sf =new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            account.setStarttlsEnable(true).setSslEnable(true).setCustomProperty("mail.smtp.ssl.socketFactory",sf);

            account.setTimeout(25000).setConnectionTimeout(25000);
        }catch (Exception e){
            log.error("EmailHandler#getAccount fail!{}", Throwables.getStackTraceAsString(e));
        }
        return account;
    }
}

