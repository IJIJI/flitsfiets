import {Children, type ReactElement, useState} from "react";
import FormStep from "./FormStep.tsx";
import FormContainer from "../FormContainer.tsx";
import {MdArrowBack} from "react-icons/md";

export default function MultiStepFormContainer({children, titles, completed}: { children: ReactElement<typeof FormStep>[], titles: string[], completed: boolean[] }) {

    const steps = Children.toArray(children);

    const [currentStep, setCurrentStep] = useState(0);
    // const [currentStep, setCurrentStep] = useState(steps[0]);

    return (
        <FormContainer className="multistep-form-container">
            <div className="header">
                <h1>{titles[currentStep]}</h1>
                <div className="progressBar">
                    <div className="progress" style={{width: currentStep/steps.length*100+"%"}}></div>
                </div>
            </div>
            <div className="stepContent">
                {children[currentStep]}
            </div>
            <div className="footer d-flex justify-content-between">
                <div className={"backContainer "+(!(currentStep > 0) ? "hidden" : "")}>
                    <button className="btn btn-secondary" onClick={() => setCurrentStep(currentStep - 1)} disabled={!(currentStep > 0)}><MdArrowBack /></button>
                </div>
                {currentStep < steps.length - 1 ?
                    <button className="btn btn-primary w-100" onClick={() => setCurrentStep(currentStep + 1)} disabled={!(completed[currentStep])}>Next</button>
                    :
                    <button className="btn btn-primary w-100" type="submit">Submit</button>
                }
            </div>
        </FormContainer>
    );
}