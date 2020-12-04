
package model.services;

import java.util.ArrayList;
import java.util.List;
import model.entilies.Department;

/**
 *
 * @author Jonas create 03/12/2020
 */
public class DepartmentService {
    
    //Colecao
    public List<Department> findyAll(){
        List<Department> list = new ArrayList<>();
        list.add(new Department(1, "Books"));
        list.add(new Department(2, "Computer"));
        list.add(new Department(3, "Eletronics"));
        return(list);
    }
}
    

