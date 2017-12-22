package com.mytaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.DriverRepository;

/**
 * @author deniz.ozen
 *
 */
@Service("UserDetailsService")
public class UserServiceImpl implements UserDetailsService
{

    @Autowired
    private DriverRepository driverRepository;


    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return driverRepository.findByUsername(username);
    }
}
