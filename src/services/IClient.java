
package services;

import modeles.Client;

import java.util.List;

public interface IClient {
       public String genererQunt();

       public boolean addClient(Client clt);

       public boolean updateClient(Client clt);

       public boolean deleteClt(String numero);

       public List<Client> getAllClt();

       public boolean exist(Client clt);
}
