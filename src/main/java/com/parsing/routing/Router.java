package com.parsing.routing;

import com.imohsenb.ISO8583.exceptions.ISOException;
import com.models.ParsedMessage;
import com.parsing.formatters.Encoder;


import java.io.IOException;

public class Router {

    /*
	Returns the parsedMessage which is given from the hex.
	 */
    public static ParsedMessage getParsedMessage(String hex) throws ISOException {
        Encoder encoder = new Encoder();
        ParsedMessage parsedMessage = encoder.getParsedMessageFromEncodedMessage(hex);
        return parsedMessage;
    }

    /*
    Returns the hex which is given from a parsedMessage.
    */
    public static String getEncodedMessage(ParsedMessage parsedMessage) throws ISOException {
        Encoder encoder = new Encoder();
        String hex = encoder.getEncodedMessageFromParsedMessage(parsedMessage);
        return hex;
    }
}
