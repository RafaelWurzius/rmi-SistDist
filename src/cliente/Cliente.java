package cliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import idls.ClienteIDL;
import idls.ServidorIDL;

public class Cliente extends UnicastRemoteObject implements ClienteIDL{
	
	public Cliente() throws RemoteException{
		
	}
	
	public void receberMenssagem(String menssagem) throws RemoteException{
		System.out.println("Ele(a): " + menssagem);
	}

	public static void main(String[] args) {
		try {
			Registry registro = LocateRegistry.getRegistry("192.168.5.106", 12345);
			ServidorIDL servidor= (ServidorIDL)registro.lookup("chat");
			
			Cliente cliente = new Cliente();

			servidor.registrarCliente(cliente);
			Scanner scan = new Scanner(System.in);
			
			for(;;) {
				System.out.print("Eu: ");
				String menssagem = scan.nextLine();
				servidor.enviarMenssagem(menssagem, cliente);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no cliente");
			e.printStackTrace();
		}
	}
}
