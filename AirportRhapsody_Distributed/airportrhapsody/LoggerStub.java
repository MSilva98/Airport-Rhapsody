package airportrhapsody;

public class LoggerStub {
         /**
   *  Server host name
   *    @serialField serverHostName
   */

   private String serverHostName = null;

   /**
    *  Server port number
    *    @serialField serverPortNumb
    */
 
    private int serverPortNumb;

    /**
     * Instantiating theDeparture terminal entrance stub.
     */
    public LoggerStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }
}