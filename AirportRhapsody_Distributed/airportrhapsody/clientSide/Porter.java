package airportrhapsody.clientSide;

import airportrhapsody.serverSide.Logger.*;
import airportrhapsody.serverSide.ArrivalLounge.*;
import airportrhapsody.serverSide.CollectionPoint.*;
import airportrhapsody.serverSide.TempStorageArea.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import airportrhapsody.LoggerStub;
import airportrhapsody.comInf.*;

public class Porter extends Thread{
    /**
     * Porter internal state enumerate
     */
    public enum InternalState {
        WAITING_FOR_A_PLANE_TO_LAND,
        AT_THE_PLANES_HOLD,
        AT_THE_LUGGAGE_BELT_CONVEYOR,
        AT_THE_STOREROOM
    }

    /**
     * Arrival lounge
     * 
     * @serialField arrivalLounge
     */
    private ArrivalLoungeStub arrivalLounge;
    /**
     * Temporary storage area
     * 
     * @serialField tempStorageArea
     */
    private TempStorageAreaStub tempStorageArea;
    /**
     * Baggage collection point
     * 
     * @serialField collPoint
     */
    private CollectionPointStub collPoint;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private LoggerStub generalRepo;
    /**
     *  Porter identification
     * 
     *  @serialField porterID
     */
    private int porterID;
     /**
     *  Porter state
     * 
     *  @serialField passengerState
     */
    private InternalState porterState;
    
    private ArrTransQuayStub arrTransQuay;

    /**
     * Instantiating the porter thread.
     * @param id porter identification
     * @param arrivalLounge Arrival Lounge
     * @param tempStorageArea Temporary storage area
     * @param collPoint Baggage collection point
     * @param generalRepo general repository of information
     */
    public Porter(int id, ArrivalLoungeStub arrivalLounge, TempStorageAreaStub tempStorageArea, CollectionPointStub collPoint, ArrTransQuayStub arrTransQuay, LoggerStub generalRepo){
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
        this.porterID = id;
        this.arrivalLounge = arrivalLounge;
        this.tempStorageArea = tempStorageArea;
        this.collPoint = collPoint;
        this.arrTransQuay = arrTransQuay;
        this.generalRepo = generalRepo;
        this.generalRepo.setStatPorter("WPTL");
        this.generalRepo.setCb(this.collPoint.size());
        this.generalRepo.setSr(this.tempStorageArea.size());
        try{
            String s = "\n\n\nNEW EXECUTION #########################\n";
            Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            e.printStackTrace();
        }
     }

     /**
     * Porter thread life cycle
     */
    @Override
    public void run(){
        // System.out.println("RUN 0");
        // try{
        //     String s = "RUN 0 ";
        //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        // }
        // catch(IOException e){
        //     e.printStackTrace();
        // }
        //this.arrivalLounge.restPorter();
        // System.out.println("RUN 1");
        // try{
        //     String s = "RUN 1 ";
        //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        // }
        // catch(IOException e){
        //     e.printStackTrace();
        // }
        while (!this.arrivalLounge.takeARest()) {
            // System.out.println("RUN 2");
            // try{
            //     String s = "RUN 2 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            Luggage l = this.arrivalLounge.tryToCollectABag();
            // System.out.println("RUN 3");
            // try{
            //     String s = "RUN 3 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.setPorterState(InternalState.AT_THE_PLANES_HOLD);
            // System.out.println("RUN 4");
            // try{
            //     String s = "RUN 4 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.generalRepo.setStatPorter("APLH");
            // System.out.println("RUN 5");
            // try{
            //     String s = "RUN 5 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.generalRepo.write(false);
            // System.out.println("RUN 6");
            // try{
            //     String s = "RUN 6 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            while(l != null){
                // System.out.println("RUN 7");
                // try{
                //     String s = "RUN 7 ";
                //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
                // }
                // catch(IOException e){
                //     e.printStackTrace();
                // }
                this.carryItToAppropriateStore(l);
                // System.out.println("RUN 8");
                // try{
                //     String s = "RUN 8 ";
                //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
                // }
                // catch(IOException e){
                //     e.printStackTrace();
                // }
                l = this.arrivalLounge.tryToCollectABag();
            }
            // System.out.println("RUN 9");
            // try{
            //     String s = "RUN 9 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.setPorterState(this.collPoint.noMoreBagsToCollect());
            // System.out.println("RUN 10");
            // try{
            //     String s = "RUN 10 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.generalRepo.setStatPorter("WPTL");
            // System.out.println("RUN 11");
            // try{
            //     String s = "RUN 11 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.generalRepo.write(false);
            // System.out.println("RUN 12");   
            // try{
            //     String s = "RUN 12 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
        }
        this.shutdownServers();
        try{
            String s = " Porter end\n";
            Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

     /**
     * Shutdown servers
     */
    private void shutdownServers(){
        try{
            String s = "\nWAIT FOR PASSENGER";
            Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        while(!this.arrivalLounge.getPassEnd());
        // arrivalLounge.shutdown();
        // arrivalLounge.setEnd(true);
        collPoint.shutdown();
        tempStorageArea.shutdown();
        arrivalLounge.setPrtEnd(true);
        try{
            String s = "\nColl point end, TempStore end";
            Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Carry luggage to the approriate store
     * @param l bag to carry
     */
    private void carryItToAppropriateStore(Luggage l) {
        this.generalRepo.setBn(this.arrivalLounge.size());
        // try{
        //     String s = "RUN 7.1 ";
        //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        // }
        // catch(IOException e){
        //     e.printStackTrace();
        // }
        if(l.getSi() == Passenger.Situation.FDT){
            // try{
            //     String s = "RUN 7.2 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.collPoint.insertBag(l);
            this.setPorterState(InternalState.AT_THE_LUGGAGE_BELT_CONVEYOR);
            this.generalRepo.setStatPorter("ALCB");
            this.generalRepo.setCb(this.collPoint.size());
        }
        else{
            // try{
            //     String s = "RUN 7.3 ";
            //     Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
            // }
            // catch(IOException e){
            //     e.printStackTrace();
            // }
            this.tempStorageArea.insertBag(l);
            this.setPorterState(InternalState.AT_THE_STOREROOM);
            this.generalRepo.setStatPorter("ASTR");
            this.generalRepo.setSr(this.tempStorageArea.size());
        }
        this.generalRepo.write(false);
    }

    public InternalState getPorterState() {
        return this.porterState;
    }

    public void setPorterState(InternalState state) {
        this.porterState = state;
    }

}
