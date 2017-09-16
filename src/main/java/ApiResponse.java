public class ApiResponse {
    private String base;
    private String date;
    private RateObject rates;

    public void print() {
        System.out.println(base + " => " + rates.getName() + " : " + rates.getRate() + "\n");
    }

    /**
     * Return a currencyPair
     * @return currencyPair
     */
    public CurrencyPair getCurrencyPair() {
        CurrencyPair currencyPair = new CurrencyPair(this.base, this.rates.getName(),this.rates.getRate());
        return currencyPair;
    }

    /**
     * Return a rate between currency pair
     * @return rate
     */
    public Double getRate() {
        return this.rates.getRate();
    }
}
