package repository;

import entity.ProductsEntity;

public interface CrudDao <T> extends Superdao  {
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
}
