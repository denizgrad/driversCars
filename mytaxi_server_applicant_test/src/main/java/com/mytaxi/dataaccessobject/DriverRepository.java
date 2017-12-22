package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    
    @Query("SELECT d FROM CarDO c, DriverDO d " +
            "WHERE c.selectedDriver = d.id " +
            "AND (c.seatCount = ?1)"
     )
     List<DriverDO> findDriverBySeatCount(final int seatCount);

	UserDetails findByUsername(String username);
}
