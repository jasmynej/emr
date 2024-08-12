import {useEffect, useState} from "react";

function PatientDashboard(){
    const[patient,setPatient] = useState({})

    useEffect(() => {
        console.log(localStorage.getItem("patient"))
        setPatient(JSON.parse(localStorage.getItem("patient")))
    }, []);
    return (
        <div>
            <h1>Welcome {patient.firstName}</h1>
        </div>
    )
}

export default PatientDashboard