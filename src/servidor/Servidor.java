package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import idls.ClienteIDL;
import idls.ServidorIDL;

public class Servidor implements ServidorIDL{	

	private ClienteIDL cliente1 = null;
	private ClienteIDL cliente2 = null;
	
	public void enviarMenssagem(String menssagem, ClienteIDL cliente) throws RemoteException{

		if(cliente1 != null && cliente2 != null) {
			if(cliente.equals(cliente1)) {
				cliente2.receberMenssagem(menssagem);
			}else if(cliente.equals(cliente2)) {
				cliente1.receberMenssagem(menssagem);
			}
			
		}
		
	}

	@Override
	public void registrarCliente(ClienteIDL cliente) throws RemoteException {
		if(cliente1 != null) {
			cliente2 = cliente;
		}else {
			cliente1 = cliente;
		}
		
	}
	
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname", "192.168.5.106");
			
			Servidor servidor = new Servidor();
			
			ServidorIDL stub = (ServidorIDL)UnicastRemoteObject.exportObject(servidor, 0);
			
			Registry registro = LocateRegistry.createRegistry(12345);
			registro.bind("chat", stub);
			
			
			System.out.println("Servidor ON the line!");
		} catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
		}
	}
}
