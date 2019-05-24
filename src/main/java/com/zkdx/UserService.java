 package com.zkdx;

 public interface UserService {
  
     public User getUserById(int id);
     public User getUserByUsername(String name);
     public int modifyUserByUserName(String username, String password, String phone, String address);
     public int clearUsers();
     public int insertNewUser(String username, String password, String phone, String address);
     public int deleteUserByUserName(String name);
}
