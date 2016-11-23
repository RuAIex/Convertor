package send;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CurrencyReceiver {

    private final CloseableHttpClient httpClient;

    public CurrencyReceiver() {
        this.httpClient = HttpClientBuilder.create().build();
    }

    public String getCurrency(final String baseCurrency, final String toConvertCurrency) {
        if (baseCurrency.equals(toConvertCurrency))
            return "1";
        final HttpGet httpGet = new HttpGet ("http://api.fixer.io/latest?base=" + baseCurrency);
        String value = "";
        try {
            final HttpEntity entity = this.httpClient.execute(httpGet).getEntity();
            final String jsonBody = EntityUtils.toString(entity);
            final ObjectMapper objectMapper = new ObjectMapper();
            final JsonNode rootNode = objectMapper.readTree(jsonBody);
            value = rootNode.path("rates").path(toConvertCurrency).toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return value;
    }
}
