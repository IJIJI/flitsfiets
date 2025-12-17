import MultiStepFormContainer from "../../components/form/multistep/MultiStepFormContainer.tsx";
import FormStep from "../../components/form/multistep/FormStep.tsx";
import {createRef, useRef, useState} from "react";
import {Col, Form, FormFeedback, FormGroup, Input, InputGroup, InputGroupText, Label} from "reactstrap";
import BikeCardGrid from "./cards/BikeCardGrid/BikeCardGrid.tsx";
import BikeCard from "./cards/BikeCardGrid/BikeCard.tsx";
import {MdDirectionsBike, MdElectricBike, MdElectricMoped} from "react-icons/md";
import PlanCardGrid from "./cards/PlanCardGrid/PlanCardGrid.tsx";
import PlanCard from "./cards/PlanCardGrid/PlanCard.tsx";
import {BikeType, type OnboardingData, PlanType} from "../../dto/OnboardingData.tsx";
import Spinner from "../../components/form/loading/Spinner.tsx"
import type {UserData} from "../../dto/UserData.tsx";
import {toast} from "react-toastify";

export default function Register() {

    const [currentStep, setCurrentStep] = useState(0);

    const [onboardingData, setOnboardingData] = useState<OnboardingData>({
        pickupCity: "",
        bikeType: BikeType.UNDEFINED,
        planType: PlanType.UNDEFINED,
        personal: {birthdate: "", length: 0, name: "", surname: ""},
        address: {city: "", country: "", postalCode: "", street: "", streetNumber: ""},
        contact: {email: "", password: "", telephone: ""}

    });

    const setActiveBike = (index: number) => {
        setOnboardingData({
            ...onboardingData,
            bikeType: index as BikeType
        })
    }
    const setActivePlan = (index: number) => {
        setOnboardingData({
            ...onboardingData,
            planType: index as PlanType
        })
    }

    const lenghtInputRef = createRef<HTMLInputElement>();
    const [lengthInputValid, setLengthInputValid] = useState(true);

    const updateLengthInputValidity = () => {
        const input = lenghtInputRef.current;
        if (!input) return;
        console.log(input.value);
        setLengthInputValid(input.checkValidity());
    }


    const passRef = createRef<HTMLInputElement>();
    const passCheckRef = createRef<HTMLInputElement>();

    const [passwordsMatch, setPasswordsMatch] = useState(true);
    const checkPassEqual = () => {
        if (passRef?.current && passCheckRef?.current)
            setPasswordsMatch(passRef.current.value == passCheckRef.current.value || passCheckRef.current.value.length == 0)
    }

    const detailsFormRef = useRef<HTMLFormElement>(null);
    const [detailsFormValid, setDetailsFormValid] = useState(false);

    const updateDetailsFormValidity = () => {
        const form = detailsFormRef.current;
        if (!form) return;
        setDetailsFormValid(form.checkValidity());
    };

    const contactFormRef = useRef<HTMLFormElement>(null);
    const [contactFormValid, setContactFormValid] = useState(false);

    const emailInputRef = useRef<HTMLInputElement>(null);
    const [emailInputValid, setEmailInputValid] = useState(true);

    const updateContactFormValidity = () => {
        const form = contactFormRef.current;
        if (!form) return;
        setContactFormValid(form.checkValidity());
    };

    const updateEmailInputValidity = () => {
        const input = emailInputRef.current;
        if (!input) return;
        setEmailInputValid(input.checkValidity() || input.value.length == 0);
    };

    const [userData, setUserData] = useState<UserData>({
        id: 0,
    });
    const [appointmentOptions, setAppointmentOptions] = useState(null);


    const createUser = () => {

        fetch("/api/onboarding", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(onboardingData)
        })
            .then(res => {
                if (res.status != 200)
                    throw new Error(res.statusText);

                return res.json()
            })
            .then(data => {
                console.log("Data:", data);
                setUserData(data.user);
                setAppointmentOptions(data.appointmentOptions);
            })
            .catch(err => {
                console.log("ERROR: ", err);
                setCurrentStep(4);
                toast.error("HTTP Error: " + err.message);
            })
    }

    const [selectedAppointment, setSelectedAppointment] = useState(null);

    const createAppointment = () => {
        // alert("Creating appointment!");
    }


    return (
        <MultiStepFormContainer
            currentStep={currentStep}
            setCurrentStep={setCurrentStep}
            titles={[
                "Choose a City",
                "Our Bikes in " + onboardingData.pickupCity,
                "Choose Your Plan",
                "Nice to meet you!",
                "Lets stay in touch",
                "Welcome " + onboardingData.personal.name + "!",
                "Make an Appointment",
                "All Done"
            ]}
            buttonText={[
                "Next",
                "Next",
                "Next",
                "Next",
                "Create Account",
                "Make Appointment",
                "Finish",
            ]}
            completed={[
                onboardingData.pickupCity != null && onboardingData.pickupCity != "",
                onboardingData.bikeType != BikeType.UNDEFINED,
                onboardingData.planType != PlanType.UNDEFINED,
                detailsFormValid,
                contactFormValid,
                userData.id != 0,
                selectedAppointment != null
            ]}
            blockReturn={[
                false,
                false,
                false,
                false,
                false,
                true
            ]}
            callback={[
                null,
                null,
                null,
                null,
                createUser,
                createAppointment
            ]}
        >
            <FormStep>
                <FormGroup>
                    <Label for="citySelect">
                        Select
                    </Label>
                    <Input
                        id="citySelect"
                        name="city"
                        type="select"
                        value={onboardingData.pickupCity ?? ""}
                        onChange={(e) => {
                            setOnboardingData({
                                ...onboardingData,
                                pickupCity: e.target.value
                            });
                        }}
                    >
                        <option value="" disabled>
                            -- Select a City --
                        </option>
                        <option>
                            Amsterdam
                        </option>
                        <option>
                            Breda
                        </option>
                        <option>
                            Delft
                        </option>
                        <option>
                            Den Haag
                        </option>
                        <option>
                            Rotterdam
                        </option>
                    </Input>
                </FormGroup>
            </FormStep>
            <FormStep>
                <BikeCardGrid>
                    <BikeCard index={1} activeIndex={onboardingData.bikeType} setIndex={setActiveBike}
                              title={"Regular"}
                              description={"Sturdy all-round bike"} startprice={"€19,99"}
                              icon={<MdDirectionsBike/>}/>
                    <BikeCard index={3} activeIndex={onboardingData.bikeType} setIndex={setActiveBike}
                              title={"Electric"}
                              description={"Powerful electric bike that will get you places"} startprice={"€29,99"}
                              icon={<MdElectricBike/>}/>
                    <BikeCard index={4} activeIndex={onboardingData.bikeType} setIndex={setActiveBike}
                              title={"Moped"}
                              description={"Fast electric moped: Never be late again"} startprice={"€39,99"}
                              icon={<MdElectricMoped/>}/>
                </BikeCardGrid>
            </FormStep>
            <PlanCardGrid>
                <PlanCard title={"Monthly"} index={1} activeIndex={onboardingData.planType} setIndex={setActivePlan}
                          price={"35,-"}
                />
                <PlanCard title={"Yearly"} index={2} activeIndex={onboardingData.planType} setIndex={setActivePlan}
                          price={"35,-"}
                          discountedprice={"29,99"}/>
                <PlanCard title={"2 Years"} index={3} activeIndex={onboardingData.planType} setIndex={setActivePlan}
                          price={"35,-"}
                          discountedprice={"19,99"}/>
            </PlanCardGrid>
            <FormStep>
                <Form innerRef={detailsFormRef} onInput={updateDetailsFormValidity}
                      onChange={updateDetailsFormValidity}>
                    <Col className={"d-flex flex-row p-0 m-0"}>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="name">Name:</Label>
                            <Input type="text" name="name" id="name"
                                   autoComplete={"cc-given-name"}
                                   value={onboardingData.personal.name}
                                   onChange={(event) => {
                                       setOnboardingData({
                                           ...onboardingData,
                                           personal: {
                                               ...onboardingData.personal,
                                               name: event.target.value
                                           }
                                       });
                                   }}
                                   required/>
                        </FormGroup>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="surname">Surname:</Label>
                            <Input type="text" name="surname" id="surname"
                                   value={onboardingData.personal.surname}
                                   autoComplete={"cc-family-name"}
                                   onChange={(event) => {
                                       setOnboardingData({
                                           ...onboardingData,
                                           personal: {
                                               ...onboardingData.personal,
                                               surname: event.target.value
                                           }
                                       });
                                   }}
                                   required/>
                        </FormGroup>
                    </Col>
                    <Col className={"d-flex flex-row p-0 m-0"}>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="birtdate">Birtddate:</Label>
                            <Input type="date" name="birtdate" id="birtdate"
                                   value={onboardingData.personal.birthdate}
                                   autoComplete={"bday"}
                                   onChange={(event) => {
                                       setOnboardingData({
                                           ...onboardingData,
                                           personal: {
                                               ...onboardingData.personal,
                                               birthdate: event.target.value
                                           }
                                       });
                                   }}
                                   required/>
                        </FormGroup>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="length">Length:</Label>
                            <InputGroup>
                                <Input type="number" name="length" min={100} max={230} id="length"
                                       value={onboardingData.personal.length != 0 ? onboardingData.personal.length : ""}
                                       innerRef={lenghtInputRef}
                                       onChange={(event) => {
                                           setOnboardingData({
                                               ...onboardingData,
                                               personal: {
                                                   ...onboardingData.personal,
                                                   length: event.target.value as any as number
                                               }
                                           });
                                           updateLengthInputValidity();
                                       }}
                                       invalid={!lengthInputValid}
                                       required/>
                                <InputGroupText>
                                    cm
                                </InputGroupText>
                                <FormFeedback invalid={"true"}>
                                    We only support lengths between 100 and 230 cm
                                </FormFeedback>
                            </InputGroup>
                        </FormGroup>
                    </Col>
                    <h4>Address Details</h4>

                    <Col className={"d-flex flex-row p-0 m-0"}>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="postal">Postal Code:</Label>
                            <Input type="text" name="postal" id="postal"
                                   value={onboardingData.address.postalCode}
                                   autoComplete={"postal-code"}
                                   onChange={(event) => {
                                       setOnboardingData({
                                           ...onboardingData,
                                           address: {
                                               ...onboardingData.address,
                                               postalCode: event.target.value
                                           }
                                       });
                                   }}
                                   required/>
                        </FormGroup>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="streetnumber">House Number:</Label>
                            <Input type="text" name="streetnumber" id="streetnumber"
                                   value={onboardingData.address.streetNumber ? onboardingData.address.streetNumber : ""}
                                   autoComplete={"postal-code"}
                                   onChange={(event) => {
                                       setOnboardingData({
                                           ...onboardingData,
                                           address: {
                                               ...onboardingData.address,
                                               streetNumber: event.target.value
                                           }
                                       });
                                   }}
                                   required/>
                        </FormGroup>
                    </Col>
                    <FormGroup>
                        <Label className={"m-0"} for="street">Street:</Label>
                        <Input type="text" name="street" id="street"
                               value={onboardingData.address.street}
                               autoComplete={"street-address"}
                               onChange={(event) => {
                                   setOnboardingData({
                                       ...onboardingData,
                                       address: {
                                           ...onboardingData.address,
                                           street: event.target.value
                                       }
                                   });
                               }}
                               required/>
                    </FormGroup>
                    <Col className={"d-flex flex-row p-0 m-0"}>
                        {/*<FormGroup className={"col-6 px-1"}>*/}
                        {/*    <Label className={"m-0"} for="numberaddition">Addition:</Label>*/}
                        {/*    <Input type="text" name="numberaddition" id="numberaddition"*/}
                        {/*           value={onboardingData.address.numberAddition}*/}
                        {/*           onChange={(event) => {*/}
                        {/*               setOnboardingData({*/}
                        {/*                   ...onboardingData,*/}
                        {/*                   address: {*/}
                        {/*                       ...onboardingData.address,*/}
                        {/*                       numberAddition: event.target.value*/}
                        {/*                   }*/}
                        {/*               });*/}
                        {/*           }}*/}
                        {/*    />*/}
                        {/*</FormGroup>*/}
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="city">City:</Label>
                            <Input type="text" name="city" id="city"
                                   value={onboardingData.address.city != "" ? onboardingData.address.city : onboardingData.pickupCity}
                                   onChange={(event) => {
                                       setOnboardingData({
                                           ...onboardingData,
                                           address: {
                                               ...onboardingData.address,
                                               city: event.target.value
                                           }
                                       });
                                   }}
                                   required/>
                        </FormGroup>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="country">Country:</Label>
                            <Input type="text" name="country" id="country" value={"The Netherlands"} disabled/>
                        </FormGroup>
                    </Col>

                </Form>
            </FormStep>
            <FormStep>
                <Form innerRef={contactFormRef} onInput={updateContactFormValidity}
                      onChange={updateContactFormValidity}>
                    <FormGroup>
                        <Label className={"m-0"} for="email">Tel:</Label>
                        <Input type="tel" name="telephone" id="telephone"
                               value={onboardingData.contact.telephone}
                               autoComplete={"tel"}
                               onChange={(event) => {
                                   setOnboardingData({
                                       ...onboardingData,
                                       contact: {
                                           ...onboardingData.contact,
                                           telephone: event.target.value
                                       }
                                   });
                               }}
                               required/>
                    </FormGroup>
                    <FormGroup>
                        <Label className={"m-0"} for="email">Email:</Label>
                        <Input type="email" name="email" id="email"
                               innerRef={emailInputRef}
                               value={onboardingData.contact.email}
                               autoComplete={"username"}
                               onChange={(event) => {
                                   setOnboardingData({
                                       ...onboardingData,
                                       contact: {
                                           ...onboardingData.contact,
                                           email: event.target.value
                                       }
                                   });
                                   updateEmailInputValidity();
                               }}
                               invalid={!emailInputValid}
                               required/>
                        <FormFeedback invalid={"true"}>
                            Email is invalid.
                        </FormFeedback>
                    </FormGroup>
                    <FormGroup>
                        <Label className={"m-0"} for="password">Password:</Label>
                        <Input id="password" innerRef={passRef}
                               value={onboardingData.contact.password}
                               autoComplete={"new-password"}
                               onChange={(event) => {
                                   checkPassEqual();
                                   setOnboardingData({
                                       ...onboardingData,
                                       contact: {
                                           ...onboardingData.contact,
                                           password: event.target.value
                                       }
                                   });
                               }}
                               type="password" name="password" invalid={!passwordsMatch} required/>

                    </FormGroup>
                    <FormGroup>
                        <Label className={"m-0"} for="passwordCheck">Confirm Password:</Label>
                        <Input id="passwordCheck" innerRef={passCheckRef} type="password"
                               name="passwordcheck"
                               invalid={!passwordsMatch}
                               onChange={checkPassEqual}
                               autoComplete={"new-password"}
                               required/>
                        <FormFeedback invalid={"true"}>
                            Passwords do not match.
                        </FormFeedback>
                    </FormGroup>
                </Form>
            </FormStep>
            <FormStep>
                {userData.id == 0 ?
                    <Spinner>Creating Account...</Spinner>
                    :
                    <Form>
                        Welcome to BikeFLash! A confirmation email has been sent to <b
                        className={"px-1"}>{userData.email + "."}</b> Make sure to confirm it.
                    </Form>
                }
            </FormStep>
            <FormStep>
                Select Appointment
            </FormStep>
            <FormStep>
                All done! You can pick up your bike at (). Don't hesitate to contact us with any questions!
            </FormStep>
        </MultiStepFormContainer>
    );
}