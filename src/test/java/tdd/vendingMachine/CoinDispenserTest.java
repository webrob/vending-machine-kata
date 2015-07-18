package tdd.vendingMachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tdd.vendingMachine.exception.PriceNotSetRuntimeException;
import tdd.vendingMachine.listener.InsertingCoinsListener;
import tdd.vendingMachine.util.CoinHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by Robert on 2015-07-18.
 */

@RunWith(MockitoJUnitRunner.class)
public class CoinDispenserTest
{
    @Mock private InsertingCoinsListener insertingCoinsListener;

    @Test
    public void shouldReturnCoinsAfterCanceling()
    {
	//given
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setPrice(new Price(300));
	dispenser.insertCoin(Coin.CENT_20);
	dispenser.insertCoin(Coin.CENT_50);
	dispenser.returnCoins();

	//when
	List<Coin> coins = dispenser.takeChange();
	Price valueOfCoins = CoinHelper.calculateValueOfCoins(coins);

	//then
	assertThat(valueOfCoins).isEqualTo(new Price(70));
    }

    @Test(expected = PriceNotSetRuntimeException.class)
    public void shouldThrowExceptionAfterInsertingCoinWhenPriceIsNotSet()
    {
	//given
	CoinDispenser dispenser = new CoinDispenser();

	//when
	dispenser.insertCoin(Coin.CENT_20);
    }

    @Test
    public void shouldReturnChange()
    {
	//given
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setPrice(new Price(250));
	dispenser.insertCoin(Coin.PLN_2);
	dispenser.insertCoin(Coin.PLN_2);

	//when
	List<Coin> coins = dispenser.takeChange();
	Price valueOfCoins = CoinHelper.calculateValueOfCoins(coins);

	//then
	assertThat(valueOfCoins).isEqualTo(new Price(150));
    }

    @Test
    public void shouldNotifyListenerWhenChangeIsPrepared()
    {
	//given
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setInsertingCoinsListener(insertingCoinsListener);
	dispenser.setPrice(new Price(250));

	//when
	dispenser.insertCoin(Coin.PLN_2);
	dispenser.insertCoin(Coin.PLN_2);

	//then
	verify(insertingCoinsListener).prepareProduct();
    }

    @Test
    public void shouldNotifyListenerWhenMoreCoinsAreRequired()
    {
	//given
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setInsertingCoinsListener(insertingCoinsListener);
	dispenser.setPrice(new Price(250));

	//when
	dispenser.insertCoin(Coin.PLN_2);

	//then
	verify(insertingCoinsListener).insertMoreCoins(new Price(50));
    }

    @Test
    public void shouldNotifyListenerWhenChangeIsNotAvailable()
    {
	//given
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setInsertingCoinsListener(insertingCoinsListener);
	dispenser.setPrice(new Price(110));

	//when
	dispenser.insertCoin(Coin.PLN_2);

	//then
	verify(insertingCoinsListener).changeNotAvailable();
    }
}
