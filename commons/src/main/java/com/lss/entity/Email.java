package com.lss.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Email
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/3 19:23
 */
@Data
@AllArgsConstructor
public class Email implements Serializable {

    private static final long serialVersionUID = -6830876966260999789L;
    public String from;
    private String to;
    private String title;
    private String content;


}
