package com.example.vorappServer;

import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import com.example.vorappServer.model.User;
import com.example.vorappServer.repo.ClientRepo;
import com.example.vorappServer.repo.DimRepo;
import com.example.vorappServer.repo.OrdersRepo;
import com.example.vorappServer.repo.UserRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł on 2018-04-20.
 */

@Component
public class DataLoader implements ApplicationRunner{

    @Autowired
    private DimRepo dimRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    public void run(ApplicationArguments args){

        List<User> adminUser = new ArrayList<>();
        String pass = DigestUtils.sha1Hex("password");
        User usr = new User("Admin", pass, true);
        List<Dimiensions> dimsList = new ArrayList<>();
        Dimiensions dim;
        Client clientObject;
        Orders orderObject;

        ResponseEntity<List<User>> responseUsr = new ResponseEntity<List<User>>(userRepo.findByLogin("Admin"), HttpStatus.OK);

        ResponseEntity<List<Dimiensions>> responseDim = new ResponseEntity<List<Dimiensions>>(dimRepo.findAll(), HttpStatus.OK);

        try{
            adminUser = responseUsr.getBody();
        }catch (Exception e){

            e.printStackTrace();
        }

        if(adminUser.size() != 1){
            for(User u : adminUser){
                userRepo.delete(u);
            }
                userRepo.save(usr);
        }

        try{
            dimsList = responseDim.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }


        if(dimsList.size() == 0){
            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0),BigDecimal.valueOf(1.0), BigDecimal.valueOf(0.035));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0),BigDecimal.valueOf(1.5), BigDecimal.valueOf(0.050));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.085));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0),BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.100));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.120));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.145));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.170));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(30.0),BigDecimal.valueOf(30.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.220));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(1.0), BigDecimal.valueOf(0.044));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(1.5), BigDecimal.valueOf(0.072));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.100));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.116));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.140));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(3.2), BigDecimal.valueOf(0.152));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.163));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.175));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(35.0),BigDecimal.valueOf(35.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.235));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(40.0),BigDecimal.valueOf(40.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.115));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(40.0),BigDecimal.valueOf(40.0),BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.140));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(40.0),BigDecimal.valueOf(40.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.150));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(40.0),BigDecimal.valueOf(40.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.182));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(40.0),BigDecimal.valueOf(40.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.200));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(40.0),BigDecimal.valueOf(40.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.270));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(40.0),BigDecimal.valueOf(40.0),BigDecimal.valueOf(8.0), BigDecimal.valueOf(0.400));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(1.5), BigDecimal.valueOf(0.105));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.130));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.155));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.180));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.208));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.235));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(4.5), BigDecimal.valueOf(0.268));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.300));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(45.0),BigDecimal.valueOf(45.0),BigDecimal.valueOf(6.0), BigDecimal.valueOf(0.335));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(1.5), BigDecimal.valueOf(0.107));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(1.8), BigDecimal.valueOf(0.126));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.130));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.176));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.200));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.247));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.260));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.325));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(6.0), BigDecimal.valueOf(0.390));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(50.0),BigDecimal.valueOf(50.0),BigDecimal.valueOf(8.0), BigDecimal.valueOf(0.520));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(55.0),BigDecimal.valueOf(55.0),BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.185));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(55.0),BigDecimal.valueOf(55.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.220));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(55.0),BigDecimal.valueOf(55.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.290));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.175));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.225));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.240));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0), BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.255));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.325));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(4.5), BigDecimal.valueOf(0.360));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.395));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(6.0), BigDecimal.valueOf(0.470));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(7.0), BigDecimal.valueOf(0.545));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(60.0),BigDecimal.valueOf(60.0),BigDecimal.valueOf(8.0), BigDecimal.valueOf(0.625));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(70.0),BigDecimal.valueOf(70.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.195));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(70.0),BigDecimal.valueOf(70.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.285));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(70.0),BigDecimal.valueOf(70.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.335));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(70.0),BigDecimal.valueOf(70.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.385));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(70.0),BigDecimal.valueOf(70.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.490));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(70.0),BigDecimal.valueOf(70.0),BigDecimal.valueOf(6.0), BigDecimal.valueOf(0.595));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.200));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.335));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.375));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.415));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(4.5), BigDecimal.valueOf(0.468));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.520));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(5.5), BigDecimal.valueOf(0.580));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(6.0), BigDecimal.valueOf(0.610));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(7.0), BigDecimal.valueOf(0.700));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(75.0),BigDecimal.valueOf(75.0),BigDecimal.valueOf(8.0), BigDecimal.valueOf(0.810));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.215));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.340));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.385));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.430));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.540));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(6.0), BigDecimal.valueOf(0.650));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(7.0), BigDecimal.valueOf(0.760));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(80.0),BigDecimal.valueOf(80.0),BigDecimal.valueOf(8.0), BigDecimal.valueOf(0.840));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(90.0),BigDecimal.valueOf(90.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.260));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(90.0),BigDecimal.valueOf(90.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.360));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.310));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.310));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(3.5), BigDecimal.valueOf(0.465));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.530));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.670));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(6.0), BigDecimal.valueOf(0.810));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(7.0), BigDecimal.valueOf(0.940));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(8.0), BigDecimal.valueOf(1.100));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(9.0), BigDecimal.valueOf(1.230));
            dimRepo.save(dim);

            dim = new Dimiensions(BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0),BigDecimal.valueOf(10.0), BigDecimal.valueOf(1.350));
            dimRepo.save(dim);
        }

        try{
            for(int a = 0; a < 10000; a++){
                LocalDate rcvDate = LocalDate.now();
                LocalDate ordDate = LocalDate.now().plusDays(5);
                String clientsFirmName = "klient" + a;
                clientObject = clientRepo.save(new Client(clientsFirmName));
                ordersRepo.save(new Orders(clientObject, BigDecimal.TEN, rcvDate, ordDate, "",
                        4L, 5L));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
