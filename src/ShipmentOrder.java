public abstract class ShipmentOrder implements SummaryPrintable {
    protected String orderNumber;
    protected String customerName;
    protected double distanceKm;
    protected double baseFee;
    protected boolean insured;
    protected double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
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
        if (orderNumber == null || orderNumber.isBlank()) {
            throw new IllegalArgumentException("Order number cannot be blank");
        }
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }
        if (distanceKm <= 0){
            throw new IllegalArgumentException("Distance km must be greater than 0");
        }
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
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
