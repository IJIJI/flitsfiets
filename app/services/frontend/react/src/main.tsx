import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Route, Routes} from "react-router";
import Home from "./features/home/Home.tsx";
import Base from "./components/Base.tsx";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <BrowserRouter>
          <Routes>
              <Route element={<Base />}>
                  <Route path="/" element={<Home />} />
              </Route>
          </Routes>
      </BrowserRouter>
  </StrictMode>
)
