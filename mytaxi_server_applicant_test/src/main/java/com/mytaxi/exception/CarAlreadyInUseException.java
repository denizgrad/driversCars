package com.mytaxi.exception;

/**
 * 
 * @author deniz.ozen
 *
 */
public class CarAlreadyInUseException extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarAlreadyInUseException()
    {
        super("Car is currently used");
    }

}
