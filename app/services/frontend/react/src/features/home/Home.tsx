import {MdArrowForward, MdDirectionsBike, MdFlashOn, MdOpenInNew} from "react-icons/md";
import {Link} from "react-router";


export default function Home () {
    return (
        <div className={"d-flex flex-column h-100 w-100 justify-content-center align-items-center gap-2"}>
            <MdDirectionsBike size={"14rem"} color={"white"}/>
            <h1 className={"m-0 text-white"}>BikeFla<MdFlashOn style={{height: "3rem", margin: "0 -0.5rem"}} className={"text-primary"}/>h</h1>
            <Link to={"/register"} className="btn btn-primary btn-lg primary" viewTransition>
                Get started <MdArrowForward />
            </Link>
            <a href="https://github.com/IJIJI/flitsfiets" className={"text-secondary"} target="_top">
                <small>View Repo on GitHub <MdOpenInNew /></small>
            </a>
        </div>
    );
}