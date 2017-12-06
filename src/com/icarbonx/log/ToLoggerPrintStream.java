package com.icarbonx.log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;


public class ToLoggerPrintStream {
	private Logger logger;
	private PrintStream myPrintStream;

	public ToLoggerPrintStream(Logger logger) {
	        super();
	        this.logger = logger;
	    }

	public PrintStream getPrintStream() {
        if (myPrintStream == null) {
            OutputStream output = new OutputStream() {
 
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
 
                @Override
                public void write(int b) throws IOException {
 
                    baos.write(b);
 
                }
 
                @Override
                public void flush() {
 
                    String log = this.baos.toString().trim();
 
                    if (!StringUtils.isBlank(log)) {
                        logger.info(log);
                        baos = new ByteArrayOutputStream();
                    }
                }
            };
            myPrintStream = new PrintStream(output, true);
 
        }
        return myPrintStream;
    }
	
	

}
