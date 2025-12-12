import {type HTMLFormMethod} from "react-router";
import "./form.less"

export default function FormContainer({children, className, url, method}: {
    children: React.ReactNode | React.ReactNode[],
    className?: string,
    url: string,
    method: HTMLFormMethod
}) {

    return (
        <div
            className={"formContainer " + (className ? className : "")}>
            {children}
        </div>
    )
}