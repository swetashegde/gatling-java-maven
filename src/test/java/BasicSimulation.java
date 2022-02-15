import java.time.Duration;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {
  String login_uri="https://api.demoblaze.com";

  FeederBuilder feeder = csv("data/data.csv").circular();

  HttpProtocolBuilder httpProtocol = http
    .baseUrl(login_uri)
    .acceptHeader("*/*")
    .contentTypeHeader("application/json");

  ScenarioBuilder scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("Homepage")
                  .get("/")
                  .check(status().is(200))
          )
          .pause(1)
          .feed(feeder)
          .exec(
          http("Login to app")
                          .post(login_uri+"/login")
                          .body(ElFileBody("bodies/loginRequestBody.json"))
          .check(status().is(200))
          .check(regex("Auth_token: (.+?)").find().saveAs("AuthToken"))
          )
          .exec(session->{
            System.out.println(session.get("AuthToken").toString());
            return session;
    })
      .pause(1, 20)
      .pause(Duration.ofMillis(1000), Duration.ofMillis(2000));


  {
    setUp(scn.injectOpen(atOnceUsers(1)).protocols(httpProtocol));
  }
}
