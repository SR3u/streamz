package sr3u.streamex.common;

import sr3u.streamex.exceptions.StreamexProcessingRuntimeException;

public class ExceptionCreator {
    public static RuntimeException createException(Exception e) {
        return new StreamexProcessingRuntimeException(e);
    }
}
