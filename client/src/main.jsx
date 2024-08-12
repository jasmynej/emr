import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import App from './App.jsx'
import './index.css'
import PatientLogIn from "./screens/PatientLogIn.jsx";
import ProviderLogin from "./screens/ProviderLogin.jsx";
import PatientDashboard from "./screens/patient/PatientDashboard.jsx";

const router = createBrowserRouter(
    [
        {
            path:"/",
            element:<App/>
        },
        {
            path:"/patient-login",
            element:<PatientLogIn/>
        },
        {
            path:"/provider-login",
            element:<ProviderLogin/>
        },
        {
            path:"/patient-dashboard",
            element:<PatientDashboard/>
        }
    ]
)

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>,
)
