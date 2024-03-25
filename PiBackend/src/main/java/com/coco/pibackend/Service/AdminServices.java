package com.coco.pibackend.Service;



import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Entity.User;

import java.util.List;

public interface AdminServices {
    List<User> getall();
    void UpdateROle(Long id,String role);
    List<Role> getAllROles();
}
