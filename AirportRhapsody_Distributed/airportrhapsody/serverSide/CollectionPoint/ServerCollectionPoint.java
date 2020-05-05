package airportrhapsody.serverSide.CollectionPoint;

import airportrhapsody.serverSide.ServerCom;

public class ServerCollectionPoint {
/**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4003;

  /**
   *  Programa principal.
   */

   public static void main (String [] args)
   {
       int nPassengers = 6 ;                               // number of passengers
       int nPlaneLandings = 5;                             // number of plane landings
       int nSeatingPlaces = 3;                             // bus capacity
       int maxBags = 2;                                    // maximum luggage


      CollectionPoint collPoint;
      CollectionPointInterface collPointInterface;
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyCollectionPoint cliProxy;                                // thread agente prestador do serviço

     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      collPoint = new CollectionPoint(nPassengers*maxBags, nPassengers);
      collPointInterface = new CollectionPointInterface(collPoint);        // activação do interface com o serviço
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */

      while (true)
      { sconi = scon.accept ();                            // entrada em processo de escuta
        cliProxy = new ClientProxyCollectionPoint(sconi, collPointInterface);    // lançamento do agente prestador do serviço
        cliProxy.start ();
      }
   }
}