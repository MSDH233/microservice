package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by FuJinRan on 2019/10/3.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Accessors(chain = true)
public class Emp implements Serializable {
    private Integer empId ;
    private Integer empNo ;
    private String  empName ;
    private String  empEmail ;
    private Integer empPhoneNumber ;
}