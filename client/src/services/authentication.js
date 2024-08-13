import axios from "axios";

async function patientLogIn(patientLoginForm){
    let loginResponse = await axios.post("http://127.0.0.1:9140/api/patients/login",patientLoginForm)
    if(loginResponse.data === ""){
        console.log(loginResponse.data)
        return false
    }
    else {
        localStorage.setItem("patient",JSON.stringify(loginResponse.data))
        console.log(loginResponse.data)
        return true
    }

}

async function providerLogIn(providerLoginForm){
    let loginResponse = await axios.post("http://127.0.0.1:9140/api/healthcare-providers/login",providerLoginForm)
    if(loginResponse.data === ""){
        console.log(loginResponse.data)

        return false
    }
    else {
        localStorage.setItem("provider",JSON.stringify(loginResponse.data))
        console.log(loginResponse.data)
        return true
    }
}

function patientLogOut(){
    localStorage.removeItem("patient")
}

function providerLogOut() {
    localStorage.removeItem("provider")
}

export {patientLogIn,providerLogIn,patientLogOut,providerLogOut}