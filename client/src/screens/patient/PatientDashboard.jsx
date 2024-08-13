import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {patientLogOut} from "../../services/authentication.js";

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
        <div>
            <h1>Welcome {patient.firstName}</h1>
            <button className="bg-red-500" onClick={logOut}>Log Out</button>
        </div>
    )
}

export default PatientDashboard