package airportrhapsody.serverSide.DepTransQuay;

import airportrhapsody.serverSide.ServerCom;

public class ServerDepTransQuay {
/**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4005;

  /**
   *  Programa principal.
   */

   public static void main (String [] args)
   {
       int nPassengers = 6 ;                               // number of passengers
       int nPlaneLandings = 5;                             // number of plane landings
       int nSeatingPlaces = 3;                             // bus capacity
       int maxBags = 2;                                    // maximum luggage

      DepTransQuay depTransQuay;
      DepTransQuayInterface depTransQuayInterface;
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyDepTransQuay cliProxy;                                // thread agente prestador do serviço

     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      depTransQuay = new DepTransQuay(nPassengers);
      depTransQuayInterface = new DepTransQuayInterface(depTransQuay);
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */

      while (true)
      { sconi = scon.accept ();                            // entrada em processo de escuta
        cliProxy = new ClientProxyDepTransQuay(sconi, depTransQuayInterface);    // lançamento do agente prestador do serviço
        cliProxy.start ();
      }
   }
}