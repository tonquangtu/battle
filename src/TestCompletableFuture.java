import java.util.concurrent.CompletableFuture;

public class TestCompletableFuture {

    public void test() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("tu");
        future.thenApply(s -> s + " ton")
                .thenApply(s ->  {
                    return s + " quang";
                });
    }
}
