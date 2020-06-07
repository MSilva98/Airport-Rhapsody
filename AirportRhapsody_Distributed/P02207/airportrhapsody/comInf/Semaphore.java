package airportrhapsody.comInf;

/**
 *    Este tipo de dados define um semáforo de Dijkstra a partir de um monitor.
 */

public class Semaphore
{
  /**
   *  Indicador de tipo de luz
   *
   *    @serialField val
   */

   private int val = 0;

  /**
   *  Número de threads bloqueados presentemente bloqueados
   *
   *    @serialField numbBlockThreads
   */

   private int numbBlockThreads = 0;

   /**
   *  Estado down
   *
   *    @serialField downTime
   */

  private boolean downTime;

  /**
   *  Operação down.
   */

   public synchronized void down ()
   {
     if (val == 0){ 
        numbBlockThreads += 1;
        try
        { wait ();
        }
        catch (InterruptedException e) {}
        
      }
      else val -= 1;
   }

   /**
   *  Operação down com tempo t em milissegundos.
   * 
   * @param t time
   */

  public synchronized void down (int t)
  {
    if (val == 0)
        { numbBlockThreads += 1;
          downTime = true;
          try
          { wait (t);
          }
          // verificar se o wait terminou por timeout ou por um up passar um parametro no down
          catch (InterruptedException e) {}
          if(downTime){
            numbBlockThreads-=1;
          }
        }
        else val -= 1;
  }

  /**
   *  Operação up.
   */

   public synchronized void up ()
   {
     if (numbBlockThreads != 0)
        { numbBlockThreads -= 1;
          downTime = false;
          notify ();
        }
        else val += 1;
   }
}
