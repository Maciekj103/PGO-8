public abstract class ShipmentOrder implements SummaryPrintable {
    public int orderNumber;
    public String customerName;
    public double distanceKm;
    public double baseFee;
    public boolean insured;
    public double lastCalculatedPrice;

    public ShipmentOrder(int orderNumber, String customerName, double distanceKm, double baseFee, boolean insured, double lastCalculatedPrice) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
        this.lastCalculatedPrice = lastCalculatedPrice;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder(){

    }

    protected void validateSpecificRules(){

    }

    private double applyInsurance(double price){
        return insured ? price * 1.07 : price;
    }

    protected double applyBusinessDiscount(double price){
        return price;
    }

    private void printProcessingResult(){
        System.out.println("Numer zamówienia: " + orderNumber + "\nImię klienta: " + customerName +
                "\nDystans w kilometrach: " + distanceKm + "\nStawka bazowa: " + baseFee + "\nUbezpieczony: " + insured +
                "\nOstatnia cena: " + lastCalculatedPrice);
    }

    public String buildSummaryLine(){
        return  "Numer zamówienia: " + orderNumber + "\nImię klienta: " + customerName +
                "\nTyp przesyłki: " + getShipmentType() + "\nOstatnia cena: " + lastCalculatedPrice;
    }

    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();

    // gettery i settery

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(double baseFee) {
        this.baseFee = baseFee;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public double getLastCalculatedPrice() {
        return lastCalculatedPrice;
    }

    public void setLastCalculatedPrice(double lastCalculatedPrice) {
        this.lastCalculatedPrice = lastCalculatedPrice;
    }
}
