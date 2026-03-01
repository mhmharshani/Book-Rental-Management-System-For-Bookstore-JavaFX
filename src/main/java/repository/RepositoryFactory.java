package repository;

import repository.custom.impl.*;
import util.RepositoryType;

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
            case AUTHOR: return (T) new AuthorRepositoryImpl();
            case RENTNRETURNDETAILS: return (T) new RentNReturnDetailsRepositoryImpl();
            case PAYMENT: return (T) new PaymentRepositoryImpl();
            case USER: return (T) new UserLoginRepositoryImpl();
        }
        return null;
    }
}
