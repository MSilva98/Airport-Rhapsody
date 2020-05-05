package airportrhapsody.serverSide.DepTermEntrance;

import airportrhapsody.serverSide.ServerCom;

public class ServerDepTermEntrance {
/**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4004;

  /**
   *  Programa principal.
   */

   public static void main (String [] args)
   {
       int nPassengers = 6 ;                               // number of passengers
       int nPlaneLandings = 5;                             // number of plane landings
       int nSeatingPlaces = 3;                             // bus capacity
       int maxBags = 2;                                    // maximum luggage

      DepTermEntrance depTermEntrance;
      DepTermEntranceInterface depTermEntranceInterface;
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyDepTermEntrance cliProxy;                                // thread agente prestador do serviço

     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      depTermEntrance = new DepTermEntrance(nPassengers);
      depTermEntranceInterface = new DepTermEntranceInterface(depTermEntrance);
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */

      while (true)
      { sconi = scon.accept ();                            // entrada em processo de escuta
        cliProxy = new ClientProxyDepTermEntrance (sconi, depTermEntranceInterface);    // lançamento do agente prestador do serviço
        cliProxy.start ();
      }
   }
}