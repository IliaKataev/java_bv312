import java.sql.ClientInfoStatus;
import java.util.Objects;

class Client {
    private final String clientId;
    private final String fullName;

    public Client(String clientId, String fullName){
        this.clientId = Objects.requireNonNull(clientId);
        this.fullName = Objects.requireNonNull(fullName);
    }

    public String getClientId() {return clientId; }
    public String getFullName() {return fullName; }

    @Override
    public String toString(){
        return fullName + " ("+clientId+")";
    }
}
