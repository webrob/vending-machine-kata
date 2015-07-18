package tdd.vendingMachine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tdd.vendingMachine.util.CoinHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest
{
    @Mock private PriceList priceList;

    private VendingMachine vendingMachine;

    @Before
    public void setUp() throws Exception
    {
	//given
	Storage storage = new Storage();
	Slot slotWithCola = new Slot();
	slotWithCola.addProduct(new Product(ProductType.COLA_CAN));
	storage.addSlotWithNumber(slotWithCola, 1);

	Slot slotWithWater = new Slot();
	slotWithWater.addProduct(new Product(ProductType.MINERAL_WATER_BOTTLE));
	storage.addSlotWithNumber(slotWithWater, 2);

	when(priceList.getPriceForProductType(ProductType.COLA_CAN)).thenReturn(new Price(250));
	when(priceList.getPriceForProductType(ProductType.MINERAL_WATER_BOTTLE)).thenReturn(new Price(110));

	vendingMachine = new VendingMachine(priceList, storage);
    }

    @Test
    public void shouldDisplayInitText()
    {
	//when
	String displayedText = vendingMachine.getDisplayedText();

	//then
	assertThat(displayedText).isEqualTo(Display.INIT_TEXT);
    }

    @Test
    public void shouldDisplayProductPriceAfterChoosingSlot()
    {
	//when
	vendingMachine.chooseSlot(1);

	//then
	String displayedText = vendingMachine.getDisplayedText();
	assertThat(displayedText).isEqualTo("2,5 zł");
    }

    @Test
    public void shouldDisplayHowMuchStillToPutAfterInsertingOneSmallCoin()
    {
	//when
	vendingMachine.chooseSlot(1);
	vendingMachine.insertCoin(Coin.CENT_20);

	String displayedText = vendingMachine.getDisplayedText();

	//then
	assertThat(displayedText).isEqualTo("2,3 zł");
    }

    @Test
    public void shouldDisplayHowMuchStillToPutAfterInsertingTwoSmallCoins()
    {
	//when
	vendingMachine.chooseSlot(1);
	vendingMachine.insertCoin(Coin.CENT_20);
	vendingMachine.insertCoin(Coin.CENT_20);

	String displayedText = vendingMachine.getDisplayedText();

	//then
	assertThat(displayedText).isEqualTo("2,1 zł");
    }

    @Test
    public void shouldReturnProductAfterInsertingExpectedAmount()
    {
	//when
	vendingMachine.chooseSlot(1);
	vendingMachine.insertCoin(Coin.PLN_2);
	vendingMachine.insertCoin(Coin.CENT_50);

	Product product = vendingMachine.takeProduct();

	//then
	assertThat(product.getProductType()).isEqualTo(ProductType.COLA_CAN);
    }

    @Test
    public void shouldReturnProductAndChangeAfterInsertingMoreThanExpectedAmount()
    {
	//when
	vendingMachine.chooseSlot(1);
	vendingMachine.insertCoin(Coin.PLN_2);
	vendingMachine.insertCoin(Coin.PLN_2);

	Product product = vendingMachine.takeProduct();
	List<Coin> coins = vendingMachine.takeChange();
	Price valueOfCoins = CoinHelper.calculateValueOfCoins(coins);

	//then
	assertThat(product.getProductType()).isEqualTo(ProductType.COLA_CAN);
	assertThat(valueOfCoins).isEqualTo(new Price(150));
    }

    @Test
    public void shouldReturnAllAmountAfterInsertingAtLeastOneCoinAndPressingCancel()
    {
	//when
	vendingMachine.chooseSlot(1);
	vendingMachine.insertCoin(Coin.PLN_2);

	vendingMachine.cancel();
	List<Coin> coins = vendingMachine.takeChange();
	Price valueOfCoins = CoinHelper.calculateValueOfCoins(coins);

	//then
	assertThat(valueOfCoins).isEqualTo(new Price(200));
    }

    @Test
    public void shouldNotTakeProductDueToChangeNotAvailable()
    {
	//when
	vendingMachine.chooseSlot(2);
	vendingMachine.insertCoin(Coin.PLN_2);

	String displayedText = vendingMachine.getDisplayedText();

	//then
	assertThat(displayedText).isEqualTo(Display.CHANGE_NOT_AVAILABLE_TEXT);
    }

    @Test
    public void shouldReturnCorrectChangeWithSecondTransaction()
    {
	//when
	vendingMachine.chooseSlot(1);
	vendingMachine.insertCoin(Coin.PLN_2);
	vendingMachine.insertCoin(Coin.CENT_20);
	vendingMachine.insertCoin(Coin.CENT_20);
	vendingMachine.insertCoin(Coin.CENT_20);

	vendingMachine.takeChange();

	vendingMachine.chooseSlot(2);
	vendingMachine.insertCoin(Coin.PLN_2);

	List<Coin> change = vendingMachine.takeChange();
	Price valueOfChange = CoinHelper.calculateValueOfCoins(change);

	//then
	assertThat(valueOfChange).isEqualTo(new Price(90));
    }

}
