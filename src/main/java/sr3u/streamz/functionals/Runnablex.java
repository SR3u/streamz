package sr3u.streamz.functionals;

import sr3u.streamz.common.ExceptionCreator;

public interface Runnablex {
    void run() throws Exception;

    default Runnable wrap() {
        return () -> {
            try {
                run();
            } catch (Exception e) {
                throw ExceptionCreator.createException(e);
            }
        };
    }
}
