public class DomesticCourierShipment extends  ShipmentOrder {
    private double packageWeightKg;
    private boolean weekendDelivery;

    public DomesticCourierShipment(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured, double packageWeightKg, boolean weekendDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.weekendDelivery = weekendDelivery;
        this.packageWeightKg = packageWeightKg;
    }

    @Override
    public double applyBusinessDiscount(double price) {
        return distanceKm >= 300 ? price * 0.95 : price;
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 1.20;
    }

    @Override
    protected double calculateAdditionalFee() {
        return packageWeightKg * 4.00 + (weekendDelivery ? 25 : 0 );
    }

    @Override
    public String getShipmentType() {
        return "Domestic courier";
    }
}
