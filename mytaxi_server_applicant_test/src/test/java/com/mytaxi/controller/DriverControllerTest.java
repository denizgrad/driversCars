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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.ATest;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.service.DriverCarService;
import com.mytaxi.service.DriverService;

public class DriverControllerTest extends ATest{
	private static final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    private static String context = "/v1/drivers";
    @Mock
    private DriverService driverService;
    @Mock
    private DriverCarService driverCarService;

    @InjectMocks
    private DriverController driverController;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DriverController.class);
    }


    @Before
    public void init()
    {
        mvc = MockMvcBuilders.standaloneSetup(driverController).dispatchOptions(true).build();
    }


    @Test
    public void testSelectCarByDriver() throws Exception
    {
        DriverDTO driverDTO = getDriverDTO();
        doReturn(driverDTO).when(driverCarService).selectCarByDriver(any(Long.class), any(Long.class));
        mvc
            .perform(post(context + "/select")
                .param("driverId", "1")
                .param("carId", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
    		.andExpect(jsonPath("$.username", is("test")))
            .andReturn();

        verify(driverCarService, times(1)).selectCarByDriver(any(Long.class), any(Long.class));
		verifyNoMoreInteractions(driverCarService);
    }


    @Test
    public void testDeSelectCarByDriver() throws Exception
    {
        doNothing().when(driverCarService).deSelectCarByDriver(any(Long.class), any(Long.class));
        mvc
            .perform(delete(context + "/deselect")
                .param("driverId", "1")
                .param("carId", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        
        verify(driverCarService, times(1)).deSelectCarByDriver(any(Long.class), any(Long.class));
		verifyNoMoreInteractions(driverCarService);
    }


    @Test
    public void testGetDriver() throws Exception
    {
        when(driverService.findDriverById(any(Long.class))).thenReturn(getDriverDO());;
        mvc
            .perform(get(context + "/{driverId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.username", is("test")))
            .andExpect(jsonPath("$.id", is(1)))
            .andReturn();
        
        verify(driverService, times(1)).findDriverById(any(Long.class));
		verifyNoMoreInteractions(driverService);
    }


    @Test
    public void testUpdateLocation() throws Exception
    {
        doNothing().when(driverService).updateLocation(any(Long.class), any(Double.class), any(Double.class));
        mvc
            .perform(put(context + "/{driverId}", 1)
                .param("longitude", "1").param("latitude", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        verify(driverService, times(1)).updateLocation(any(Long.class), any(Double.class), any(Double.class));
		verifyNoMoreInteractions(driverService);
    }


    @Test
    public void testCreateDriver() throws Exception
    {
        when(driverService.create(getDriverDO())).thenReturn(getDriverDO());
        mvc.perform(post(context)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(getDriverDTO()))
        )
	    .andExpect(status().isCreated())
	    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	    .andExpect(jsonPath("$.username", is("test")));
    
	    verify(driverService, times(1)).create(getDriverDO());
		verifyNoMoreInteractions(driverService);

    }


    @Test
    public void testDeleteDriver() throws Exception
    {
        doNothing().when(driverService).delete(any(Long.class));
        mvc
            .perform(delete(context + "/{driverId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        verify(driverService, times(1)).delete(any(Long.class));
		verifyNoMoreInteractions(driverService);
    }


    @Test
    public void testFindByOnlineStatus() throws Exception
    {
        List<DriverDO> driverDO = Arrays.asList(getDriverDO());
        when(driverService.find(any(OnlineStatus.class))).thenReturn(driverDO);
        mvc
            .perform(get(context)
                .param("onlineStatus", OnlineStatus.ONLINE.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].username", is("test")))
            .andReturn();
        
        verify(driverService, times(1)).find(any(OnlineStatus.class));
		verifyNoMoreInteractions(driverService);
    }
    
//    @Test //TODO
//    public void testFindDriverByCarAttributes() throws Exception
//    {
//        List<DriverDTO> driverDTO = Collections.singletonList(getDriverDTO());
//        doReturn(driverDTO).when(driverService).findDriverByCarAttributes(any(CarData.class));
//        Map<String, String> params = new HashMap<>();
//        params.put("seatCount", "3");
//        driverController.findDriverByCarAttributes(params);
//        MvcResult result = mvc
//            .perform(get("/v1/drivers/car")
//                .param("seatCount", "3"))
//            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//        final String responseBody = result.getResponse().getContentAsString();
//        Assert.assertTrue(responseBody.contains("test"));
//    }

}
