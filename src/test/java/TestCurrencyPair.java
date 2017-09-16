import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing CurrencyPair class (particularly equals methods )

 */
public class TestCurrencyPair {

    @DataProvider
    public Object[][] dataProvider() {
        Object[][] data = new Object[][] {
                {new CurrencyPair("USD","RUB"), new CurrencyPair("USD","RUB"), true},
                {new CurrencyPair("USD","RUB"), new CurrencyPair("RUB","USD"), false},
                {new CurrencyPair("USD","RUB"), new CurrencyPair("USD","EUR"), false},
        };
        return data;
    }

    @Test(dataProvider = "dataProvider")
    private void test(CurrencyPair currencyPair1, CurrencyPair currencyPair2, boolean answer) {
        Assert.assertEquals(currencyPair1.equals(currencyPair2),answer);
    }
}
