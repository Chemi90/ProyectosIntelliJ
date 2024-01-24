package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI extends UnicastRemoteObject implements RMI {

    public ServerRMI() throws RemoteException{
        super();
    }

    @Override
    public int sumar(int x, int y) throws RemoteException {
        return x + y;
    }

    @Override
    public int resta(int x, int y) throws RemoteException {
        return x - y;
    }

    @Override
    public int multiplica(int x, int y) throws RemoteException {
        return x * y;
    }

    @Override
    public double divide(int x, int y) throws RemoteException {
        if (y != 0) {
            return x / y;
        } else {
            throw new RemoteException("No se puede dividir por cero.");
        }    }

    @Override
    public int porcentaje(int x, int y) throws RemoteException {
        return (x * y) / 100;
    }

    @Override
    public int resto(int x, int y) throws RemoteException {
        if (y != 0) {
            return x % y;
        } else {
            throw new RemoteException("No se puede calcular el resto con cero.");
        }    }

    @Override
    public double media(int[] x) throws RemoteException {
        if (x.length > 0) {
            double suma = 0;
            for (int i = 0; i < x.length; i++) {
                suma += x[i];
            }
            return suma / x.length;
        } else {
            throw new RemoteException("El array de números está vacío.");
        }
    }

    public static void main(String[] args){
        try{
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.rebind("RemotoRMI", new ServerRMI());
            System.out.println("Servidor activo");
        }catch (RemoteException e){
            System.out.println(e.getMessage());
        }
    }
}
