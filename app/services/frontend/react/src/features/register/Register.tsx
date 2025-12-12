import MultiStepFormContainer from "../../components/form/multistep/MultiStepFormContainer.tsx";
import FormStep from "../../components/form/multistep/FormStep.tsx";
import {useState} from "react";
import {FormGroup, Input, Label} from "reactstrap";
import BikeCardGrid from "./cards/BikeCardGrid/BikeCardGrid.tsx";
import BikeCard from "./cards/BikeCardGrid/BikeCard.tsx";
import {MdDirectionsBike, MdElectricBike, MdElectricMoped} from "react-icons/md";
import PlanCardGrid from "./cards/PlanCardGrid/PlanCardGrid.tsx";
import PlanCard from "./cards/PlanCardGrid/PlanCard.tsx";

export default function Register() {

    const [city, setCity] = useState<string | null>(null);

    const [activeBike, setActiveBike] = useState(0);
    const [activePlan, setActivePlan] = useState(0);

    return (
        <MultiStepFormContainer
            titles={["Choose a City", "Our Bikes in " + city, "Choose Your Plan", "Nice to meet you!", "Make an Appointment"]}
            completed={[city != null, activeBike != 0, activePlan != 0, false, true]}
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
                        value={city ?? ""}
                        onChange={(e) => setCity(e.target.value)}
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
                    <BikeCard index={1} activeIndex={activeBike} setIndex={setActiveBike} title={"Regular"}
                              description={"Sturdy all-round bike"} startprice={"€19,99"}
                              icon={<MdDirectionsBike/>}/>
                    <BikeCard index={3} activeIndex={activeBike} setIndex={setActiveBike} title={"Electric"}
                              description={"Powerful electric bike that will get you places"} startprice={"€29,99"}
                              icon={<MdElectricBike/>}/>
                    <BikeCard index={4} activeIndex={activeBike} setIndex={setActiveBike} title={"Moped"}
                              description={"Fast electric moped: Never be late again"} startprice={"€39,99"}
                              icon={<MdElectricMoped/>}/>
                </BikeCardGrid>
            </FormStep>
            <PlanCardGrid>
                <PlanCard title={"Monthly"} index={1} activeIndex={activePlan} setIndex={setActivePlan} price={"35,-"}
                />
                <PlanCard title={"Yearly"} index={2} activeIndex={activePlan} setIndex={setActivePlan} price={"35,-"}
                          discountedprice={"29,99"}/>
                <PlanCard title={"2 Years"} index={3} activeIndex={activePlan} setIndex={setActivePlan} price={"35,-"}
                          discountedprice={"19,99"}/>
            </PlanCardGrid>
            <FormStep>
                Step 3 Content
            </FormStep>
            <FormStep>
                Step 4 Content
            </FormStep>
            <FormStep>
                Step 5 Content
            </FormStep>
        </MultiStepFormContainer>
    );
}