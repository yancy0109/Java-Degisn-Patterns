package com.yancy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 原型：笔
 * @author yancy0109
 */
@Data
@ToString
@AllArgsConstructor
public class Pen implements Serializable {

    private String name;
}
