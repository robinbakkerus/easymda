/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jrb.blazeds.grpc;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jrb.grpc.blazeds.GetKlantbeeldGrpc;
import jrb.grpc.blazeds.KlantbeeldMsg;
import jrb.grpc.blazeds.SelectKlantbeeldMsg;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class KlantbeeldClient {
  private static final Logger logger = Logger.getLogger(KlantbeeldClient.class.getName());

  private final ManagedChannel channel;
  private final GetKlantbeeldGrpc.GetKlantbeeldBlockingStub blockingStub;

  /** Construct client connecting to HelloWorld server at {@code host:port}. */
  public KlantbeeldClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        .usePlaintext()
        .build());
  }

  /** Construct client for accessing HelloWorld server using the existing channel. */
  KlantbeeldClient(ManagedChannel channel) {
    this.channel = channel;
    blockingStub = GetKlantbeeldGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /** Get klantbeeld. */
  public void greet(String name) {
    logger.info("Will try to greet " + name + " ...");
    SelectKlantbeeldMsg request = SelectKlantbeeldMsg.newBuilder().setAchternaam("test").build();
    KlantbeeldMsg response;
    try {
      response = blockingStub.getKlantbeeld(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    logger.info("Response ontvangen achternaam = " + response.getAchternaam());
  }

  /**
   * Greet server. If provided, the first element of {@code args} is the name to use in the
   * greeting.
   */
  public static void main(String[] args) throws Exception {
    KlantbeeldClient client = new KlantbeeldClient("localhost", 50051);
    try {
      /* Access a service running on the local machine on port 50051 */
      String user = "world";
      if (args.length > 0) {
        user = args[0]; /* Use the arg as the name to greet if provided */
      }
      client.greet(user);
    } finally {
      client.shutdown();
    }
  }
}
