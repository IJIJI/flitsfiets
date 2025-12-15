import {MdDirectionsBike, MdFlashOn} from "react-icons/md";
import "./logo.less";

export default function LargeLogo() {
    return (
        <>
            <MdDirectionsBike size={"14rem"} color={"white"}/><h1 className={"m-0 text-white"}>BikeFla<MdFlashOn
            style={{height: "3rem", margin: "0 -0.5rem"}}
            className={"text-primary"}/>h</h1>
        </>
    );
}