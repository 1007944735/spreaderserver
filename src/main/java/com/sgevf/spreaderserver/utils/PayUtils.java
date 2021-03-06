package com.sgevf.spreaderserver.utils;

public class PayUtils {
    //支付宝网关
    public static final String URL="https://openapi.alipaydev.com/gateway.do";
    //支付宝支付异步通知地址
    public static final String BACK_URL="http://47.103.8.72:8080/spreader/S0015";
    //APPID
    public static final String APP_ID="2016092800616091";
    //开发者应用私钥
    public static final String APP_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCP9WZbGcTkcRmPES8OJOB3FbW2UvSKTtok98rmwDex3AlWsgxqf28wYq2W3ScE2knK8k1/UAELHXHwt8XbWiI9joQUppqRJetPp875apJVOzz9JEE0dlN7jXRDklHFPptS+qFo+BTkviUYolW5+AVhcva7DddludljZe/Le30n7Kwe2oeYl6g2dzTkZS2V1YZeMjXx2/Ct95DU/c5TNjNprbetilpM11vt2kqqSFfVcnU4Y64TnFwHvTcUPcuHtw5WcH1QVitF2i/L3jIF1v2F2kMp4gn7tz4XjYP6ptKy19FcA7FFmd4m3z6EvSKeE5+V5npWGh4paM+K1PggPF01AgMBAAECggEAdwDFvElxGebk2f+6hla1t7uamz24KR1F94lfkaEUaVMb5S8KVH9DyxvHpA7tUnWgysPCjJv2QNn2dqd0SAAajghh5zBSVeqiuxZdYpFWpl5x7FEsFdI68MfedH1gW02F2PKWu89Ydj85K0QG2dTNneELVVX72Xvo2OJLeEp9C6KzclhHgxoantwA6Tq2FkvWpr695Eevd7uw2m2aUVf3+yUDoI7mts+8QsyeXDjL8dkaZ8QkgJnq0BjY9uJAy8nYrmAsp5JrIicjgXY3noXdpcmUtOxz7lBKBatWRVCtoVeVuCkQmR9chXXsMzh5HLJCiRFq+udTHoHfyPciepdsAQKBgQD0ccCkm0SkDMGX1lhVnBfNNHGVJhU7uO6KJFu7R0mDKL3Us4d/IH7xW6L2FnlV87//oQlo1CZtBcfIfPQ0Pox6KI1nqD65INBuK+bE2kqWVnh28CvKGF6nnv3MulsWdx10UXL9Q67/kymRROs784s3CR+JJLh6nvOp+URtFmHrKQKBgQCWw5N9PxfnUJ1rvEQJ8ivlE+CC/kEYJ3ypcIbo3R0u4Mf5Bu2ARLxGzD043TALz63deA8Pi62tLwCUKwsHavy9pdS+OFnj6MUUcqIGZmGg5H+cwJYmqRwjv9T9VZVvyWNO5311PqD57GIf+aLQ8WWiLu6dqpDmzVhGBxkvBL2vLQKBgQDLkKN+MTOjbmAWA1GC0b/ciwz/yTwrhSFZd1fRCVfF2zrjFDMFgllUxBxc1XBsTNYamtAMNYheiE7Mvopbq68u1/CSX0EEyLfq17KRQryUoCQn4HCHF25UJwpy4MqUVdP4lw+srB3jBnm6WWuQfIv15CoX+rWKoC6I4WXDdr1KqQKBgEOUfYt5+bwNjdMaPCF9NaHk7BGr6P/ry1ykfUTqwMcfqWP+b/28Ue0mZhcURxI0UMpio9WjFgH4fLjuJm53zb0xFZEC74BS+ey1id12jTKy2mUoiuXfjiaUOeQpNeg7DNH0wG8K2NvO0xlkLFCetKEjioCQUY2z5GrXibli1agtAoGBAMYuhxmZVmxBZIeEWVhQMHiFgH8/yrfhl3bpz/aQipYdPs7WzN7zhx9NhR6j7w9FDlkyzOhaJF2TKvA6+0k5d6nW3Z6aQfq33pYoGx4/9JGS4lcjtS8rl2Z67ZzIXElPPaA1cscHwEKuK/YmWrWneLdEQE9BVPRe3L00Ojokgvz3";
    //参数返回格式
    public static final String FORMAT="json";
    //请求签名使用的编码方式
    public static final String CHARSET="UTF-8";
    //支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuHE8Gsg13Oylk+8vupH/+03XgHrsozasQA8RkX/RzBH3o1Tqubeml2s/sCGwuO+7LYr6XSshqE8emlbARFmBsOLoaXx7ZdzKhbv0cNarvCFIIlPnfUe0snKlX+Dt6/ItNhwOlbUKL0CsJboCMTVn/YRggTdv/1hE2W5zaYZZ65T5j955oPs+5PtPCPCR9gK4fGU7uhJReXh8uAHQXRtpxTb6KY9fa4qAui5JRzGZI4Gr4GOO5TXf0/Iy2Z7odO6VCJgtFGjQlO+oxeDNYMT/qanrRiGiWf32X5TDvzcQ4BnGVoiX5f0yOTq1CTKJKq2IC+QlULZ8usOIOwyjFYLblQIDAQAB";
    //签名算法类型
    public static final String SIGN_TYPE="RSA2";
    //销售产品码
    public static final String PRODUCT_CODE="QUICK_MSECURITY_PAY";
    //商家支付宝UID
    public static final String BUSINESS_UID="2088102177656027";
}
