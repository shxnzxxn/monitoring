package com.josunhotel.monitoring.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TableSpacesVO {
    private String serverName; // 서버 이름
    private List<TableSpaceDto> tableSpaceDtos;
}
