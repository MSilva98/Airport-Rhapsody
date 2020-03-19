package airportrhapsody.sharedRegions;

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
   *  Operação down.
   */

   public synchronized void down ()
   {
     if (val == 0)
        { numbBlockThreads += 1;
          try
          { wait ();
          }
          catch (InterruptedException e) {}
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
          notify ();
        }
        else val += 1;
   }
}
