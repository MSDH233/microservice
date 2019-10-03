package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by FuJinRan on 2019/10/3.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Emp {
    private Integer empId ;
    private Integer empNo ;
    private String  empName ;
    private String  empEmail ;
    private Integer empPhoneNumber ;
}