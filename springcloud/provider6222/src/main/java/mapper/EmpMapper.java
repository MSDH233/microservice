package mapper;

import entities.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by FuJinRan on 2019/10/3.
 */
@Mapper
public interface EmpMapper {
    public Emp get(Integer empId);
    public boolean set(Emp emp);
    public List<Emp> selectAll();
}