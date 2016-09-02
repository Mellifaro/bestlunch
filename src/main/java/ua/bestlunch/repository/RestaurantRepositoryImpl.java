package ua.bestlunch.repository;


import org.springframework.stereotype.Repository;
import ua.bestlunch.model.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виктор on 13.08.2016.
 */

//public class RestaurantRepositoryImpl implements RestaurantRepository{
//
//
//    @Override
//    public List<Restaurant> getAll(){
//        List<Restaurant> restaurants = new ArrayList<>();
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/bestlunch", "postgres", "postgres");
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM restaurants");
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String address = rs.getString("address");
//                int popularity = rs.getInt("popularity");
//                restaurants.add(new Restaurant(id, name, address, popularity));
//            }
//            connection.close();
//        }catch (SQLException | ClassNotFoundException e){e.printStackTrace();}
//        return restaurants;
//    }
//}
