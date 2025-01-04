package com.rad.services.resistor;

import android.content.Context;

import com.rad.database.resistor.ResistorRepository;
import com.rad.models.Resistor;
import com.rad.models.types.ResistorType;

import java.util.ArrayList;

public class ResistorService
{
	//#region Constructor

	private final ResistorRepository resistorRepository;
	
	public ResistorService(Context context)
	{
		resistorRepository = new ResistorRepository(context);
	}
	
	//#endregion
	
	//#region Methods

	/**
	 * Validates a resistor from the database.
	 * @param id The id of the resistor to validate.
	 * @return The resistor configuration.
	 */
	public Resistor validate(int id)
	{
		return resistorRepository.select(id);
	}

	/**
	 * Inserts a new resistor into the database.
	 * @param resistor The resistor configuration to insert.
	 */
	public void create(Resistor resistor)
	{
		resistorRepository.insert(resistor);
	}

	/**
	 * Selects all resistor from the database.
	 * @param type The resistor type.
	 * @param lastId The last resistor id.
	 * @param count The number of items to retrieve.
	 * @return The resistor configuration.
	 */
	public ArrayList<Resistor> getPaginated(ResistorType type, int lastId, int count)
	{
		return resistorRepository.selectPaginated(type, lastId, count);
	}
	
	public int getCount(ResistorType type)
	{
		if (type == ResistorType.NONE)
			return resistorRepository.selectCount();
		
		return resistorRepository.selectCount(type);
	}
	
	/**
	 * Deletes a resistor from the database.
	 * @param id The id of the resistor to delete.
	 */
	public void delete(int id)
	{
		resistorRepository.delete(id);
	}
	
	//#endregion

}
