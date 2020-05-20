package airportrhapsody.serverSide.CollectionPoint;

import airportrhapsody.comInf.MessageCollectionPoint;
import airportrhapsody.comInf.MessageException;
import airportrhapsody.serverSide.ServerCom;

/**
 * Este tipo de dados define o thread agente prestador de serviço para uma
 * solução do Problema dos Barbeiros Sonolentos que implementa o modelo
 * cliente-servidor de tipo 2 (replicação do servidor) com lançamento estático
 * dos threads barbeiro. A comunicação baseia-se em passagem de mensagens sobre
 * sockets usando o protocolo TCP.
 */

public class ClientProxyCollectionPoint extends Thread
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

   private CollectionPointInterface collectionPointInterface;

  /**
   *  Instanciação do interface à barbearia.
   *
   *    @param sconi canal de comunicação
   *    @param bShopInter interface à barbearia
   */

   public ClientProxyCollectionPoint (ServerCom sconi, CollectionPointInterface collectionPointInterface)
   {
      super ("Proxy_" + getProxyId ());

      this.sconi = sconi;
      this.collectionPointInterface = collectionPointInterface;
   }

  /**
   *  Ciclo de vida do thread agente prestador de serviço.
   */

   @Override
   public void run ()
   {
      MessageCollectionPoint inMessage = null,                                      // mensagem de entrada
              outMessage = null;                      // mensagem de saída

      inMessage = (MessageCollectionPoint) sconi.readObject ();                     // ler pedido do cliente
      try
      { outMessage = collectionPointInterface.processAndReply (inMessage);         // processá-lo
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
      Class<airportrhapsody.serverSide.CollectionPoint.ClientProxyCollectionPoint> cl = null;             // representação do tipo de dados ClientProxy na máquina
                                                           //   virtual de Java
      int proxyId;                                         // identificador da instanciação

      try
      { cl = (Class<airportrhapsody.serverSide.CollectionPoint.ClientProxyCollectionPoint>) Class.forName ("airportrhapsody.serverSide.CollectionPoint.ClientProxyCollectionPoint");
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
