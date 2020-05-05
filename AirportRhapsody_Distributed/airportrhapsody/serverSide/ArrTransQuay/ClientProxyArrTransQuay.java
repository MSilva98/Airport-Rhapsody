package airportrhapsody.serverSide.ArrTransQuay;

import airportrhapsody.comInf.MessageArrTransQuay;
import airportrhapsody.comInf.MessageException;
import airportrhapsody.serverSide.ServerCom;

/**
 * Este tipo de dados define o thread agente prestador de serviço para uma
 * solução do Problema dos Barbeiros Sonolentos que implementa o modelo
 * cliente-servidor de tipo 2 (replicação do servidor) com lançamento estático
 * dos threads barbeiro. A comunicação baseia-se em passagem de mensagens sobre
 * sockets usando o protocolo TCP.
 */

public class ClientProxyArrTransQuay extends Thread
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

   private ArrTransQuayInterface arrTransQuayInterface;

  /**
   *  Instanciação do interface à barbearia.
   *
   *    @param sconi canal de comunicação
   *    @param bShopInter interface à barbearia
   */

   public ClientProxyArrTransQuay (ServerCom sconi, ArrTransQuayInterface arrTransQuayInterface)
   {
      super ("Proxy_" + getProxyId ());

      this.sconi = sconi;
      this.arrTransQuayInterface = arrTransQuayInterface;
   }

  /**
   *  Ciclo de vida do thread agente prestador de serviço.
   */

   @Override
   public void run ()
   {
      MessageArrTransQuay inMessage = null,                                      // mensagem de entrada
              outMessage = null;                      // mensagem de saída

      inMessage = (MessageArrTransQuay) sconi.readObject ();                     // ler pedido do cliente
      try
      { outMessage = arrTransQuayInterface.processAndReply (inMessage);         // processá-lo
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
      Class<airportrhapsody.serverSide.ArrTransQuay.ClientProxyArrTransQuay> cl = null;             // representação do tipo de dados ClientProxy na máquina
                                                           //   virtual de Java
      int proxyId;                                         // identificador da instanciação

      try
      { cl = (Class<airportrhapsody.serverSide.ArrTransQuay.ClientProxyArrTransQuay>) Class.forName ("airportrhapsody.serverSide.ArrTransQuay.ClientProxyArrTransQuay");
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
}
