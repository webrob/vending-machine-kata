package tdd.vendingMachine;

/**
 * Created by Robert on 2015-07-18.
 */
public class ProductDispenser
{
    private Product product;

    public Product getProduct()
    {
	Product productToReturn = product;
	product = null;
	return productToReturn;
    }

    public void setProduct(Product product)
    {
	this.product = product;
    }
}
