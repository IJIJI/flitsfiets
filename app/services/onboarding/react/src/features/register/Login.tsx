import FormContainer from "../../components/form/FormContainer.tsx";
import {Form, FormGroup, Input, Label} from "reactstrap";

export default function Login() {

    return (
        <FormContainer className="multistep-form-container">
            <div className="header">
                <h1>Login</h1>
                <div className="progressBar">
                    <div className="progress" style={{width: 0 * 100 + "%"}}></div>
                </div>
            </div>
            <div className="stepContent">
                <Form>
                    <FormGroup>
                        <Label className={"m-0"} for="email">Email:</Label>
                        <Input type="email" name="email" id="email"
                               autoComplete={"username"}
                               required/>
                    </FormGroup>
                    <FormGroup>
                        <Label className={"m-0"} for="password">Password:</Label>
                        <Input id="password"
                               autoComplete={"new-password"}
                               type="password" name="password" required/>

                    </FormGroup>
                </Form>
            </div>
            <div className="footer d-flex justify-content-between">
                <button className="btn btn-primary w-100" type="submit">Next</button>
            </div>
        </FormContainer>
    );
}