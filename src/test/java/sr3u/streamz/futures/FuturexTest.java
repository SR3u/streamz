package sr3u.streamz.futures;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FuturexTest {

    @Test
    public void getOptionalSilent0() {
        Integer value = 11;
        Assert.assertEquals(value, futurex(value).getOptionalSilent().orElseThrow());
    }

    @Test
    public void getOptionalSilent1() {
        Integer value = 11;
        Assert.assertEquals(value, futurex(value).getOptionalSilent(10, TimeUnit.SECONDS).orElseThrow());
    }

    @Test
    public void getOptional0() throws ExecutionException, InterruptedException {
        Integer value = 11;
        Assert.assertEquals(value, futurex(value).getOptional().orElseThrow());
    }

    @Test
    public void getOptional1() throws ExecutionException, InterruptedException, TimeoutException {
        Integer value = 11;
        Assert.assertEquals(value, futurex(value).getOptional(10, TimeUnit.SECONDS).orElseThrow());
    }

    @Test
    public void get0() throws ExecutionException, InterruptedException {
        Integer value = 11;
        Assert.assertEquals(value, futurex(value).get());
    }

    @Test
    public void get1() throws ExecutionException, InterruptedException, TimeoutException {
        Integer value = 11;
        Assert.assertEquals(value, futurex(value).get(10, TimeUnit.SECONDS));
    }

    @Test
    public void map0() throws ExecutionException, InterruptedException, TimeoutException {
        Assert.assertEquals(Integer.valueOf(130), futurex(13).map(x -> x * 10)
                .get(10, TimeUnit.SECONDS));
    }

    @Test
    public void map1() throws ExecutionException, InterruptedException, TimeoutException {
        Assert.assertEquals(Integer.valueOf(130), futurex(13)
                .mapWithTimeout(x -> x * 10, 10, TimeUnit.SECONDS)
                .get(10, TimeUnit.SECONDS));
    }

    private Futurex<Integer> futurex(int contents) {
        return Futurex.withFuture(CompletableFuture.completedFuture(contents));
    }

}