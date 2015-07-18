package tdd.vendingMachine;

import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exception.EmptySlotRuntimeException;
import tdd.vendingMachine.exception.WrongProductTypeRuntimeException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Robert on 2015-07-18.
 */
public class SlotTest
{
    private Slot slot;

    @Before
    public void setUp() throws Exception
    {
	//given
	slot = new Slot();
	slot.addProduct(new Product(ProductType.COLA_CAN));
    }

    @Test
    public void shouldTakeColaCanProductFromSlot()
    {
	//when
	Product product = slot.takeProduct();

	//then
	ProductType productType = product.getProductType();
	assertThat(productType).isEqualTo(ProductType.COLA_CAN);
    }

    @Test(expected = WrongProductTypeRuntimeException.class)
    public void shouldThrowExceptionAfterAddingProductWithDifferentThanColaType()
    {
	//when
	slot.addProduct(new Product(ProductType.MINERAL_WATER_BOTTLE));
    }

    @Test(expected = EmptySlotRuntimeException.class)
    public void shouldThrowExceptionWhenTryingToTakeProductFromEmptyList()
    {
	//when
	slot.takeProduct();
	slot.takeProduct();
    }
}
