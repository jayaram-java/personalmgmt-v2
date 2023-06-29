package com.company.Personalmgmt.serviceimpl;

import java.math.BigDecimal;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.company.Personalmgmt.dto.GeneralDto;
import com.company.Personalmgmt.service.RestService;

@Service
public class RestServiceImpl implements RestService {

	/*@Autowired
	RestTemplate restTemplate;*/

	@Override
	public GeneralDto callingMoneyConverter(String currency) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		GeneralDto generalDto = new GeneralDto();
		
		try {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String response = restTemplate
				.exchange("https://api.exchangerate-api.com/v4/latest/USD", HttpMethod.GET, entity, String.class)
				.getBody();
		
		
			JSONObject ob = new JSONObject(response);
			
			JSONObject rates = (JSONObject)ob.get("rates");
			
			System.out.println(rates.get(currency));
			
			double rate = Double.parseDouble(String.valueOf((BigDecimal)rates.get(currency)));
			
			double rateInr = Double.parseDouble(String.valueOf((BigDecimal)rates.get("INR")));
			
			generalDto.setAmount(rate);
			generalDto.setIndAmount(rateInr);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		

		return generalDto;
	}

}
