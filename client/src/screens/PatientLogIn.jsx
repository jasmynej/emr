import {useState} from "react";
import {patientLogIn} from "../services/authentication.js";
import { useNavigate } from "react-router-dom";
function PatientLogIn(){

    const [loginForm,setLoginForm] = useState({
        lastName:"",
        birthDate:""
    })

    const navigate = useNavigate()

    const onFormChange = (event) => {
        let curForm = loginForm
        curForm[event.target.name] = event.target.value
        setLoginForm(curForm)
        console.log(loginForm)
    }

    const logIn = () => {
        patientLogIn(loginForm)
            .then(function(result){
                if(result){
                    navigate("/patient-dashboard")
                }
                else {
                    console.log("try again")
                }
            })
    }
    return (
        <div className="w-screen h-screen bg-amber-200 flex flex-col items-center justify-center">
            <div className="w-1/2 h-1/2 bg-amber-50 rounded-md flex flex-col items-center justify-center">
                <h1 className="text-3xl font-semibold">Patient Login</h1>
                <form className="m-6 w-3/4 flex flex-col items-center gap-3" onChange={onFormChange} onSubmit={(e) => e.preventDefault()}>
                    <div className="flex flex-row items-center w-4/5 justify-between">
                        <label className="text-2xl">Last Name</label>
                        <input type="text" name="lastName" className="p-2 w-2/3 border-2 border-stone-50"/>
                    </div>
                    <div className="flex flex-row items-center w-4/5 justify-between">
                        <label className="text-2xl">Date Of Birth</label>
                        <input type="date" name="birthDate" className="p-2 w-2/3 border-2 border-stone-50 rounded-md"/>
                    </div>
                    <button onClick={logIn} className=" w-full m-5 text-xl bg-cyan-400 p-2 rounded-full text-white hover:bg-cyan-500 duration-300 transition ease-in-out drop-shadow-sm ">Log In</button>
                </form>
            </div>
        </div>
    )
}

export default PatientLogIn