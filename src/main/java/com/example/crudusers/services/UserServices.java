package com.example.crudusers.services;


import com.example.crudusers.models.User2;
import com.example.crudusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServices {

    private UserRepository userRepository;
    @Autowired
    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // данный метод позволяет вернуть всех пользователей
    public List<User2> getAllUser() {
        return userRepository.findAll(); //select * from user_site
    }
    // данный метод позволяет вернуть пользователя по id
    public User2 getUserId(int id){
        Optional<User2> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null); //select * from user_site where id = переменная id
    }
    // данный метод позволяет сохранить пользователя
    @Transactional
    public void save(User2 user){
        userRepository.save(user); // insert into user_site(last_name, first_name, patronymic, age, phone, email) values('Иванов','' и т.д.);
    }
    //данный метод позволяет обновить данные пользователя по id
    @Transactional
    public void update(int id, User2 user){
        user.setId(id);
        userRepository.save(user); // update user_site SET first_name=user.first_name where id = параметр id
    }
    //данный метод позволяет удалить пользователя по id
    @Transactional
    public void delete(int id){
        userRepository.deleteById(id); //delete from user_site where id = переменная id
    }
}
