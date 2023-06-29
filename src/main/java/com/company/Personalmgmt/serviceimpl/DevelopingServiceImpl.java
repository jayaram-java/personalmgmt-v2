package com.company.Personalmgmt.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.company.Personalmgmt.dto.DevelopingDto;
import com.company.Personalmgmt.dto.PropertyDto;
import com.company.Personalmgmt.model.PresentNeighbor;
import com.company.Personalmgmt.model.QuotesDetail;
import com.company.Personalmgmt.repository.PresentNeighborRepository;
import com.company.Personalmgmt.repository.QuotesDetailRepository;
import com.company.Personalmgmt.service.DevelopingService;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

@Service
public class DevelopingServiceImpl implements DevelopingService {

	@Autowired
	QuotesDetailRepository quotesDetailRepository;

	@Autowired
	PresentNeighborRepository presentNeighborRepository;

	private RestTemplate restTemplate;

	private HttpHeaders httpHeaders;

	private static final String username = "jayaramp51096@gmail.com";
	private static final String password = "i02Cl6Hsy2s3mmThmgsd155A";
	private static final String jiraBaseURL = "https://person-tracking.atlassian.net/rest/api/3/issue/";

	public boolean createHeadersWithAuthentication() {

		createIssue("PT", "create inventory", "descrip", "Story");

		createHeadersWithAuthenticationy();

		return false;
	}

	public ResponseEntity createIssue(String key, String summary, String description, String issueType) {

		String createIssueJSON = createCreateIssueJSON(key, summary, description, issueType);

		String url = jiraBaseURL;

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<String>(createIssueJSON, httpHeaders);

		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

	}

	private HttpHeaders createHeadersWithAuthenticationy() {
		String plainCreds = username + ":" + password;
		// byte[] base64CredsBytes =
		// Base64.getEncoder().encode(plainCreds.getBytes());
		// String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		// headers.add("Authorization", "Basic " + base64Creds);

		return headers;
	}

	private String createCreateIssueJSON(String key, String summary, String description, String issueType) {
		String createIssueJSON = "{\"fields\":{\"project\":{\"key\":\"$KEY\"},\"summary\":\"$SUMMARY\",\"description\":\"$DESCRIPTION\",\"issuetype\": {\"name\": \"$ISSUETYPE\"}}}";

		createIssueJSON = createIssueJSON.replace("$KEY", key);
		createIssueJSON = createIssueJSON.replace("$SUMMARY", summary);
		createIssueJSON = createIssueJSON.replace("$DESCRIPTION", description);
		return createIssueJSON.replace("$ISSUETYPE", issueType);
	}

	public List<DevelopingDto> datafill() {

		List<DevelopingDto> developingDtos = new ArrayList<DevelopingDto>();

		DevelopingDto developingDto = new DevelopingDto();

		developingDto.setSummary("set timer ");

		developingDtos.add(developingDto);

		DevelopingDto developingDto1 = new DevelopingDto();

		developingDto1.setSummary("GMT type");

		developingDtos.add(developingDto1);

		return developingDtos;
	}

	public boolean createIssueAtJira() {

		List<DevelopingDto> obj = datafill();

		for (DevelopingDto ob : obj) {

			String s1 = "\"";

			String summary = s1.concat(ob.getSummary()).concat(s1);

			String assignto = s1.concat("60810844cbff1b007015b070").concat(s1);

			String Uname = "jayaramp51096@gmail.com";

			String Password = "i02Cl6Hsy2s3mmThmgsd155A";

			Client client = Client.create();
			WebResource webResource = client.resource("https://person-tracking.atlassian.net/rest/api/3/issue/");

			String data = "{\"fields\":{\"project\":{\"key\":\"GROC\"},\"summary\":" + summary
					+ ",\"issuetype\":{\"name\":\"Story\"},\"assignee\": {\"id\":" + assignto + "}}}";

			// String data =
			// "{\"fields\":{\"project\":{\"key\":\"PT\"},\"summary\":\"create
			// customer
			// details\",\"issuetype\":{\"name\":\"Story\"},\"assignee\":
			// {\"name\": \"jeyaramp51096\"}}}";

			String auth = new String(Base64.encode(Uname + ":" + Password));

			ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
					.accept("application/json").post(ClientResponse.class, data);
			int statusCode = response.getStatus();

			if (statusCode == 401) {
				// throw new AuthenticationException("Invalid Username or
				// Password");
			} else if (statusCode == 403) {
				// throw new AuthenticationException("Forbidden");
			} else if (statusCode == 200 || statusCode == 201) {
				System.out.println("Ticket Create succesfully");
			} else {
				System.out.print("Http Error : " + statusCode);
			}

		}

		return true;
	}

	public boolean savePropertyDetails(PropertyDto propertyDto) {

		System.out.println(propertyDto.getDescription());

		QuotesDetail quotesDetail = new QuotesDetail();

		quotesDetail.setName(propertyDto.getDescription());

		quotesDetailRepository.save(quotesDetail);

		return false;
	}

	public boolean checkingJPARepository() {

		List<PresentNeighbor> presentNeighbors = presentNeighborRepository.findAll();

		for (PresentNeighbor presentNeighbor : presentNeighbors) {
			System.out.println(presentNeighbor.getName() + " checking");
		}

		return true;
	}

	public boolean completableFutureJava8() {
		
		Runnable runnable = new Runnable(){

			public void run() {
				
				checkingMethod();
			}

		
			
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		
		service.scheduleAtFixedRate(runnable, 5, 60, TimeUnit.SECONDS);
		
		return true;
	}
	
	private void checkingMethod() {
		
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy  'at'  hh:mm a");

		LocalDateTime localDateTime = LocalDateTime.now();
		
		CompletableFuture<Void> ob1 = CompletableFuture.runAsync(()->{
			
			ExecutorService executor = Executors.newFixedThreadPool(3);
			
			System.out.println(FOMATTER.format(localDateTime));
			
		});
		
		
	}

	public static void main(String[] args) {
		
		DevelopingServiceImpl ob = new DevelopingServiceImpl();
		
		ob.completableFutureJava8();
	}

}
