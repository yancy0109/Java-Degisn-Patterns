package com.yancy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 原型：学生
 * 深拷贝
 * @author yancy0109
 */
@Data
@ToString
@AllArgsConstructor
public class StudentDeepCopy extends ProtoTypeForDeepClone<StudentDeepCopy> implements Serializable {
    private Pen pen;
}
