import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {patientLogOut} from "../../services/authentication.js";
import logo from "../../assets/stethoscope.svg"
import "../../styles/dashboard.css"
function PatientDashboard(){
    const[patient,setPatient] = useState({})
    const navigate = useNavigate()

    const logOut = () =>{
        patientLogOut()
        navigate("/")
    }
    useEffect(() => {
        console.log(localStorage.getItem("patient"))
        setPatient(JSON.parse(localStorage.getItem("patient")))
    }, []);
    return (
        <div className="h-screen w-screen bg-cyan-50">
            <div className="nav-container">
                <div className="nav-logo flex flex-row gap-3 items-center">
                    <img src={logo} alt="heart and stesthescope" className="w-20"/>
                    <h2 className="text-xl">VitalHealth</h2>
                </div>
                <button className="logout-btn" onClick={logOut}>Log Out</button>
            </div>
            <h1>Welcome {patient.firstName}</h1>

        </div>
    )
}

export default PatientDashboard