
package model.services;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


/**
 *
 * @author Jonas create 03/12/2020
 */
public class DepartmentService {
    
    private DepartmentDao dao = DaoFactory.createDepartmentDao();
    
    public List<Department> findyAll(){
        return dao.findAll();
    }
}
    
































