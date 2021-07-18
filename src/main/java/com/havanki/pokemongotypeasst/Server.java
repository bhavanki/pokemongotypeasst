package com.havanki.pokemongotypeasst;

import static spark.Spark.*;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import java.io.FileReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

  private static final Logger LOG = LoggerFactory.getLogger(Server.class);

  private static final FancyNLP nlp = new FancyNLP();

  public static void main(String[] args) throws Exception {
    Properties props = new Properties();
    try (FileReader r = new FileReader(args[0])) {
      props.load(r);
    }

    String keystorePath = props.getProperty("keystorePath");
    String keystorePassword = props.getProperty("keystorePassword");
    secure(keystorePath, keystorePassword, null, null);

    post("/pokemongotypeasst", (req, res) -> {
      String smsBody = req.queryParams("Body");
      LOG.debug("Client IP: {}", req.ip());
      LOG.debug("Received: {}", smsBody);
      String smsResponse = nlp.process(smsBody);
      LOG.debug("Response: {}", smsResponse);
      res.type("application/xml");
      Body body = new Body.Builder(smsResponse)
        .build();
      Message sms = new Message.Builder()
        .body(body)
        .build();
      MessagingResponse twiml = new MessagingResponse.Builder()
        .message(sms)
        .build();
      return twiml.toXml();
    });
  }
}
