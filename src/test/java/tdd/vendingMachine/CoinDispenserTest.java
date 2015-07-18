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
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setPrice(new Price(300));

	dispenser.insertCoin(Coin.CENT_20);
	dispenser.insertCoin(Coin.CENT_50);

	dispenser.returnCoins();

	List<Coin> coins = dispenser.takeChange();
	Price valueOfCoins = CoinHelper.calculateValueOfCoins(coins);

	assertThat(valueOfCoins).isEqualTo(new Price(70));
    }

    @Test(expected = PriceNotSetRuntimeException.class)
    public void shouldThrowExceptionAfterInsertingCoinWhenPriceIsNotSet()
    {
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.insertCoin(Coin.CENT_20);
    }

    @Test
    public void shouldReturnChange()
    {
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setPrice(new Price(250));
	dispenser.insertCoin(Coin.PLN_2);
	dispenser.insertCoin(Coin.PLN_2);

	List<Coin> coins = dispenser.takeChange();
	Price valueOfCoins = CoinHelper.calculateValueOfCoins(coins);

	assertThat(valueOfCoins).isEqualTo(new Price(150));
    }

    @Test
    public void shouldNotifyListenerWhenChangeIsPrepared()
    {
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setInsertingCoinsListener(insertingCoinsListener);
	dispenser.setPrice(new Price(250));
	dispenser.insertCoin(Coin.PLN_2);
	dispenser.insertCoin(Coin.PLN_2);

	verify(insertingCoinsListener).prepareProduct();
    }

    @Test
    public void shouldNotifyListenerWhenMoreCoinsAreRequired()
    {
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setInsertingCoinsListener(insertingCoinsListener);
	dispenser.setPrice(new Price(250));
	dispenser.insertCoin(Coin.PLN_2);

	verify(insertingCoinsListener).insertMoreCoins(new Price(50));
    }

    @Test
    public void shouldNotifyListenerWhenChangeIsNotAvailable()
    {
	CoinDispenser dispenser = new CoinDispenser();
	dispenser.setInsertingCoinsListener(insertingCoinsListener);
	dispenser.setPrice(new Price(110));
	dispenser.insertCoin(Coin.PLN_2);

	verify(insertingCoinsListener).changeNotAvailable();
    }
}
