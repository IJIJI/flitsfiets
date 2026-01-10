import {BrowserRouter, Route, Routes} from "react-router";
import Base from "./components/Base.tsx";
import Home from "./features/home/Home.tsx";
import Register from "./features/register/Register.tsx";
import "./app.less";
import {ToastContainer} from "react-toastify";
import Login from "./features/register/Login.tsx";

export default function App() {

    return (
        <>
            <ToastContainer/>
            <BrowserRouter>
                <Routes>
                    <Route element={<Base hideLogo={true}/>}>
                        <Route index element={<Home/>}/>
                    </Route>
                    <Route element={<Base/>}>
                        <Route path="/register" element={<Register/>}/>
                        <Route path="/login" element={<Login/>}/>
                    </Route>
                </Routes>
            </BrowserRouter>
        </>
    )
}