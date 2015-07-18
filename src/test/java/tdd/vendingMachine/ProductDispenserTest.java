package tdd.vendingMachine;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Robert on 2015-07-18.
 */
public class ProductDispenserTest
{
    @Test
    public void shouldAllowToTakeOnceProduct()
    {
	//given
	ProductDispenser dispenser = new ProductDispenser();

	//when
	Product product = new Product(ProductType.CHOCOLATE_BAR);
	dispenser.setProduct(product);

	Product dispenserProduct = dispenser.getProduct();

	//then
	assertThat(dispenserProduct).isEqualTo(product);

    }

    @Test
    public void shouldNotAllowToTakeTwiceTheSameProduct()
    {
	//given
	ProductDispenser dispenser = new ProductDispenser();

	//when
	Product product = new Product(ProductType.CHOCOLATE_BAR);
	dispenser.setProduct(product);

	dispenser.getProduct();
	Product dispenserProduct = dispenser.getProduct();

	//then
	assertThat(dispenserProduct).isNull();
    }

}
