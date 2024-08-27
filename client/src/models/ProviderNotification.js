
class ProviderNotification{
    constructor(id,type,message,createdAt,updatedAt,read) {
        this.id = id
        this.type = type
        this.message = message
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.read = read
    }

    static fromJson(json) {
        return new ProviderNotification(
            json.id,
            json.type,
            json.message,
            json.createdAt,
            json.updatedAt,
            json.read
        );
    }
}

export default ProviderNotification