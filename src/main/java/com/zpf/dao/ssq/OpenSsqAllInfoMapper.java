package com.zpf.dao.ssq;

import com.zpf.domain.ssq.OpenSsqAllInfo;

public interface OpenSsqAllInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OpenSsqAllInfo record);

    int insertSelective(OpenSsqAllInfo record);

    OpenSsqAllInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OpenSsqAllInfo record);

    int updateByPrimaryKey(OpenSsqAllInfo record);

    int queryExistByExpect(String expect);
}