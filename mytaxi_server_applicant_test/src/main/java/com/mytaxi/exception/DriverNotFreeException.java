package com.mytaxi.exception;

/**
 * 
 * @author deniz.ozen
 *
 */
public class DriverNotFreeException extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DriverNotFreeException()
    {
        super("Driver has currently a car or is offline");
    }

}
