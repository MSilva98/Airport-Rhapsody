package airportrhapsody.serverSide.ArrTransQuay;

import airportrhapsody.serverSide.ServerCom;

import java.net.SocketTimeoutException;

import airportrhapsody.LoggerStub;

public class ServerArrTransQuay {
/**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4002;

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


      ArrTransQuay arrTransQuay;                          // barbearia (representa o serviço a ser prestado)
      ArrTransQuayInterface arrTransQuayInterface;                      // interface à barbearia
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyArrTransQuay cliProxy;                                // thread agente prestador do serviço
      LoggerStub generalRepo = new LoggerStub("localhost", 4008);
     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      arrTransQuay = new ArrTransQuay(nPassengers, nSeatingPlaces, generalRepo);
      arrTransQuayInterface = new ArrTransQuayInterface(arrTransQuay);
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */
      waitConn = true;
      while (waitConn)
      { 
        try{
        sconi = scon.accept ();                            // entrada em processo de escuta
        cliProxy = new ClientProxyArrTransQuay(sconi, arrTransQuayInterface);    // lançamento do agente prestador do serviço
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