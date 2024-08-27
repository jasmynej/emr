import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {providerLogOut} from "../../services/authentication.js";
import "../../styles/dashboard.css"
import logo from "../../assets/stethoscope.svg"
import Provider from "../../models/Provider.js";
import VisitCard from "../../components/VisitCard.jsx";
function ProviderDashboard(){
    const [provider,setProvider] = useState(Provider.createWithDefaults)
    const [appointments,setAppointments] = useState([])
    const [notifications,setNotifications] = useState([])
    const navigate = useNavigate()

    const logOut = () => {
        providerLogOut()
        navigate("/")
    }
    useEffect(() => {
        let p = Provider.fromJson(JSON.parse(localStorage.getItem("provider")))
        setProvider(p)
    }, []);

    useEffect(() => {
        if(provider.id !== null){
            setNotifications(provider.getUnreadNotifications())
            provider.getAppointments().then(function (response){
                setAppointments(response)
            })
                .catch((e)=>console.log(e))
        }
    }, [provider]);

    return (
        <div className="h-screen w-screen bg-amber-100 overflow-scroll">
            <div className="nav-container">
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
                    <i className="fi fi-rr-user text-xl"></i>
                    <div className="relative">
                        <i className="fi fi-rr-bell text-xl"></i>
                        {notifications.length > 0 && <span
                            className="absolute top-0 right-0 block h-2 w-2 rounded-full ring-2 ring-white bg-red-500"></span>}

                    </div>

                    <button onClick={logOut} className="logout-btn">Log out</button>
                </div>

            </div>
            <div className="bg-white w-max rounded m-5 p-5">
                <h1 className="text-2xl">{provider.getFullName()}</h1>
                <h2 className="text-xl font-thin">{provider.role}, {provider.specialty}</h2>
                <h2>{provider.officeAddress}</h2>
            </div>
            <div className="w-2/3 h-2/3 flex flex-col flex-wrap">
                {
                    appointments.map((appt)=> {
                        return (
                            <VisitCard visit={appt} key="x"/>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default ProviderDashboard