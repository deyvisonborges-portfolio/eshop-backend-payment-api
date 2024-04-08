import java.util.UUID;

public class BaseModel {
    protected UUID id;
    protected Boolean active;
    protected Instant  createdAt;
    protected Instance updatedAt;

    public BaseModel(UUID id, Boolean active, Instance createdAt, Instance updatedAt) {
        this.id = id != null ? id : UUID.randomUUID();
        this.active = active != null ? active : true;
        this.createdAt = createdAt != null ? createdAt : Instance.now();
        this.updatedAt = updatedAt != null ? updatedAt : Instance.now();
    }

    public UUID getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public Instance getCreatedAt() {
        return createdAt;
    }

    public Instance getUpdatedAt() {
        return updatedAt;
    }
}