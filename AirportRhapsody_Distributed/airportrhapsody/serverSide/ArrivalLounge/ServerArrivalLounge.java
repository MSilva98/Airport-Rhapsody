package airportrhapsody.serverSide.ArrivalLounge;

import airportrhapsody.serverSide.ServerCom;
import airportrhapsody.LoggerStub;

public class ServerArrivalLounge {
    /**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

   private static final int portNumb = 4000;

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


       ArrivalLounge arrivalLounge;                          // barbearia (representa o serviço a ser prestado)
       ArrivalLoungeInterface arrivalLoungeInt;                      // interface à barbearia
       ServerCom scon, sconi;                               // canais de comunicação
       ClientProxyArrivalLounge cliProxy;                                // thread agente prestador do serviço
       LoggerStub generalRepo = new LoggerStub("localhost", 4008);
      /* estabelecimento do servico */
 
       scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
       scon.start ();                                       // com o endereço público
       arrivalLounge = new ArrivalLounge(nPassengers*maxBags, nPassengers, generalRepo);
       arrivalLoungeInt = new ArrivalLoungeInterface (arrivalLounge);        // activação do interface com o serviço
       System.out.println("O serviço foi estabelecido!");
       System.out.println("O servidor esta em escuta.");
 
      /* processamento de pedidos */
       waitConn = true;
       while (waitConn)
       { sconi = scon.accept ();                            // entrada em processo de escuta
         cliProxy = new ClientProxyArrivalLounge (sconi, arrivalLoungeInt);    // lançamento do agente prestador do serviço
         cliProxy.start ();
       }
    }
}