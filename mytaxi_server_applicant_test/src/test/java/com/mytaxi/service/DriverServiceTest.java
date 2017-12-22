package com.mytaxi.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mytaxi.ATest;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
/**
 * 
 * @author deniz.ozen
 *
 */
public class DriverServiceTest extends ATest {
	@Mock
	private DriverRepository driverRepository;

	@InjectMocks
	private DefaultDriverService driverService;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(DefaultDriverService.class);
	}

	@Test
	public void testFindDriverById() throws EntityNotFoundException {
		DriverDO driver = getDriverDO();
		when(driverRepository.findOne(any(Long.class))).thenReturn(driver);
		driverService.findDriverById(any(Long.class));
		verify(driverRepository, times(1)).findOne(any(Long.class));
	}

	@Test
	public void testCreate() throws ConstraintsViolationException {
		DriverDO driver = getDriverDO();
		when(driverRepository.save(any(DriverDO.class))).thenReturn(driver);
		driverService.create(any(DriverDO.class));
		verify(driverRepository, times(1)).save(any(DriverDO.class));
	}

	@Test
	public void testDelete() throws EntityNotFoundException {
		DriverDO driver = getDriverDO();
		when(driverRepository.findOne(any(Long.class))).thenReturn(driver);
		driverService.delete(any(Long.class));
		verify(driverRepository, times(1)).findOne(any(Long.class));
	}

	@Test
	public void testUpdateLocation() throws EntityNotFoundException {
		DriverDO driver = getDriverDO();
		when(driverRepository.findOne(any(Long.class))).thenReturn(driver);
		driverService.updateLocation(1L, 0, 0);
		verify(driverRepository, times(1)).findOne(any(Long.class));
	}

	@Test
	public void testFindByOnlineStatus() {
		List<DriverDO> drivers = Arrays.asList(getDriverDO());
		when(driverRepository.findByOnlineStatus(any(OnlineStatus.class))).thenReturn(drivers);
		driverService.find(OnlineStatus.ONLINE);
		verify(driverRepository, times(1)).findByOnlineStatus(any(OnlineStatus.class));
	}
}
