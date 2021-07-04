package com.unsri.ecommerce.infrastructure.mediator;

import org.springframework.stereotype.Component;

@Component
public class MediatorImpl<TResponse, TRequest> implements Mediator<TResponse, TRequest> {

    @Override
    public TResponse send(TRequest request, Handler handler) {
        return (TResponse) handler.handle(request);

//        try {
//
//            ApplicationContext applicationContext = SpringbootBeanHelper.getApplicationContext();
//
//            // todo: find class that extend request
//            Class<?> clazz = request.getClass();
//
//            Class<?> entityType = Class.forName("com.unsri.ecommerce.application.behaviours.inventory.commands.AddInventoryCommand");
//
//            Class<?> handlerType = Class.forName("com.unsri.ecommerce.application.behaviours.inventory.commands.AddInventoryCommandHandler");
//
//            //Reflection setting method parameters.
//            Method method = handlerType.getDeclaredMethod("handle",entityType);
//
//            //Remove the instantiated bean according to the class in the ApplicationContext
//            method.invoke(applicationContext.getBean(handlerType), new Object[] {new AddInventoryCommand()});
//
//            return null;
//        } catch (Exception ex) {
//            return null;
//        }
    }
}
