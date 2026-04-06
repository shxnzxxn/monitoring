package com.josunhotel.monitoring.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TableSpaceDto {
    private String tableSpace;
    private double used;
}
