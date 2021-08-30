package asadal.jaeyoung.persistances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sample {
	protected static final Logger logger = LoggerFactory.getLogger(Sample.class);
	protected static final Logger fileLogger = LoggerFactory.getLogger("fileLogger");

	
	public static void main(String[] args) {

		logger.info("Sample.class logger print with info level");

		fileLogger.info("fileLogger logger print with info level");
	}

}
