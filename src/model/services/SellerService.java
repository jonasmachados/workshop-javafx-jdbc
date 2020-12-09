 
package model.services;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

/**
 *
 * @author Jonas create 08/12/2020
 */
public class SellerService {
    
    private SellerDao dao = DaoFactory.createSellerDao();
    
    //Lista todos os objetos Seller
    public List<Seller> findyAll(){
        return dao.findAll();
    }
    
    //Metodo que vai Inserir ou atualizar os dados do Seller
    public void saveOrUpdate(Seller obj){
        if(obj.getId() == null){
            dao.insert(obj);
        }
        else{
            dao.update(obj);
        }
    }
    
    //Metodo para remover um Seller do banco de dados
    public void remove(Seller obj){
        dao.deleteById(obj.getId());
    }
}
    
































