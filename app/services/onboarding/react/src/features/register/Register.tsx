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


export default function Register() {

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

    const updateEmailInoutValidity = () => {
        const input = emailInputRef.current;
        if (!input) return;
        setEmailInputValid(input.checkValidity() || input.value.length == 0);
    };

    return (
        <MultiStepFormContainer
            titles={["Choose a City", "Our Bikes in " + onboardingData.pickupCity, "Choose Your Plan", "Nice to meet you!", "Lets stay in touch", "Make an Appointment"]}
            completed={[onboardingData.pickupCity != null, onboardingData.bikeType != BikeType.UNDEFINED, onboardingData.planType != PlanType.UNDEFINED, detailsFormValid, contactFormValid]}
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
                                       onChange={(event) => {
                                           setOnboardingData({
                                               ...onboardingData,
                                               personal: {
                                                   ...onboardingData.personal,
                                                   length: event.target.value as any as number
                                               }
                                           });
                                       }}
                                       required/>

                                <InputGroupText>
                                    cm
                                </InputGroupText>
                            </InputGroup>
                        </FormGroup>
                    </Col>
                    <h4>Address Details</h4>

                    <Col className={"d-flex flex-row p-0 m-0"}>
                        <FormGroup className={"col-6 px-1"}>
                            <Label className={"m-0"} for="postal">Postal Code:</Label>
                            <Input type="text" name="postal" id="postal"
                                   value={onboardingData.address.postalCode}
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
                            <Input type="text" name="country" id="country" value={"The Netherlands"}  disabled/>
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
                               onChange={(event) => {
                                   setOnboardingData({
                                       ...onboardingData,
                                       contact: {
                                           ...onboardingData.contact,
                                           email: event.target.value
                                       }
                                   });
                                   updateEmailInoutValidity();
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
                Step 5 Content
            </FormStep>
        </MultiStepFormContainer>
    );
}