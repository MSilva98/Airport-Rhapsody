package airportrhapsody;


/**
 * LoggerTest
 */
public class LoggerTest {

    public static void main(String[] args) {
        Logger l = new Logger(3,6,"log.txt");
        l.setBn(123);
        l.setCb(456);
        l.setSr(10);
        l.setFn(412);
        l.setNa(0, 1);
        l.setNa(1, 3);
        l.setNa(2, 0);
        l.setNa(3, 4);
        l.setNa(4, 2);
        l.setNa(5, 1);
        l.setNr(0, 1);
        l.setNr(1, 1);
        l.setNr(2, 0);
        l.setNr(3, 2);
        l.setNr(4, 2);
        l.setNr(5, 1);
        l.setStatDriver("OLAAAAAAAA");
        l.setStatPorter("TEST");
        l.setQ(0,"0");
        l.setQ(1,"1");
        l.setQ(2,"2");
        l.setQ(3,"3");
        l.setQ(4,"2");
        l.setQ(5,"1");
        l.setS(0,"0");
        l.setS(1,"1");
        l.setS(2,"2");
        l.setSi(0,"TRT");
        l.setSi(1,"TRT");
        l.setSi(2,"TRT");
        l.setSi(3,"FDT");
        l.setSi(4,"FDT");
        l.setSi(5,"FDT");
        l.setSt(0,"P1");
        l.setSt(1,"P2");
        l.setSt(2,"P3");
        l.setSt(3,"P4");
        l.setSt(4,"P5");
        l.setSt(5,"P6");
        System.out.println(l.toString());

        System.out.println(l.finalRep());
        l.write(false);
        l.write(false);
        l.write(false);
        l.write(false);
        l.write(true);
    }
}