package airportrhapsody.serverSide.ArrTermExit;

import java.net.SocketTimeoutException;

import airportrhapsody.LoggerStub;
import airportrhapsody.clientSide.ArrTransQuayStub;
import airportrhapsody.clientSide.ArrivalLoungeStub;
import airportrhapsody.serverSide.ServerCom;

public class ServerArrTermExit {
 /**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4001;

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

      ArrTermExit arrTermExit;                          // barbearia (representa o serviço a ser prestado)
      ArrTermExitInterface arrTermExitInterface;
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyArrTermExit cliProxy;                                // thread agente prestador do serviço
      ArrivalLoungeStub arrivalLounge;
      ArrTransQuayStub arrTransQuay;
      LoggerStub generalRepo;
      generalRepo = new LoggerStub(serverHostName, 4008);
      arrivalLounge = new ArrivalLoungeStub(serverHostName, 4000);
      arrTransQuay = new ArrTransQuayStub(serverHostName, 4002);
     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      arrTermExit = new ArrTermExit(nPassengers, arrivalLounge, arrTransQuay, nPlaneLandings, generalRepo);
      arrTermExitInterface = new ArrTermExitInterface(arrTermExit);        // activação do interface com o serviço
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */
      waitConn = true;
      while (waitConn)
      { 
        try
        {
        sconi = scon.accept ();                            // entrada em processo de escuta
        cliProxy = new ClientProxyArrTermExit (sconi, arrTermExitInterface);    // lançamento do agente prestador do serviço
        cliProxy.start ();
        }
        catch (SocketTimeoutException e)
        { 
        }
      }
      scon.end ();
       System.out.println("O servidor foi desativado"); 
   }
    
}