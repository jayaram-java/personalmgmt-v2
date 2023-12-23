package com.company.Personalmgmt.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.company.Personalmgmt.model.CheckListCategory;
import com.company.Personalmgmt.model.DepositDetails;
import com.company.Personalmgmt.model.ExpenseCategory;
import com.company.Personalmgmt.model.KeyNotesCategory;
import com.company.Personalmgmt.model.MonthMaster;
import com.company.Personalmgmt.model.PropertyCategory;
import com.company.Personalmgmt.model.QuotesDetail;
import com.company.Personalmgmt.model.StatusMaster;
import com.company.Personalmgmt.model.YearMaster;
import com.company.Personalmgmt.repository.CheckListCategoryRepository;
import com.company.Personalmgmt.repository.DepositDetailsRepository;
import com.company.Personalmgmt.repository.ExpenseCategoryRepository;
import com.company.Personalmgmt.repository.KeyNotesCategoryRepository;
import com.company.Personalmgmt.repository.MonthMasterRepository;
import com.company.Personalmgmt.repository.PropertyCategoryRepository;
import com.company.Personalmgmt.repository.QuotesDetailRepository;
import com.company.Personalmgmt.repository.StatusMasterRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.repository.YearMasterRepository;
import com.company.Personalmgmt.serviceimpl.LoginServiceImpl;

@Controller
public class NavigationController {

	@Autowired
	HttpSession httpsession;

	@Autowired
	PropertyCategoryRepository propertyCategoryRepository;

	@Autowired
	QuotesDetailRepository quotesDetailRepository;

	@Autowired
	ExpenseCategoryRepository expenseCategoryRepository;

	@Autowired
	MonthMasterRepository monthMasterRepository;

	@Autowired
	YearMasterRepository yearMasterRepository;

	@Autowired
	CheckListCategoryRepository checkListCategoryRepository;

	@Autowired
	KeyNotesCategoryRepository keyNotesCategoryRepository;

	@Autowired
	StatusMasterRepository statusMasterRepository;
	
	@Autowired
	DepositDetailsRepository depositDetailsRepository;

	@Autowired
	UserRepository userRepository;

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@RequestMapping(value = { "/", "/home" })
	public String home() {

		log.info("jsp page = employeeLogin");

		return "employeeLogin";

	}

	@RequestMapping(value = { "/task" })
	public String employee() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/employee";
	}

	@RequestMapping(value = { "/timeDifference" })
	public String timeDifference() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/timeDifference";
	}

	@RequestMapping(value = { "/basicCalculator" })
	public String basicCalculator() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/BasicCalculator";
	}

	@RequestMapping(value = { "/conversion" })
	public String converter() {
		/*
		 * if (httpsession.getAttribute("username") == null) {
		 * 
		 * return "redirect:/"; }
		 */
		return "user/conversion";
	}

	@RequestMapping(value = { "/discountCalc" })
	public String discountCalc() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/discountCalc";
	}

	@RequestMapping(value = { "/user" })
	public String user() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user";
	}

	@RequestMapping(value = { "/expenseCategory" })
	public String expenseCategory() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "expenseCategory";
	}

	@RequestMapping(value = { "/userProfile" })
	public String userProfile() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/userProfile";
	}

	@RequestMapping(value = { "/expense" })
	public String expense() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/expense";
	}

	@RequestMapping(value = { "/priority" })
	public String priority() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/priority";
	}

	@RequestMapping(value = { "/admin" })
	public String adminlogin() {

		log.info("jsp page = adminlogin");

		return "adminLogin";
	}

	@RequestMapping(value = { "/testing" })
	public ModelAndView test() {

		return new ModelAndView("user/test");
	}

	@RequestMapping(value = { "/websitelink" })
	public String webSiteLink() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/webSiteLink";
	}

	@RequestMapping(value = { "/developing" })
	public String developing() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/developing";
	}

	@RequestMapping(value = { "/checklist" })
	public ModelAndView checkList() {

		long userId = (Long) httpsession.getAttribute("userId");

		List<StatusMaster> statusMaster = statusMasterRepository.findAll();

		ModelAndView resulttologin = new ModelAndView("user/checkList");

		ModelAndView result = new ModelAndView("user/checkList");

		List<CheckListCategory> checkListCategory = checkListCategoryRepository.findByStatusAndUserId("active", userId);

		result.addObject("checkListCategory", checkListCategory);

		result.addObject("statusMaster", statusMaster);

		if (httpsession.getAttribute("username") == null) {

			return resulttologin;
		}

		return result;
	}

	@RequestMapping(value = { "/dashboard" })
	public String dashboard() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/dashboard";
	}

	@RequestMapping(value = { "/property" })
	public ModelAndView property() {

		List<PropertyCategory> propertyCategory = propertyCategoryRepository.findAll();

		ModelAndView result = new ModelAndView("user/property");

		result.addObject("propertycategory", propertyCategory);

		// log.info("property page");

		return result;
	}

	@RequestMapping(value = { "/quotes" })
	public ModelAndView quotes() {

		List<QuotesDetail> QuotesDetail = quotesDetailRepository.findAll();

		ModelAndView result = new ModelAndView("user/quotes");

		result.addObject("quotesHead", "Courage");

		result.addObject("quote", QuotesDetail);

		// log.info("quotes page");

		return result;
	}

	@RequestMapping(value = { "/quotesClickView" })
	public ModelAndView quotesClickView() {

		List<QuotesDetail> QuotesDetail = quotesDetailRepository.findAll();

		ModelAndView result = new ModelAndView("user/quotesClickView");

		result.addObject("quotesHead", "Courage");

		result.addObject("quote", QuotesDetail);

		// log.info("quotes click view page");

		return result;
	}

	@RequestMapping(value = { "/multiLanguage" })
	public String multiLanguage() {

		if (httpsession.getAttribute("username") == null) {
			return "redirect:/";
		}
		return "user/multiLanguage";
	}

	@RequestMapping(value = { "/personalinfo" })
	public String personalInfo() {

		if (httpsession.getAttribute("username") == null) {
			return "redirect:/";
		}

		return "user/personalInfo";
	}

	@RequestMapping(value = { "/goback" })
	public String goBack() {

		if (httpsession.getAttribute("username") == null) {
			return "redirect:/";
		}

		return "user/goBack";
	}

	@RequestMapping(value = { "/codeConversion" })
	public String codeConversion() {

		if (httpsession.getAttribute("username") == null) {
			return "redirect:/";
		}

		return "user/encodeDecode";
	}

	@RequestMapping(value = { "/neighbourinfo" })
	public String neighbourInfo() {

		if (httpsession.getAttribute("username") == null) {
			return "redirect:/";
		}

		return "user/neighbourInfo";
	}

	@RequestMapping(value = { "/interLinkSoftware" })
	public String interLinkSoftware() {

		if (httpsession.getAttribute("username") == null) {
			return "redirect:/";
		}

		return "user/interLinkSoftware";
	}

	@RequestMapping(value = { "/nxtcompany" })
	public String nxtcompany() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/NextCompanyInfo";
	}

	@RequestMapping(value = { "/filesmgmt" })
	public String filesMgmt() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/fileMgmt";
	}

	@RequestMapping(value = { "/moveforward" })
	public String moveForward() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/moveForward";
	}

	@RequestMapping(value = { "/expensereport" })
	public ModelAndView expenseReport() {

		List<ExpenseCategory> expenseCategory = expenseCategoryRepository.findAll();

		List<MonthMaster> monthMaster = monthMasterRepository.findAll();

		ModelAndView result = new ModelAndView("user/expenseReport");

		result.addObject("expenseCategory", expenseCategory);

		result.addObject("monthMaster", monthMaster);

		List<YearMaster> yearMaster = yearMasterRepository.findAll();

		result.addObject("yearMaster", yearMaster);

		/*
		 * if (httpsession.getAttribute("username") == null) {
		 * 
		 * return "redirect:/"; }
		 */

		return result;
	}

	@RequestMapping(value = { "/expensereportviaprice" })
	public ModelAndView expenseReportBasedonPrice() {

		List<ExpenseCategory> expenseCategory = expenseCategoryRepository.findAll();
		List<MonthMaster> monthMaster = monthMasterRepository.findAll();

		ModelAndView result = new ModelAndView("user/expenseReportBasedonPrice");
		result.addObject("expenseCategory", expenseCategory);
		result.addObject("monthMaster", monthMaster);
		List<YearMaster> yearMaster = yearMasterRepository.findAll();
		result.addObject("yearMaster", yearMaster);

		return result;
	}

	@RequestMapping(value = { "/last7day" })
	public String graphLastSeven() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/graphLastSeven";
	}

	@RequestMapping(value = { "/graphActualAmt" })
	public ModelAndView graphActualAmt() {

		ModelAndView result = new ModelAndView("user/graphActualAmt");

		List<YearMaster> yearMaster = yearMasterRepository.findAll();

		result.addObject("yearMaster", yearMaster);

		if (httpsession.getAttribute("username") == null) {

			return new ModelAndView("employeeLogin");
		}

		return result;
	}

	@RequestMapping(value = { "/graphExpenseCategory" })
	public String graphExpenseCategory() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/graphExpenseCategory";
	}

	@RequestMapping(value = { "/graphPaymentMode" })
	public ModelAndView graphPaymentMode() {

		ModelAndView result = new ModelAndView("user/graphPaymentMode");

		List<YearMaster> yearMaster = yearMasterRepository.findAll();

		result.addObject("yearMaster", yearMaster);

		if (httpsession.getAttribute("username") == null) {

			return new ModelAndView("employeeLogin");
		}

		return result;
	}

	@RequestMapping(value = { "/graphAvgAmt" })
	public ModelAndView graphAvgAmt() {

		ModelAndView result = new ModelAndView("user/graphAvgAmt");

		List<YearMaster> yearMaster = yearMasterRepository.findAll();

		result.addObject("yearMaster", yearMaster);

		if (httpsession.getAttribute("username") == null) {

			return new ModelAndView("employeeLogin");
		}

		return result;
	}

	@RequestMapping(value = { "/graphFinancialYear" })
	public ModelAndView graphFinancialYear() {

		ModelAndView result = new ModelAndView("user/graphFinancialYear");

		List<YearMaster> yearMaster = yearMasterRepository.findAll();

		result.addObject("yearMaster", yearMaster);

		if (httpsession.getAttribute("username") == null) {

			return new ModelAndView("employeeLogin");
		}

		return result;
	}

	@RequestMapping(value = { "/graphBalanceSheet" })
	public ModelAndView graphBalanceSheet() {

		ModelAndView result = new ModelAndView("user/graphBalanceSheet");

		List<YearMaster> yearMaster = yearMasterRepository.findAll();

		result.addObject("yearMaster", yearMaster);

		if (httpsession.getAttribute("username") == null) {

			return new ModelAndView("employeeLogin");
		}

		return result;
	}

	@RequestMapping(value = { "/timesheet" })
	public ModelAndView timesheet() {

		ModelAndView result = new ModelAndView("user/timeSheet");

		if (httpsession.getAttribute("username") == null) {

			return new ModelAndView("employeeLogin");
		}

		return result;
	}

	@RequestMapping(value = { "/expenseReportPagination" })
	public String expenseReportPagination() {

		if (httpsession.getAttribute("username") == null) {

			return "redirect:/";
		}

		return "user/expenseReportPagination";
	}

	@RequestMapping(value = { "/product" })
	public String product() {

		return "user/product";
	}

	@RequestMapping(value = { "/keyNotes" })
	public ModelAndView keyNotes() {

		long userId = (Long) httpsession.getAttribute("userId");
		ModelAndView resulttologin = new ModelAndView("user/employeeLogin");

		ModelAndView result = new ModelAndView("user/keyNotes");

		List<KeyNotesCategory> keyNotesCategory = keyNotesCategoryRepository.findByStatusAndUserId("active", userId);

		Comparator<KeyNotesCategory> com = new Comparator<KeyNotesCategory>() {

			@Override
			public int compare(KeyNotesCategory o1, KeyNotesCategory o2) {

				if (o1.getSequenceOrder() > o2.getSequenceOrder()) {
					return 1;
				} else {
					return -1;
				}
			}

		};

		Collections.sort(keyNotesCategory, com);

		result.addObject("keyNotesCategory", keyNotesCategory);

		if (httpsession.getAttribute("username") == null) {

			return resulttologin;
		}

		return result;
	}
	
	@RequestMapping(value = { "/deposits" })
	public ModelAndView depositDetails() {

		ModelAndView resulttologin = new ModelAndView("user/employeeLogin");

		ModelAndView result = new ModelAndView("user/depositDetails");

		List<YearMaster> yearMaster = yearMasterRepository.findAll();

		result.addObject("yearMaster", yearMaster);

		List<DepositDetails> depositDetails = depositDetailsRepository.findDistinctByBankNameIsNotNull();
		
		List<String> response =  depositDetails.stream().map(DepositDetails :: getBankName).distinct().collect(Collectors.toList());
		
		result.addObject("bankNames", response);

		if (httpsession.getAttribute("username") == null) {

			return resulttologin;
		}

		return result;
	}

}
