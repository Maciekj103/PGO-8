import java.security.InvalidParameterException;

public class PickupPointShipment extends ShipmentOrder {
    private String lockerSize;
    private boolean fragile;

    public PickupPointShipment(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured, String lockerSize, boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile = fragile;
    }

    @Override
    public void validateSpecificRules(){
        if ( lockerSize== null || lockerSize.isBlank() || !( lockerSize.equals("S") || lockerSize.equals("M") ||  lockerSize.equals("L") ) ) {
            throw new IllegalArgumentException("Invalid locker size");
        }
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 0.75;
    }

    @Override
    protected double calculateAdditionalFee() {
        double additionalFee = 0;
        if (lockerSize.equals("S")) additionalFee += 5.00;
        else if (lockerSize.equals("M")) additionalFee += 10.00;
        else if (lockerSize.equals("L")) additionalFee += 18.00;
        if (fragile) additionalFee += 12.00;
        return additionalFee;
    }

    @Override
    public String getShipmentType() {
        return "Pickup point";
    }
}
