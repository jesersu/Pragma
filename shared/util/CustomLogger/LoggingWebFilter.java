package shared.util.CustomLogger;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class LoggingWebFilter implements WebFilter {

    private static final AppLogger log = AppLogger.getLogger(LoggingWebFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long start = System.currentTimeMillis();
        return chain.filter(exchange)
                .doOnSubscribe(s -> log.info("Request: " + exchange.getRequest().getURI()))
                .doOnSuccess(aVoid -> {
                    long duration = System.currentTimeMillis() - start;
                    log.info("Response: " + exchange.getResponse().getStatusCode()
                            + " (" + duration + "ms)");
                })
                .doOnError(error -> log.error("Error handling request", error));
    }
}
