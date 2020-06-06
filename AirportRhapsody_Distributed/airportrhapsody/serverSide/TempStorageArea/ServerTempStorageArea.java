package airportrhapsody.serverSide.TempStorageArea;

import java.net.SocketTimeoutException;

import airportrhapsody.serverSide.ServerCom;

public class ServerTempStorageArea {
/**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4007;

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


      TempStorageArea tempStorageArea;
      TempStorageAreaInterface tempStorageAreaInterface;
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyTempStorageArea cliProxy;                                // thread agente prestador do serviço

     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      tempStorageArea = new TempStorageArea(nPassengers*maxBags);
      tempStorageAreaInterface = new TempStorageAreaInterface(tempStorageArea);
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */
   
      waitConn = true;
      while (waitConn)
      { 
         try
         {
            sconi = scon.accept ();                            // entrada em processo de escuta
            cliProxy = new ClientProxyTempStorageArea(sconi, tempStorageAreaInterface);   // lançamento do agente prestador do serviço
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