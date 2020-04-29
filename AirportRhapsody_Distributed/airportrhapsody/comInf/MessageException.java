package airportrhapsody.comInf;

/**
 *   Este tipo de dados define uma excepção que é lançada se a mensagem for inválida.
 */

public class MessageException extends Exception
{
  /**
   *  Mensagem que originou a excepção
   *    @serialField msg
   */

   private Object msg;

  /**
   *  Instanciação de uma mensagem.
   *
   *    @param errorMessage texto sinalizando a condição de erro
   *    @param msg mensagem que está na origem da excepção
   */

   public MessageException (String errorMessage, Object msg)
   {
     super (errorMessage);
     this.msg = msg;
   }

  /**
   *  Obtenção da mensagem que originou a excepção.
   *
   *    @return mensagem
   */

   public Object getMessageVal ()
   {
     return (msg);
   }
}
