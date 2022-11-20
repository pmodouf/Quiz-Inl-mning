package client;

import gamepackage.GamePackage;

public class ClientProtocol {
    Client client;

    public ClientProtocol(Client client){
        this.client = client;

    }

    public void  update(){
        client.gp.setMessage("Hej det här är ifrån klienten. ");
        System.out.println(client.gp);

    }

}
