import ProviderNotification from "./ProviderNotification.js";
import axios from "axios";
class Provider {

    constructor(id, firstName, lastName, email, role, specialty, officeAddress, phoneNumber, createdAt, updatedAt, notifications = []) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.specialty = specialty;
        this.officeAddress = officeAddress;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.notifications = notifications;
    }

    static createWithDefaults() {
        return new Provider(null, '', '', '', '', '', '', '', new Date(), new Date(), []);
    }


    static fromJson(json) {
        const notifications = json.notifications.map(notificationJson => ProviderNotification.fromJson(notificationJson));
        return new Provider(
            json.id,
            json.firstName,
            json.lastName,
            json.email,
            json.role,
            json.specialty,
            json.officeAddress,
            json.phoneNumber,
            json.createdAt,
            json.updatedAt,
            notifications
        );
    }

    getFullName() {
        return `${this.firstName} ${this.lastName}`;
    }


    getUnreadNotifications() {
        return this.notifications.filter(notification => !notification.read);
    }

    async getAppointments(){
        const visitResponse = await axios.get(`http://192.168.1.162:9140/api/healthcare-providers/${this.id}/visits`)
        return visitResponse.data
    }

}

export default Provider