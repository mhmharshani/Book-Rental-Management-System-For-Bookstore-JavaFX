package repository;

import repository.custom.impl.BookRepositoryImpl;
import repository.custom.impl.RentNReturnRepositoryImpl;
import util.RepositoryType;

import repository.custom.impl.CustomerRepositoryImpl;

public class RepositoryFactory {
    private static RepositoryFactory instance;

    private RepositoryFactory() {}

    public static RepositoryFactory getInstance(){
        return instance==null?instance=new RepositoryFactory():instance;
    }

    public <T extends SuperRepository>T getRepositoryType(RepositoryType repositoryType){
        switch(repositoryType){
            case CUSTOMER: return (T) new CustomerRepositoryImpl();
            case BOOK: return (T) new BookRepositoryImpl();
            case RENTNRETURN: return (T) new RentNReturnRepositoryImpl();
        }
        return null;
    }
}
