package airportrhapsody.serverSide.ArrTermExit;

import airportrhapsody.serverSide.ServerCom;

public class ServerArrTermExit {
 /**
   *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
   *
   *    @serialField portNumb
   */

  private static final int portNumb = 4001;

  /**
   *  Programa principal.
   */

   public static void main (String [] args)
   {
       int nPassengers = 6 ;                               // number of passengers
       int nPlaneLandings = 5;                             // number of plane landings
       int nSeatingPlaces = 3;                             // bus capacity
       int maxBags = 2;                                    // maximum luggage


      ArrTermExit arrTermExit;                          // barbearia (representa o serviço a ser prestado)
      ArrTermExitInterface arrTermExitInterface;
      ServerCom scon, sconi;                               // canais de comunicação
      ClientProxyArrTermExit cliProxy;                                // thread agente prestador do serviço

     /* estabelecimento do servico */

      scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
      scon.start ();                                       // com o endereço público
      arrTermExit = new ArrTermExit(nPassengers, nPlaneLandings);
      arrTermExitInterface = new ArrTermExitInterface(arrTermExit);        // activação do interface com o serviço
      System.out.println("O serviço foi estabelecido!");
      System.out.println("O servidor esta em escuta.");

     /* processamento de pedidos */

      while (true)
      { sconi = scon.accept ();                            // entrada em processo de escuta
        cliProxy = new ClientProxyArrTermExit (sconi, arrTermExitInterface);    // lançamento do agente prestador do serviço
        cliProxy.start ();
      }
   }
    
}