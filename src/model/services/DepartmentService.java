 
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
    
    //Lista todos os objetos Department
    public List<Department> findyAll(){
        return dao.findAll();
    }
    
    //Metodo que vai Inserir ou atualizar os dados do Department
    public void saveOrUpdate(Department obj){
        if(obj.getId() == null){
            dao.insert(obj);
        }
        else{
            dao.update(obj);
        }
    }
    
    //Metodo para remover um Department do banco de dados
    public void remove(Department obj){
        dao.deleteById(obj.getId());
    }
}
    
































