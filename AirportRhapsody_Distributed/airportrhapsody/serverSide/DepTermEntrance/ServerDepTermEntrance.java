package airportrhapsody.serverSide.DepTermEntrance;

import java.net.SocketTimeoutException;

import airportrhapsody.LoggerStub;
import airportrhapsody.clientSide.ArrTermExitStub;
import airportrhapsody.serverSide.ServerCom;

public class ServerDepTermEntrance {
/**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4004;

  public static boolean waitConn;
  /**
   *  Programa principal.
   */

   public static void main (String [] args)
   {
       int nPassengers = 6 ;                               // number of passengers
       int nPlaneLandings = 5;                             // number of plane landings
       int nSeatingPlaces = 3;                             // bus capacity
       int maxBags = 2;                                    // maximum luggage
       String serverHostName = "localhost";
      DepTermEntrance depTermEntrance;
      DepTermEntranceInterface depTermEntranceInterface;
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyDepTermEntrance cliProxy;                                // thread agente prestador do serviço
      LoggerStub generalRepo;
      ArrTermExitStub arrTermExit;

      generalRepo = new LoggerStub(serverHostName, 4008);
      arrTermExit = new ArrTermExitStub(serverHostName, 4001);
     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      depTermEntrance = new DepTermEntrance(nPassengers, arrTermExit, generalRepo);;
      depTermEntranceInterface = new DepTermEntranceInterface(depTermEntrance);
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */

      waitConn = true;
      while (waitConn)
      { 
        try{
        sconi = scon.accept ();                            // entrada em processo de escuta
        cliProxy = new ClientProxyDepTermEntrance (sconi, depTermEntranceInterface);    // lançamento do agente prestador do serviço
        cliProxy.start ();
        }
        catch (SocketTimeoutException e)
        { 
        }
        System.out.println(waitConn);
      }
      scon.end ();
       System.out.println("O servidor foi desativado"); 
   }
}