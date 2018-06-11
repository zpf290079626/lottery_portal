package com.zpf.dao.sys;


import com.zpf.domain.common.SystemParamDomain;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemParamsMapper {

	List<SystemParamDomain> selectByExample(@Param("systemParam") SystemParamDomain systemParam);
	
	List<SystemParamDomain> selectAllData();

	int insert(@Param("systemParam") SystemParamDomain systemParam);

	int updateByPrimaryKey(@Param("systemParam") SystemParamDomain systemParam);

	int deleteByPrimaryKey(String paramId);
	
	String selectByParamName(@Param("paramName") String paramName);

}