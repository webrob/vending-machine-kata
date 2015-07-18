package tdd.vendingMachine;

import tdd.vendingMachine.exception.WrongSlotRuntimeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert on 2015-07-15.
 */
public class Storage
{
    private Map<Integer, Slot> slots = new HashMap<>();

    public void addSlotWithNumber(Slot slot, int number)
    {
	slots.put(number, slot);
    }

    public ProductType getProductTypeFromSlot(int slotNumber)
    {
	Slot slot = getSlot(slotNumber);
	return slot.getProductType();
    }

    private Slot getSlot(int slotNumber)
    {
	Slot slot = slots.get(slotNumber);
	checkIfSlotExists(slot);
	return slot;
    }

    private void checkIfSlotExists(Slot slot)
    {
	if (slot == null)
	{
	    throw new WrongSlotRuntimeException();
	}
    }

    public Product takeProductFromSlot(int slotNumber)
    {
	Slot slot = getSlot(slotNumber);
	return slot.takeProduct();
    }
}
