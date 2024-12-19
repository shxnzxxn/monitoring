package com.josunhotel.monitoring.mapper;

import com.josunhotel.monitoring.vo.TableSpaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonitoringMapper {
    List<TableSpaceDto> getTableSpaceUsage();
}
