import './App.css'
import logo from "./assets/stethoscope.svg"
import { useNavigate } from "react-router-dom";
function App() {
    const navigate = useNavigate();

  return (
   <div className="w-full">
       <div className="w-full h-screen flex flex-col items-center justify-center bg-amber-200">
           <div className="bg-amber-50 p-6 flex flex-col items-center rounded-md">
               <div className="flex flex-row items-center w-52 gap-2 justify-center p-3">
                   <img src={logo} alt="heart and stesthescope smiling" className="w-20"/>
                   <h2 className="text-4xl">VitalHealth</h2>
               </div>
               <div className="justify-self-end gap-2 justify-between flex-row flex mr-2">
                   <button onClick={() => navigate("/patient-login")} className="text-xl bg-cyan-400 p-3 rounded-full text-white hover:bg-cyan-500 duration-300 transition ease-in-out drop-shadow-md ">Patients Click
                       Here
                   </button>
                   <button onClick={() => navigate("/provider-login")} className="text-xl bg-pink-400 text-white p-3 rounded-full hover:bg-pink-500 duration-300 transition ease-in-out drop-shadow-md">Providers Click
                       Here
                   </button>
               </div>
           </div>


       </div>
   </div>
  )
}

export default App
