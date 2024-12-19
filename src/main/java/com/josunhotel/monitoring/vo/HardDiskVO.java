package com.josunhotel.monitoring.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class HardDiskVO {
    private String name;
    private double percentOfUsage;
    private String usage;
    private String totalSpace;

    @Override
    public String toString() {
        return name + ": usage " + String.format("%.2f", percentOfUsage) + "% ("
                + usage + "/" + totalSpace + ")";
    }
}
