import {Col, Spinner} from "reactstrap";
import "./spinner.less";

export default function AdminSpinner({children}: {children?:string}) {
    return(
        <Col md={12} className={"form-spinner w-100 d-flex flex-column justify-content-center align-items-center mt-5 mb-2 gap-2"}>
            <Spinner
                color="primary"
            >
                {children ? children : "Loading..."}
            </Spinner>
            <small className={"m-0 fw-bold text-muted"} style={{fontSize: "0.75rem"}}>
                {children ? children : "Loading..."}
            </small>

        </Col>
    );
}