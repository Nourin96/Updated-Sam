package com.publisher.SamPublisher.repo;

import com.publisher.SamPublisher.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends MongoRepository<UserData,String> {
    UserData findByParentId(String parentId);

}
