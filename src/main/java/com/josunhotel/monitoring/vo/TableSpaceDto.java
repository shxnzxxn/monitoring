package com.josunhotel.monitoring.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableSpaceDto {
    private String tableSpace;
    private double used;
}
