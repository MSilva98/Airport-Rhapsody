package airportrhapsody.serverSide.Logger;

import airportrhapsody.comInf.MessageException;
import airportrhapsody.comInf.MessageLogger;

public class LoggerInterface {
    /**
     * Logger
     * @serialField logger
     */
    private Logger logger;

    /**
     * Instantiate the Logger interface
     * @param logger Logger
     */
    public LoggerInterface(Logger logger){
        this.logger = logger;
    }

    public MessageLogger processAndReply (MessageLogger inMessage) throws MessageException
    {
        MessageLogger outMessage = null;
        
        /* validate message */
        switch (inMessage.getType()){
            case MessageLogger.SFN:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Flight number missing", inMessage);
                }
                break;
            case MessageLogger.SBN:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Bags at plane's hold number missing", inMessage);
                }
                break;
            case MessageLogger.STP:
                if(inMessage.getGenStr() == null){
                    throw new MessageException("Porter state(string) missing", inMessage);
                }
                break;
            case MessageLogger.SCB:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Conveyor belt bags number missing", inMessage);
                }
                break;
            case MessageLogger.SSR:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Transit bags number missing", inMessage);
                }
                break;
            case MessageLogger.SSD:
                if(inMessage.getGenStr() == null){
                    throw new MessageException("Driver state(string) missing", inMessage);
                }
                break;
            case MessageLogger.SQ:
                if(inMessage.getGenStr() == null){
                    throw new MessageException("Queue occupation state missing", inMessage);
                }
                break;
            case MessageLogger.SQE:
                break;
            case MessageLogger.SS:
                if(inMessage.getGenStr() == null){
                    throw new MessageException("Seat occupation state missing", inMessage);
                }
                break;
            case MessageLogger.SSE:
                break;
            case MessageLogger.SST:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Passenger id missing", inMessage);
                }
                if(inMessage.getGenStr() == null){
                    throw new MessageException("Passenger state(string) missing", inMessage);
                }
                break;
            case MessageLogger.SSI:
                if(inMessage.getGenInt()== -1){
                    throw new MessageException("Passenger id missing", inMessage);
                }
                if(inMessage.getGenStr() == null){
                    throw new MessageException("Passenger situation(string) missing", inMessage);
                }
                break;
            case MessageLogger.SNR:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger id missing", inMessage);
                }
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Carried bags at the begin number missing", inMessage);
                }
                break;
            case MessageLogger.SNA:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger id missing", inMessage);
                }
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Collected bags number missing", inMessage);
                }
                break;
            case MessageLogger.SM:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Missing bags number missing", inMessage);
                }
                break;
            case MessageLogger.CLRV:
                break;
            case MessageLogger.WRT:
                break;
            default:    throw new MessageException("Invalide type", inMessage);
        }

        /* process message */
        switch (inMessage.getType()){
            case MessageLogger.SFN:
                logger.setFn(inMessage.getGenInt());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SBN:
                logger.setBn(inMessage.getGenInt());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.STP:
                logger.setStatPorter(inMessage.getGenStr());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SCB:
                logger.setCb(inMessage.getGenInt());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SSR:
                logger.setSr(inMessage.getGenInt());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SSD:
                logger.setStatDriver(inMessage.getGenStr());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SQ:
                logger.setQ(inMessage.getGenInt(), inMessage.getGenStr());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SQE:
                logger.setQEmpty();
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SS:
                logger.setS(inMessage.getGenInt(), inMessage.getGenStr());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SSE:
                logger.setSEmpty();
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SST:
                logger.setSt(inMessage.getGenInt(), inMessage.getGenStr());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SSI:
                logger.setSi(inMessage.getGenInt(), inMessage.getGenStr());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SNR:
                logger.setNr(inMessage.getPassengerID(), inMessage.getGenInt());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SNA:
                logger.setNr(inMessage.getPassengerID(), inMessage.getGenInt());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.SM:
                logger.setMissing(inMessage.getGenInt());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.CLRV:
                logger.clearVars();
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
            case MessageLogger.WRT:
                logger.write(inMessage.getEnd());
                outMessage = new MessageLogger(MessageLogger.ACK);       // gerar resposta
                break;
        }
        return outMessage;
    }
    
}