package repository;

import entity.AdminEntity;

public interface CrudDao <T> extends Superdao  {
    boolean save(T t);
}
