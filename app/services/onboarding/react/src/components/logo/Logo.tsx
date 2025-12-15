import {MdDirectionsBike, MdFlashOn} from "react-icons/md";
import "./logo.less";

export default function Logo() {
    return (
        <div className={"d-flex mainLogo flex-row justify-content-center align-items-center gap-2"}>
            <MdDirectionsBike size={"2.5rem"} color={"white"}/>
            <h1 className={"m-0 text-white"}>BikeFla<MdFlashOn style={{height: "3rem", margin: "0 -0.5rem"}} className={"text-primary"}/>h</h1>
        </div>
    );
}