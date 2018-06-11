package com.zpf.dao.ssq;

import com.zpf.domain.ssq.OpenSsqInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpenSsqInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OpenSsqInfo record);

    int insertSelective(OpenSsqInfo record);

    OpenSsqInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OpenSsqInfo record);

    int updateByPrimaryKey(OpenSsqInfo record);

    int queryExistByExpect(String expect);

    List<OpenSsqInfo> selectByExpect(@Param("list") List<String> list);
}