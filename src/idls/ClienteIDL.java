package idls;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteIDL extends Remote{

	public void receberMenssagem(String menssagem) throws RemoteException;
}
