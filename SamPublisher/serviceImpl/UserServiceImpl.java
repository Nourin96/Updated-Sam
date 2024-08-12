package com.publisher.SamPublisher.serviceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.publisher.SamPublisher.dto.Parent;
import com.publisher.SamPublisher.exception.UserException;
import com.publisher.SamPublisher.model.UserData;
import com.publisher.SamPublisher.repo.UserDataRepo;
import com.publisher.SamPublisher.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserDataRepo userDataRepo;

    public List<UserData> getAllUsers() {
        log.info("--User Details--");
        return userDataRepo.findAll();
    }

    public UserData getUserById(String id) {
        log.info("--Inside Service: Fetching User Details--");
        return userDataRepo.findById(id).orElse(null);
    }

    /**
     * @param userData
     * @return parent
     * method to fetch parent details
     * @throws JsonProcessingException
     * @throws UserException
     */

    public Parent getParentDetails(UserData userData) throws UserException {

        Parent parent;
                   log.info("Id :" + userData.get_id());
                    parent= userData.getParent();
                    if(parent!=null)
                    {
                        return parent;
                    }
                    else
                        throw new UserException("Parent data is null");
    }
}