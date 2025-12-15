import {MdArrowForward, MdOpenInNew} from "react-icons/md";
import {Link} from "react-router";
import LargeLogo from "../../components/logo/LargeLogo.tsx";


export default function Home() {
    return (
        <div className={"d-flex mainLogo flex-column w-100 justify-content-center align-items-center gap-2"} style={{height:'80vh'}}>
            <LargeLogo />
            <Link to={"/register"} className="btn btn-primary btn-lg primary" viewTransition>
                Get started <MdArrowForward/>
            </Link>
            <a href="https://github.com/IJIJI/flitsfiets" className={"text-secondary"} target="_blank">
                <small>View Repo on GitHub <MdOpenInNew/></small>
            </a>
        </div>
    );
}