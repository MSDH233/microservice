package com.fujinran.utils.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by FuJinRan on 2019/12/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {
    private String subject ;
    private String message ;
    private Set<String> receivers ;

}