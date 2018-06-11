package com.zpf.task.Lottery;

import com.zpf.constants.CommonConstants;
import com.zpf.dao.common.UserSubscriptionOpenInfoMapper;
import com.zpf.dao.ssq.OpenSsqAllInfoMapper;
import com.zpf.dao.ssq.OpenSsqInfoMapper;
import com.zpf.domain.Lottery.LotteryDataDomain;
import com.zpf.domain.Lottery.LotteryDomain;
import com.zpf.domain.common.MailParamDomain;
import com.zpf.domain.common.UserSubscriptionOpenInfo;
import com.zpf.domain.ssq.OpenSsqAllInfo;
import com.zpf.domain.ssq.OpenSsqInfo;
import com.zpf.service.httpService.IHttpService;
import com.zpf.service.mail.ISendMailService;
import com.zpf.utils.JsonUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 同步彩票开奖信息
 */
@Service
public class GetLotteryInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetLotteryInfo.class);

    @Autowired
    IHttpService httpService;

    @Autowired
    ISendMailService sendMailService;

    @Autowired
    OpenSsqAllInfoMapper openSsqAllInfoMapper;

    @Autowired
    OpenSsqInfoMapper openSsqInfoMapper;

    @Autowired
    UserSubscriptionOpenInfoMapper userSubscriptionOpenInfoMapper;

    private String managerMail;


    public void task() {

        LOGGER.info("任务开始执行......");

        // 双色球开奖信息
        String url = "http://f.apiplus.net/ssq-1.json";
        String json = httpService.sendGet(url, "");
        if (StringUtils.isBlank(json)) {
            LOGGER.error("获取开奖信息返回值为空！");
            return;
        } else {
            LOGGER.info("获取开奖信息为：{}", json);
        }

        LotteryDomain lotterySsqDomain = JsonUtil.fromJsonToJava(json, LotteryDomain.class);
        // 将信息写入开奖信息表
        List<String> saveExpectList = new ArrayList<>();
        try {
            saveExpectList = insertSsqInfoToDb(lotterySsqDomain);
        } catch (Exception e) {
            LOGGER.error("更新双色球开奖信息错误", e);
        }
        // TODO
        if (!CollectionUtils.isEmpty(saveExpectList)) {
            // 将本次新增的开奖信息发给订阅用户
            List<OpenSsqInfo> openSsqInfoList = openSsqInfoMapper.selectByExpect(saveExpectList);

            // 向管理员发送开奖信息
            MailParamDomain mail = new MailParamDomain();
            mail.setSubject("开奖保存的信息为：");
            String info = "";
            for (OpenSsqInfo s : openSsqInfoList) {
                info = info + s.toString() + "<br/>";
            }
            mail.setContent(info);

            // 向订阅了信息的用户发送信息
            List<UserSubscriptionOpenInfo> list = userSubscriptionOpenInfoMapper.selectListByType("ssq");
            if (!CollectionUtils.isEmpty(list)) {
                list.forEach(e -> {
                    String email = e.getEmail();
                    mail.setTo(email);

                });
            }

            // 向管理员发送
            mail.setTo(this.managerMail);
            sendMailService.sendMail(mail);
        }

        // 向管理员发送开奖信息
        MailParamDomain mail = new MailParamDomain();
        mail.setSubject("获取彩票开奖信息");
        mail.setContent(json);
        mail.setTo(this.managerMail);
        sendMailService.sendMail(mail);
    }


    private List<String> insertSsqInfoToDb(LotteryDomain lotterySsqDomain)
        throws InvocationTargetException, IllegalAccessException {

        List<String> result = new ArrayList<>();

        List<LotteryDataDomain> ssqList = lotterySsqDomain.getData();
        for (LotteryDataDomain domain : ssqList) {
            String expect = domain.getExpect();
            // 判断当前开奖信息是否存在
            int existCount = openSsqInfoMapper.queryExistByExpect(expect);
            if (existCount <= 0) { // 数据不存在，新增
                OpenSsqInfo openSsqInfo = transOpenSsqInfo(domain.getOpencode());
                openSsqInfo.setExpect(domain.getExpect());
                openSsqInfo.setCreated(new Date());
                openSsqInfo.setModified(new Date());
                openSsqInfo.setOpenTime(domain.getOpentime().split(" ")[0]);
                openSsqInfo.setOperator("sys");
                openSsqInfo.setYn((byte) CommonConstants.YN_YES);
                openSsqInfoMapper.insertSelective(openSsqInfo);

                result.add(expect);
            }
            existCount = openSsqAllInfoMapper.queryExistByExpect(expect);
            if (existCount <= 0) { // 数据不存在，新增
                OpenSsqAllInfo openSsqAllInfo = transOpenSsqAllInfo(domain.getOpencode());
                openSsqAllInfo.setExpect(domain.getExpect());
                openSsqAllInfo.setCreated(new Date());
                openSsqAllInfo.setModified(new Date());
                openSsqAllInfo.setOpenTime(domain.getOpentime().split(" ")[0]);
                openSsqAllInfo.setOperator("sys");
                openSsqAllInfo.setYn(CommonConstants.YN_YES);
                openSsqAllInfoMapper.insertSelective(openSsqAllInfo);

                if (!result.contains(expect)) {
                    result.add(expect);
                }
            }

        }

        return result;
    }

    OpenSsqAllInfo transOpenSsqAllInfo(String opencode) throws InvocationTargetException, IllegalAccessException {
        OpenSsqAllInfo result = new OpenSsqAllInfo();
        String redAll = opencode.split("\\+")[0];
        String blueAll = opencode.split("\\+")[1];
        for (String red : redAll.split(",")) {
            int redInt = Integer.parseInt(red);
            BeanUtils.setProperty(result, "red" + redInt, Byte.parseByte("1"));
        }
        int blueInt = Integer.parseInt(blueAll);
        BeanUtils.setProperty(result, "blue" + blueInt, Byte.parseByte("1"));

        return result;
    }

    OpenSsqInfo transOpenSsqInfo(String opencode) {
        OpenSsqInfo result = new OpenSsqInfo();
        String redAll = opencode.split("\\+")[0];
        String blueAll = opencode.split("\\+")[1];
        String[] red = redAll.split(",");

        result.setRed1(Byte.parseByte(String.valueOf(Integer.parseInt(red[0]))));
        result.setRed2(Byte.parseByte(String.valueOf(Integer.parseInt(red[1]))));
        result.setRed3(Byte.parseByte(String.valueOf(Integer.parseInt(red[2]))));
        result.setRed4(Byte.parseByte(String.valueOf(Integer.parseInt(red[3]))));
        result.setRed5(Byte.parseByte(String.valueOf(Integer.parseInt(red[4]))));
        result.setRed6(Byte.parseByte(String.valueOf(Integer.parseInt(red[5]))));

        result.setBlue(Byte.parseByte(String.valueOf(Integer.parseInt(blueAll))));

        return result;

    }

    public void setManagerMail(String managerMail) {
        this.managerMail = managerMail;
    }


}
