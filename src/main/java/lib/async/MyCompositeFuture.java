package lib.async;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.impl.future.CompositeFutureImpl;

import java.util.List;

// TODO: javadoc
public interface MyCompositeFuture extends CompositeFuture {

    static <T> CompositeFuture join(List<Future<T>> futures) {
        return CompositeFutureImpl.join(futures.toArray(new Future[0]));
    }

    static <T> CompositeFuture all(List<Future<T>> futures) {
        return CompositeFutureImpl.all(futures.toArray(new Future[futures.size()]));
    }

    static <T1, T2> CompositeFuture all(Future<T1> f1, Future<T2> f2) {
        return CompositeFutureImpl.all(f1, f2);
    }
}
