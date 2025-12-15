// import {type HTMLFormMethod} from "react-router";
import "./form.less"

// export default function FormContainer({children, className, url, method}: {
//     children: React.ReactNode | React.ReactNode[],
//     className?: string,
//     url: string,
//     method: HTMLFormMethod
// }) {

export default function FormContainer({children, className}: {
    children: React.ReactNode | React.ReactNode[],
    className?: string,
}) {

    return (
        <div
            className={"formContainer " + (className ? className : "")}>
            {children}
        </div>
    )
}