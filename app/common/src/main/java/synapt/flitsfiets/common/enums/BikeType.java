package synapt.flitsfiets.common.enums;

public enum BikeType
{
    REGULAR("REGULAR"),
    ELECTRIC("ELECTRIC"),
    PREMIUM("PREMIUM"),
    MOPED("MOPED");

    public final String label;

    private BikeType(String label){
        this.label = label;
    }
}
