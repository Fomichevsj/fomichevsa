import java.util.Objects;

/**
 * Class describes existing in local pairs of currency

 */
public class CurrencyPair {
    private String firstCurrency;
    private String secondCurrency;
    private double rate;

    public CurrencyPair(String firstCurrency, String secondCurrency, double rate) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.rate = rate;
    }

    public CurrencyPair(String firstCurrency, String secondCurrency) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.rate = rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!CurrencyPair.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final CurrencyPair other = (CurrencyPair) obj;
        //Assume firstCurrency and SecondCurrency is not null
        if( (this.firstCurrency.equals(other.firstCurrency)) && (this.secondCurrency.equals(other.secondCurrency))) return true;
        else
            return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstCurrency,secondCurrency);
    }

    public String getFirstCurrency() {
        return firstCurrency;
    }

    public String getSecondCurrency() {
        return secondCurrency;
    }

    public double getRate() {
        return rate;
    }
}
