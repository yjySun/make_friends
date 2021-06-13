package com.yjy.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RegisterStatistics {

    private Integer Jan;
    private Integer Feb;
    private Integer Mar;
    private Integer Apr;
    private Integer May;
    private Integer Jun;
    private Integer Jul;
    private Integer Aug;
    private Integer Sept;
    private Integer Oct;
    private Integer Nov;
    private Integer Dec;
    @Column(name = "registerCount")
    private Integer registerCount;
    private Double growthRatio;

}
