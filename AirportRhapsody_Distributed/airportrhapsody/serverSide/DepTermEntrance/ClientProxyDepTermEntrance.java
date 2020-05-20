package airportrhapsody.serverSide.DepTermEntrance;

import javax.sql.rowset.serial.SerialArray;

import airportrhapsody.comInf.MessageDepTermEntrance;
import airportrhapsody.comInf.MessageException;
import airportrhapsody.serverSide.ServerCom;

/**
 * Este tipo de dados define o thread agente prestador de serviço para uma
 * solução do Problema dos Barbeiros Sonolentos que implementa o modelo
 * cliente-servidor de tipo 2 (replicação do servidor) com lançamento estático
 * dos threads barbeiro. A comunicação baseia-se em passagem de mensagens sobre
 * sockets usando o protocolo TCP.
 */

public class ClientProxyDepTermEntrance extends Thread
{
  /**
   *  Contador de threads lançados
   *
   *    @serialField nProxy
   */

   private static int nProxy;

  /**
   *  Canal de comunicação
   *
   *    @serialField sconi
   */

   private ServerCom sconi;

  /**
   *  Interface à barbearia
   *
   *    @serialField bShopInter
   */

   private DepTermEntranceInterface depTermEntranceInterface;

  /**
   *  Instanciação do interface à barbearia.
   *
   *    @param sconi canal de comunicação
   *    @param bShopInter interface à barbearia
   */

   public ClientProxyDepTermEntrance (ServerCom sconi, DepTermEntranceInterface depTermEntranceInterface)
   {
      super ("Proxy_" + getProxyId ());

      this.sconi = sconi;
      this.depTermEntranceInterface = depTermEntranceInterface;
   }

  /**
   *  Ciclo de vida do thread agente prestador de serviço.
   */

   @Override
   public void run ()
   {
      MessageDepTermEntrance inMessage = null,                                      // mensagem de entrada
              outMessage = null;                      // mensagem de saída

      inMessage = (MessageDepTermEntrance) sconi.readObject ();                     // ler pedido do cliente
      try
      { outMessage = depTermEntranceInterface.processAndReply (inMessage);         // processá-lo
      }
      catch (MessageException e)
      { System.out.println ("Thread " + getName () + ": " + e.getMessage () + "!");
        System.out.println(e.getMessageVal ().toString ());
        System.exit (1);
      }
      sconi.writeObject (outMessage);                                // enviar resposta ao cliente
      sconi.close ();                                                // fechar canal de comunicação
   }

  /**
   *  Geração do identificador da instanciação.
   *
   *    @return identificador da instanciação
   */

   private static int getProxyId ()
   {
      Class<airportrhapsody.serverSide.DepTermEntrance.ClientProxyDepTermEntrance> cl = null;             // representação do tipo de dados ClientProxy na máquina
                                                           //   virtual de Java
      int proxyId;                                         // identificador da instanciação

      try
      { cl = (Class<airportrhapsody.serverSide.DepTermEntrance.ClientProxyDepTermEntrance>) Class.forName ("airportrhapsody.serverSide.DepTermEntrance.ClientProxyDepTermEntrance");
      }
      catch (ClassNotFoundException e)
      { System.out.println ("O tipo de dados ClientProxy não foi encontrado!");
        e.printStackTrace ();
        System.exit (1);
      }

      synchronized (cl)
      { proxyId = nProxy;
        nProxy += 1;
      }

      return proxyId;
   }

public ServerCom getScon() {
	return this.sconi;
}
}
