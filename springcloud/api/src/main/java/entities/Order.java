package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by FuJinRan on 2019/10/9.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Accessors(chain = true)
public class Order implements Serializable {
    private Integer orderId ;
    private Integer orderNum ;
    private Integer order_emp_id ;
}