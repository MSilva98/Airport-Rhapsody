package airportrhapsody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Logger
 * 
 * PLANE      PORTER                    DRIVER
 * FN BN    Stat CB SR      Stat Q1 Q2 Q3 Q4 Q5 Q6 S1 S2 S3
 * 
 *                      PASSENGERS
 * St1 Si1 NR1 NA1 St2 Si2 NR2 NA2 St3 Si3 NR3 NA3 St4 Si4 NR4 NA4 St5 Si5 NR5 NA5 St6 Si6 NR6 NA6
 * ## ## #### ## ## #### # # # # # # # # #
 * ### ### # # ### ### # # ### ### # # ### ### # # ### ### # # ### ### # #
 * 
 * Legend:
 * FN – flight number
 * BN - number of pieces of luggage presently at the plane's hold
 * Stat – state of the porter
 * CB - number of pieces of luggage presently on the conveyor belt
 * SR - number of pieces of luggage belonging to passengers in transit presently stored at the storeroom
 * Stat – state of the driver
 * Q# - occupation state for the waiting queue (passenger id / - (empty))
 * S# - occupation state for seat in the bus (passenger id / - (empty))
 * St# - state of passenger # (# - 0 .. 5)
 * Si# - situation of passenger # (# - 0 .. 5) – TRT (in transit) / FDT (has this airport as her final destination)
 * NR# - number of pieces of luggage the passenger # (# - 0 .. 5) carried at the start of her journey
 * NA# - number of pieces of luggage the passenger # (# - 0 .. 5) she has presently collected
 *  
 * Final report
 * N. of passengers which have this airport as their final destination = ##
 * N. of passengers in transit = ##
 * N. of bags that should have been transported in the the planes hold = ##
 * N. of bags that were lost = ##
 */

public class Logger {


    private int fn;
    private int bn;
    private String statPorter;
    private int cb;
    private int sr;
    private String statDriver;
    private String[] q;
    private String[] s;
    private String[] st;
    private String[] si;
    private int[] nr;
    private int[] na;
    private int numP_FDT;
    private int numP_TRT;
    private int numTotalBags;
    private int numLostBags;
    private File log;
    
    public Logger(int numSeats, int numP, String filename){
        this.q = new String[numP];
        this.s = new String[numSeats];
        this.st = new String[numP];
        this.si = new String[numP];
        this.nr = new int[numP];
        this.na = new int[numP];
        this.numP_FDT = 0;
        this.numP_TRT = 0;
        this.numTotalBags = 0;
        this.numLostBags = 0;
        this.log = new File(filename);
        try {
            Files.deleteIfExists(log.toPath());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    // Flight number
    public void setFn(int fn) {
        this.fn = fn;
    }

    // Number of pieces fo luggage presently at the plane's hold
    public void setBn(int bn) {
        this.bn = bn;
    }

    // State of the porter
    public void setStatPorter(String statPorter) {
        this.statPorter = statPorter;
    }

    // Number of pieces of luggage presently on the conveyor belt
    public void setCb(int cb) {
        this.cb = cb;
    }

    // Number of pieces of luggage belonging to passengers in transit presently stored at the storeroom
    public void setSr(int sr) {
        this.sr = sr;
    }

    // State of the bus driver
    public void setStatDriver(String statDriver) {
        this.statDriver = statDriver;
    }

    // Occupation state for the waiting queue
    public void setQ(int id, String state) {
        this.q[id] = state;
    }

    // Occupation state for the seat in the bus
    public void setS(int id, String state) {
        this.s[id] = state;
    }

    // State of the passengers
    public void setSt(int id, String state) {
        this.st[id] = state;
    }

    // Situation of the passengers
    public void setSi(int id, String state) {
        this.si[id] = state;
        if(state == "TRT"){
            this.numP_TRT++;
        }
        else{
            this.numP_FDT++;
        }
    }

    // Number of pieces of luggage each passenger carried at the start ot his journey
    public void setNr(int id, int n) {
        this.nr[id] = n;
        this.numTotalBags += n;
    }

    // Number of pieces of luggage each passenger has collected
    public void setNa(int id, int n) {
        this.na[id] = n;
        this.numLostBags = this.numTotalBags - n;
    }

    // Final report
    public String finalRep(){
        return  "\n\nFinal Report" +
                "\nN. of passengers which have this airport as their final destination = " + this.numP_FDT + 
                "\nN. of passengers in transit = " + this.numP_TRT +
                "\nN. of bags that should have been transported in the planes hold = " + this.numTotalBags +
                "\nN- of bags that were lost = " + this.numLostBags;
    }

    @Override
    public String toString() {
        return String.format("PLANE         PORTER                  DRIVER" +
                             "\n%2d %2d   %s %2d %2d     %s %s %s %s %s %s %s   %s %s %s" +
                             "\n                    PASSENGER           " +  
                             "\nSt1 Si1 NR1 NA1   St2 Si2 NR2 NA2   St3 Si3 NR3 NA3  St4 Si4 NR4 NA4  St5 Si5 NR5 NA5  St6 Si6 NR6 NA6" +
                             "\n%s  %3s %1d   %1d     %s %3s  %1d   %1d     %s %3s  %1d   %1d    %s %3s  %1d   %1d    %s %3s  %1d   %1d    %s %3s  %1d   %1d", 
        this.fn, this.bn, this.statPorter, this.cb, this.sr, this.statDriver, 
        this.q[0], this.q[1], this.q[2], this.q[3], this.q[4], this.q[5],
        this.s[0], this.s[1], this.s[2],
        this.st[0], this.si[0], this.nr[0], this.na[0],
        this.st[1], this.si[1], this.nr[1], this.na[1],
        this.st[2], this.si[2], this.nr[2], this.na[2],
        this.st[3], this.si[3], this.nr[3], this.na[3],
        this.st[4], this.si[4], this.nr[4], this.na[4],
        this.st[5], this.si[5], this.nr[5], this.na[5]);
    }

    public void write(boolean end){
        String first = ("PLANE         PORTER                  DRIVER                     PASSENGER" +
        "\nFN  BN   Stat CB SR     Stat Q1 Q2 Q3 Q4 Q5 Q6  S1 S2 S3    St1 Si1 NR1 NA1   St2 Si2 NR2 NA2   St3 Si3 NR3 NA3  St4 Si4 NR4 NA4  St5 Si5 NR5 NA5  St6 Si6 NR6 NA6");
        String s = String.format("\n%2d %2d   %s %2d %2d     %s %s %s %s %s %s %s   %s %s %s    %s  %3s %1d   %1d     %s %3s  %1d   %1d     %s %3s  %1d   %1d    %s %3s  %1d   %1d    %s %3s  %1d   %1d    %s %3s  %1d   %1d", 
        this.fn, this.bn, this.statPorter, this.cb, this.sr, this.statDriver, 
        this.q[0], this.q[1], this.q[2], this.q[3], this.q[4], this.q[5],
        this.s[0], this.s[1], this.s[2],
        this.st[0], this.si[0], this.nr[0], this.na[0],
        this.st[1], this.si[1], this.nr[1], this.na[1],
        this.st[2], this.si[2], this.nr[2], this.na[2],
        this.st[3], this.si[3], this.nr[3], this.na[3],
        this.st[4], this.si[4], this.nr[4], this.na[4],
        this.st[5], this.si[5], this.nr[5], this.na[5]);

        try {
            FileWriter fw;
            if(log.createNewFile()){
                fw = new FileWriter(log);
                fw.write(first + s);
                fw.close();
            }
            else{
                fw = new FileWriter(log, true);
                if(end){
                    fw.append(this.finalRep());
                }
                else{
                    fw.append(s);
                }
                fw.close();
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}