package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Dimiensions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Paweł on 2018-04-21.
 */
public interface storyOrdersRepoCustom {
    List findByClient(Client client);
    List findByDimension(Dimiensions dimension);



    @Transactional
    void deleteByClient(Client client);

    @Transactional
    void deleteByDimension (Dimiensions dimension);

}
