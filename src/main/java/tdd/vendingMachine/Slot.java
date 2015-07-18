package tdd.vendingMachine;

import tdd.vendingMachine.exception.EmptySlotRuntimeException;
import tdd.vendingMachine.exception.WrongProductTypeRuntimeException;

import java.util.Stack;

/**
 * Created by Robert on 2015-07-15.
 */
public class Slot
{
    private ProductType productType;
    private Stack<Product> allProducts = new Stack<>();

    public ProductType getProductType()
    {
	return productType;
    }

    public void addProduct(Product product)
    {
	setProductTypeIfNotSet(product);
	throwExceptionIfProductTypeDifferentThanAlreadySet(product);
	allProducts.add(product);
    }

    private void throwExceptionIfProductTypeDifferentThanAlreadySet(Product product)
    {
	if (productType != product.getProductType())
	{
	    throw new WrongProductTypeRuntimeException();
	}
    }

    private void setProductTypeIfNotSet(Product product)
    {
	if (productType == null)
	{
	    productType = product.getProductType();
	}
    }

    protected Product takeProduct()
    {
	if (allProducts.isEmpty())
	{
	    throw new EmptySlotRuntimeException();
	}

	return allProducts.pop();
    }
}
