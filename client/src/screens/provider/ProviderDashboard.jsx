import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {providerLogOut} from "../../services/authentication.js";
import "../../styles/dashboard.css"
import logo from "../../assets/stethoscope.svg"
function ProviderDashboard(){
    const [provider,setProvider] = useState({})
    const navigate = useNavigate()

    const logOut = () => {
        providerLogOut()
        navigate("/")
    }
    useEffect(() => {
        setProvider(JSON.parse(localStorage.getItem("provider")))
    }, []);
    return (
        <div className="h-screen w-screen bg-amber-100">
            <div className="w-screen h-24 bg-white flex justify-between items-center">
                <div className="nav-logo flex flex-row gap-3 items-center">
                    <img src={logo} alt="heart and stesthescope" className="w-20"/>
                    <h2 className="text-xl">VitalHealth</h2>
                </div>
                <div className="dashboard-nav">
                    <div className="dashboard-nav-item active">
                        <p>Dashboard</p>
                    </div>
                    <div className="dashboard-nav-item">
                        <p>Appointments</p>
                    </div>
                    <div className="dashboard-nav-item">
                        <p>Patients</p>
                    </div>
                    <div className="dashboard-nav-item">
                        <p>Reports</p>
                    </div>
                </div>
                <div className="flex flex-row items-center gap-3">
                    <i className="fi fi-rr-user"></i>
                    <i className="fi fi-rr-bell"></i>
                    <button onClick={logOut} className="logout-btn">Log out</button>
                </div>

            </div>
            <h1>Welcome {provider.firstName} </h1>
        </div>
    )
}

export default ProviderDashboard