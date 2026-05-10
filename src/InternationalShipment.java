public class InternationalShipment extends ShipmentOrder {
    private String destinationCountry;
    private boolean customsDocumentsRequired;
    private boolean expressDelivery;
    public InternationalShipment(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured, String destinationCountry, boolean customsDocumentsRequired, boolean expressDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.destinationCountry = destinationCountry;
        this.customsDocumentsRequired = customsDocumentsRequired;
        this.expressDelivery = expressDelivery;
    }

    @Override
    public void validateSpecificRules(){
        if (destinationCountry == null || destinationCountry.isBlank()) {
            throw new IllegalArgumentException("Destination Country cannot be empty");
        }
    }

    @Override
    public double applyBusinessDiscount(double price){
        if (!expressDelivery && distanceKm > 1000)
            return price * 0.97;
        return price;
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 2.10;
    }

    @Override
    protected double calculateAdditionalFee() {
        double additionalFee = 0;
            if (customsDocumentsRequired) additionalFee += 45.00;
            if (expressDelivery) additionalFee += 80.00;
        return additionalFee;
    }

    @Override
    public String getShipmentType() {
        return "International";
    }
}
