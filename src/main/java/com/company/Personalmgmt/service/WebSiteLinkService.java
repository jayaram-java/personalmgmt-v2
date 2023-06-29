package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.WebSiteLinkDetailsDto;
import com.company.Personalmgmt.model.WebSiteLinkDetails;

public interface WebSiteLinkService {

	List<WebSiteLinkDetailsDto> getAllWebSiteLinks();

	public boolean saveWebLinkDetails(WebSiteLinkDetails webSiteLinkDetails);

	public WebSiteLinkDetails retrieveLastData();

}
