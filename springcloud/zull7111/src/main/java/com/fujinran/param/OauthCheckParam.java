package com.fujinran.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by FuJinRan on 2019/12/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OauthCheckParam implements Serializable {
    private String token ;
    private String rquestURI;
}