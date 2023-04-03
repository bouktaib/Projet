package modeles;

import java.util.ArrayList;
import java.util.List;

public class Credit {

    private int id;
    private String type;
    private List<Client> clients;



    public Credit() {
        clients = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return type;
    }


}
