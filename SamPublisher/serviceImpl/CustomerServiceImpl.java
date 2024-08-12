package com.publisher.SamPublisher.serviceImpl;

import com.mongodb.MongoException;
import com.publisher.SamPublisher.dto.Purchase;
import com.publisher.SamPublisher.exception.UserException;
import com.publisher.SamPublisher.model.CustomerData;
import com.publisher.SamPublisher.model.ProductData;
import com.publisher.SamPublisher.model.UserData;
import com.publisher.SamPublisher.repo.CustomerDataRepository;
import com.publisher.SamPublisher.repo.ProductDataRepo;
import com.publisher.SamPublisher.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    ProductDataRepo productDataRepo;

    @Autowired
    CustomerDataRepository customerDataRepository;

    public boolean addCustomerData(UserData userData) throws UserException {

        CustomerData customerData=new CustomerData();
        boolean success=false;

        try {
            setCustomerData(customerData, userData);
            ArrayList<Purchase> userPurchase = userData.getPurchases();
            for (Purchase purchase : userPurchase) {
                ProductData product = productDataRepo.findById(purchase.getProductId()).get();
                setProductData(purchase, product);
            }
            customerData.setPurchases(userPurchase);

                customerDataRepository.save(customerData);
                log.info("Customer Data Successfully Added ");
                success=true;
            }catch (MongoException e) {
                throw new UserException("Database connection error or other MongoDB issues.");}
            catch (Exception e) {
                throw new UserException("Failed to save customer data.");
            }
        return success;
    }

    private static void setProductData(Purchase purchase, ProductData product) {
        purchase.setName(product.getName());
        purchase.setDescription(product.getDescription());
        purchase.setPrice(product.getPrice());
        purchase.setCategory(product.getCategory());
        purchase.setManufacturer(product.getManufacturer());
    }

    private static void setCustomerData(CustomerData customerData, UserData userData) {
        customerData.set_id(userData.get_id());
        customerData.setUserId(userData.get_id());
        customerData.setName(userData.getName());
        customerData.setEmail(userData.getEmail());
        customerData.setCountry(userData.getCountry());
        customerData.setParentId(userData.getParentId());
        //Address
        customerData.setAddress(userData.getAddress());
        //user
        customerData.setPhone(userData.getPhone());
        customerData.setRegisteredAt(userData.getRegisteredAt());
    }
}
