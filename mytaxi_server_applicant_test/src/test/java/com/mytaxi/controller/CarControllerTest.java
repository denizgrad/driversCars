package com.mytaxi.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mytaxi.ATest;
import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.service.CarService;
/**
 * @author deniz.ozen
 *
 */
public class CarControllerTest extends ATest{

	private MockMvc mvc;
	private static String context = "/v1/cars";

	@Mock
	private CarService carService;

	@InjectMocks
	private CarController carController;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(CarController.class);
	}

	@Before
	public void init() {
		mvc = MockMvcBuilders.standaloneSetup(carController).dispatchOptions(true).build();
	}

	@Test
	public void testGetCar() throws Exception {
		when(carService.findCarById(1L)).thenReturn(getCarDO());
		mvc.perform(get(context+ "/{carId}", 1))
			.andExpect(jsonPath("$.licensePlate", is("PLAKA")));
		
		verify(carService, times(1)).findCarById(1L);
		verifyNoMoreInteractions(carService);
	}

	@Test
	public void getAllCars() throws Exception {
		when(carService.findAllCars()).thenReturn(Arrays.asList(getCarDO()));
		mvc.perform(get(context))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].licensePlate", is("PLAKA")))
		.andReturn();
		
		verify(carService, times(1)).findAllCars();
		verifyNoMoreInteractions(carService);
	}

	@Test
	public void createCar() throws Exception {
		CarDTO carDto = getCarDTO();
		CarDO car = getCarDO();
		when(carService.create(car)).thenReturn(car);
		 mvc.perform(post(context)
	                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	                .content(convertObjectToJsonBytes(carDto))
	        )
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.licensePlate", is("PLAKA")));
        
        verify(carService, times(1)).create(car);
		verifyNoMoreInteractions(carService);
	}

	@Test
	public void updateCar() throws Exception {
		CarDTO carDto = getCarDTO();
		CarDO car = CarMapper.convert(carDto);
		doNothing().when(carService).update(car);
        mvc.perform(put(context)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(carDto))
        )
        .andExpect(status().isOk());
        
        verify(carService, times(1)).update(car);
		verifyNoMoreInteractions(carService);
	}

	@Test
	public void deleteCar() throws Exception {
		doNothing().when(carService).delete(any(Long.class));
		mvc.perform(delete(context + "/{carId}", 1L))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		verify(carService, times(1)).delete(any(Long.class));
		verifyNoMoreInteractions(carService);
	}

}
