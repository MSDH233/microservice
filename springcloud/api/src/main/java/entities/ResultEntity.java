package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Data
@Builder
@AllArgsConstructor
public class ResultEntity implements Serializable {

    private String status ;

    private Object data ;

    private boolean flag ;

    private String message ;

    private String token ;

    public ResultEntity(String status, Object data) {
        this.status = status;
        this.data = data;
    }
    public ResultEntity(String token ) {
        this.token = token;
    }
    public ResultEntity(){

    }
    public ResultEntity(boolean flag , Object data ){
        this.flag = flag;
        this.data = data;
    }

    public ResultEntity(boolean flag , Object data , String message ){
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public ResultEntity(Object o){
        this.data = o;
    }

    public static ResultEntity success(Object data){
        return new ResultEntity(true , data ,"操作成功");

    }

    public static ResultEntity fault(Object data){
        return new ResultEntity(false ,data ,"操作失败");
    }
}