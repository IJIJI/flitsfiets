package synapt.flitsfiets.common.enums;

public enum PlanType
{
    MONTHLY("MONTHLY"),
    YEARLY("YEARLY"),
    BIYEARLY("BIYEARLY");

    public final String label;

    private PlanType(String label){
        this.label = label;
    }
}
