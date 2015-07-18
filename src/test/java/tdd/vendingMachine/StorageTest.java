package tdd.vendingMachine;

import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exception.WrongSlotRuntimeException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Robert on 2015-07-17.
 */
public class StorageTest
{
    private Storage storage;

    @Before
    public void setUp() throws Exception
    {
	//given
	storage = new Storage();
	Slot slotWithCola = new Slot();
	Product product = new Product(ProductType.COLA_CAN);
	slotWithCola.addProduct(product);
	storage.addSlotWithNumber(slotWithCola, 1);
    }

    @Test
    public void shouldReturnColaCanProductType()
    {
	//when
	ProductType productType = storage.getProductTypeFromSlot(1);

	//then
	assertThat(productType).isEqualTo(ProductType.COLA_CAN);
    }

    @Test
    public void shouldReturnColaCanProduct()
    {
	//when
	Product product = storage.takeProductFromSlot(1);
	ProductType productType = product.getProductType();

	//then
	assertThat(productType).isEqualTo(ProductType.COLA_CAN);
    }

    @Test(expected = WrongSlotRuntimeException.class)
    public void shouldThrowExceptionIfSlotDoesNotExist()
    {
	//when
	storage.takeProductFromSlot(2);
    }

}
