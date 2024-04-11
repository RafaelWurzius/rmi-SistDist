package idls;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorIDL extends Remote{
	public void enviarMenssagem(String menssagem, ClienteIDL cliente) throws RemoteException;
	public void registrarCliente(ClienteIDL cliente) throws RemoteException;

}
