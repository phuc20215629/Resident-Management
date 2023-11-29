package application.dao;
import java.util.ArrayList;

public interface DAOInterface<T> {
    public boolean insert(T t);
    
    public boolean update(T t);

    public boolean deleteByID(int id);
 
    public ArrayList<T> selectAll();

    public T selectById(int id);
    
}