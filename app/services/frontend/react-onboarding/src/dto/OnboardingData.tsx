export enum BikeType {
    UNDEFINED = 0,
    Regular = 1,
    Electric = 2,
    Moped = 3,
}
export enum PlanType {
    UNDEFINED = 0,
    Monthly = 1,
    Yearly = 2,
    BiYearly = 3,
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
        streetNumber: number;
        street: string;
        numberAddition: string;
        city: string;
        country: string;
    };
    contact: {
        telephone: string;
        email: string;
        password: string;
    };
}