import {Children, type ReactElement} from "react";
import FormStep from "./FormStep.tsx";
import FormContainer from "../FormContainer.tsx";
import {MdArrowBack} from "react-icons/md";

export default function MultiStepFormContainer({
                                                   children,
                                                   currentStep,
                                                   setCurrentStep,
                                                   titles,
                                                   completed,
                                                   callback,
                                                   blockReturn,
                                                   buttonText
                                               }: {
    children: ReactElement<typeof FormStep>[], //TODO should be array of objects with all the options that are now in seperate arrays
    currentStep: number,
    setCurrentStep: (newStep: number) => void,
    titles: string[],
    completed: boolean[],
    callback: Array<null | (() => void)>,
    blockReturn: boolean[],
    buttonText: string[]
}) {

    const steps = Children.toArray(children);

    const nextstep = () => {
        const currentCallback = callback[currentStep];

        if (currentStep < children.length-1)
        setCurrentStep(currentStep + 1);

        if (currentCallback != null) {
            currentCallback();
        }
    }

    return (
        <FormContainer className="multistep-form-container">
            <div className="header">
                <h1>{titles[currentStep]}</h1>
                <div className="progressBar">
                    <div className="progress" style={{width: currentStep / steps.length * 100 + "%"}}></div>
                </div>
            </div>
            <div className="stepContent">
                {children[currentStep]}
            </div>
            <div className="footer d-flex justify-content-between">
                <div className={"backContainer " + (!(currentStep > 0) || blockReturn[currentStep] ? "hidden" : "")}>
                    <button className="btn btn-secondary" onClick={() => setCurrentStep(currentStep - 1)}
                            disabled={!(currentStep > 0)}><MdArrowBack/></button>
                </div>
                <button className="btn btn-primary w-100" onClick={() => nextstep()}
                        disabled={!(completed[currentStep])}>{buttonText[currentStep]}</button>
            </div>
        </FormContainer>
    );
}