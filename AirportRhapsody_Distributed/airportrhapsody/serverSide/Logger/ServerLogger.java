package airportrhapsody.serverSide.Logger;

import airportrhapsody.serverSide.ServerCom;

public class ServerLogger {
    /**
     *  Número do port de escuta do serviço a ser prestado (4000, por defeito)
     *
     *    @serialField portNumb
     */

    private static final int portNumb = 4008;

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


        Logger logger;                          // barbearia (representa o serviço a ser prestado)
        LoggerInterface loggerInterface;
        ServerCom scon, sconi;                               // canais de comunicação
        ClientProxyLogger cliProxy;                                // thread agente prestador do serviço

        /* estabelecimento do servico */

        scon = new ServerCom (portNumb);                     // criação do canal de escuta e sua associação
        scon.start ();                                       // com o endereço público
        logger = new Logger(nSeatingPlaces, nPassengers, "log.txt");
        loggerInterface = new LoggerInterface(logger);        // activação do interface com o serviço
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConn = true;
        while (waitConn)
        {   
            sconi = scon.accept ();                            // entrada em processo de escuta
            cliProxy = new ClientProxyLogger(sconi, loggerInterface);    // lançamento do agente prestador do serviço
            cliProxy.start ();
        }
    }
}