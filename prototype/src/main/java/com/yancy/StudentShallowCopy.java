package com.yancy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


/**
 * 原型：学生
 * 使用浅拷贝
 * @author yancy0109
 */
@Data
@ToString
@AllArgsConstructor
public class StudentShallowCopy extends ProtoType<StudentShallowCopy>{
    private Pen pen;
}
