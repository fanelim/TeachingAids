package sdk.client;

import lombok.Data;

@Data
public class ClientConfig {
    private String prefixUrl;
    /**
     * 一般请求的超时设置.
     */
    private long connectTimeout = 5000;
    /**
     * 一般请求的超时设置.
     */
    private long readTimeout = 30000;
    /**
     * 连接失败是否重试
     */
    private boolean retryOnConnectionFailure = true;
    /**
     * 根据业务方使用情况来设定.
     */
    private int connectionPoolSize = 5;
}
