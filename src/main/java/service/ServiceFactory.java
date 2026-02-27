package service;

import service.custom.impl.AuthorServiceImpl;
import service.custom.impl.BookServiceImpl;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.RentNReturnServiceImpl;
import util.ServiceType;

//Singleton factory
public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return instance == null? instance = new ServiceFactory():instance;
    }

    //Bounded Generics
    public <T extends SuperService>T getServiceType(ServiceType serviceType){
        switch (serviceType){
            //Type Casting (Cast to T type)
            case CUSTOMER : return (T) new CustomerServiceImpl();
            case BOOK : return (T) new BookServiceImpl();
            case RENTNRETURN : return (T) new RentNReturnServiceImpl();
            case AUTHOR: return (T) new AuthorServiceImpl();
        }
        return null;
    }
}
