package com.zpf.dao.common;

import com.zpf.domain.common.UserSubscriptionOpenInfo;
import java.util.List;

public interface UserSubscriptionOpenInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSubscriptionOpenInfo record);

    int insertSelective(UserSubscriptionOpenInfo record);

    UserSubscriptionOpenInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSubscriptionOpenInfo record);

    int updateByPrimaryKey(UserSubscriptionOpenInfo record);

    List<UserSubscriptionOpenInfo> selectListByType(String type);
}