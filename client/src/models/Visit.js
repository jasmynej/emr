class Visit {
    constructor(date,reason,notes,healthcareProviderId){
        this.date = date
        this.reason = reason
        this.notes = notes
        this.healthcareProviderId = healthcareProviderId
    }

    static createWithDefaults(){
        return new Visit('','','',null)
    }

    static fromJson(json){
        return new Visit(
            json.date,
            json.reason,
            json.notes,
            json.healthcareProviderId
        )
    }

    isUpcoming(){
        const today = new Date()
        const apptDate = new Date(this.date)

        return today < apptDate
    }
}

export default Visit