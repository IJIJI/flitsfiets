import {BrowserRouter, Route, Routes} from "react-router";
import Base from "./components/Base.tsx";
import Home from "./features/home/Home.tsx";
import Register from "./features/register/Register.tsx";


export default function App() {

    return(
        <BrowserRouter>
            <Routes>
                <Route element={<Base />}>
                    <Route path="/" element={<Home />} />
                    <Route path="/register" element={<Register />} />
                </Route>
            </Routes>
        </BrowserRouter>
    )
}