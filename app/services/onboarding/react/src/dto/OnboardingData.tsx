export enum BikeType {
    UNDEFINED = 0,
    Regular = "REGULAR",
    Electric = "ELECTRIC",
    Moped = "MOPED",
}
export enum PlanType {
    UNDEFINED = 0,
    Monthly = "MONTHLY",
    Yearly = "YEARLY",
    BiYearly = "BIYEARLY",
}

export interface OnboardingData {
    bikeType: BikeType;
    planType: PlanType;
    pickupCity: string;
    personal: {
        name: string;
        surname: string;
        birthdate: string;
        length: number;
    };
    address: {
        postalCode: string;
        streetNumber: string;
        street: string;
        city: string;
        country: string;
    };
    contact: {
        telephone: string;
        email: string;
        password: string;
    };
}