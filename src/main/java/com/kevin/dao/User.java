package com.kevin.dao;

import lombok.*;

import java.util.Date;

/**
 * @author kevin
 */
@Getter
@AllArgsConstructor
@ToString
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    private long id;

    private String userName;

    private String orderNo;

    private Date createTime;
}
