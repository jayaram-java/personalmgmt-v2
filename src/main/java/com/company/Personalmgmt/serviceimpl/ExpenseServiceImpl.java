package com.company.Personalmgmt.serviceimpl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.ExpenseDto;
import com.company.Personalmgmt.exception.NoDataFoundException;
import com.company.Personalmgmt.model.ExpenseCategory;
import com.company.Personalmgmt.model.ExpenseDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.ExpenseCategoryRepository;
import com.company.Personalmgmt.repository.ExpenseDetailsRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.ExpenseService;



@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(ExpenseServiceImpl.class);
	
	@Autowired
	ExpenseDetailsRepository expenseDetailsRepository;
	
	@Autowired
	ExpenseCategoryRepository expenseCategoryRepository;
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HttpSession httpsession;


	public boolean saveExpenseDetails(ExpenseDto expenseDto) {

		log.info("API name = *saveExpenseDetails");

		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy  'at'  hh:mm a");

		LocalDateTime localDateTime = LocalDateTime.now();
		
		int year = localDateTime.getYear();
		
		int month = localDateTime.getMonthValue();

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);

		ExpenseCategory expenseCategory = expenseCategoryRepository.findByName(expenseDto.getCategoryName());

		String username = (String) httpsession.getAttribute("username");

		try {

			if (expenseDto.getId() == null) {
				
				List<Object[]>  ob = expenseDetailsRepository.getexpenseId();
				
				String nxtExpenseId = generateExpenseId((String)ob.get(0)[0]); 
					
				ExpenseDetails expenseDetails = new ExpenseDetails();

				expenseDetails.setName(expenseDto.getName());
				expenseDetails.setDescription(expenseDto.getDescription());
				expenseDetails.setAmount(expenseDto.getAmount());
				expenseDetails.setDate(expenseDto.getDate());
				expenseDetails.setCreatedDate(new Date());
				expenseDetails.setCreatedBy(username);
				expenseDetails.setExpenseCategory(expenseCategory);
				String date[] = expenseDto.getDate().split("-");
				expenseDetails.setYear(date[0]);
				expenseDetails.setMonth(monthCaluculator(date[1]));
				expenseDetails.setMonthN(monthCaluculatorN(date[1]));
				expenseDetails.setPaymentMethod(expenseDto.getPaymentMethod());
				expenseDetails.setUser(user.get());
				expenseDetails.setExpenseId(nxtExpenseId);

				expenseDetailsRepository.save(expenseDetails);

			} else {

				ExpenseDetails expenseDetails = expenseDetailsRepository.findById((long) expenseDto.getId());

				expenseDetails.setName(expenseDto.getName());
				expenseDetails.setDescription(expenseDto.getDescription());
				expenseDetails.setAmount(expenseDto.getAmount());
				expenseDetails.setDate(expenseDto.getDate());
				expenseDetails.setModifiedDate(new Date());
				expenseDetails.setModifiedBy(username);
				expenseDetails.setExpenseCategory(expenseCategory);
				String date[] = expenseDto.getDate().split("-");
				expenseDetails.setYear(date[0]);
				expenseDetails.setMonth(monthCaluculator(date[1]));
				expenseDetails.setMonthN(monthCaluculatorN(date[1]));
				expenseDetails.setPaymentMethod(expenseDto.getPaymentMethod());
				expenseDetails.setUser(user.get());

				expenseDetailsRepository.save(expenseDetails);

			}

		} catch (Exception e) {

			log.error("Exception  saveExpenseDetails" + e);
		}

		return true;
	}
	
	private String generateExpenseId(String previousExpId) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yy");
		Supplier<String> s1 = () -> dtf.format(LocalDateTime.now());

		String curDate = s1.get();
		String a[] = curDate.split("-");
		String year = a[2];
		String month = a[1];
		
		//String previousExpId = "";// Fetching from query
		String previousExpCheck = previousExpId.substring(0, 5);
		
		String incrementalId = previousExpId.substring(previousExpId.length() - 4, previousExpId.length());
		int nxtId = Integer.parseInt(incrementalId);
		
		String output = String.format("%04d", nxtId + 1);
		String expIdPrefix = "EXP".concat(year);
		
		String expenseId = "";
		if (expIdPrefix.equals(previousExpCheck)) {
			expenseId = expIdPrefix.concat(month).concat(String.valueOf(output));
		} else {
			expenseId = expIdPrefix.concat(month).concat("0001");
		}
		
		log.info("expenseId = "+expenseId );

		return expenseId;
	}
	
	
	
	
	public static int monthCaluculatorN(String num) {
		
		log.info("API name = *monthCaluculatorN" );


		String month = "";
		
		int monthN = 0;

		if (num.equals("01")) {

			month = "Jan";
			
			monthN = 1;

		} else if (num.equals("02")) {

			month = "Feb";
			
			monthN = 2;

		} else if (num.equals("03")) {

			month = "Mar";
			
			monthN = 3;

		} else if (num.equals("04")) {

			month = "Apr";
			
			monthN = 4;

		} else if (num.equals("05")) {

			month = "May";
			
			monthN = 5;

		} else if (num.equals("06")) {

			month = "Jun";
			
			monthN = 6;

		} else if (num.equals("07")) {

			month = "Jul";
			
			monthN = 7;

		} else if (num.equals("08")) {

			month = "Aug";
			
			monthN = 8;

		} else if (num.equals("09")) {

			month = "Sep";
			
			monthN = 9;

		} else if (num.equals("10")) {

			month = "Oct";
			
			monthN = 10;

		} else if (num.equals("11")) {

			month = "Nov";
			
			monthN = 11;

		} else if (num.equals("12")) {

			month = "Dec";
			
			monthN = 12;
		}
		return monthN;
	}
	
	public static String monthCaluculator(String num) {
		
		log.info("API name = *monthCaluculator" );


		String month = "";

		if (num.equals("01")) {

			month = "Jan";

		} else if (num.equals("02")) {

			month = "Feb";

		} else if (num.equals("03")) {

			month = "Mar";

		} else if (num.equals("04")) {

			month = "Apr";

		} else if (num.equals("05")) {

			month = "May";

		} else if (num.equals("06")) {

			month = "Jun";

		} else if (num.equals("07")) {

			month = "Jul";

		} else if (num.equals("08")) {

			month = "Aug";

		} else if (num.equals("09")) {

			month = "Sep";

		} else if (num.equals("10")) {

			month = "Oct";

		} else if (num.equals("11")) {

			month = "Nov";

		} else if (num.equals("12")) {

			month = "Dec";
			
		}
		return month;
	}	


	public List<ExpenseDto> getAllExpenseDetails() {

		log.info("API name = *getAllExpenseDetails");
		LocalDateTime startTime = LocalDateTime.now();
		
		AtomicInteger counter = new AtomicInteger(1);
		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();
		Date datey = new Date();

		SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat formatz = new SimpleDateFormat("dd-MM-yyyy");
		LocalDate currentdate = LocalDate.now();

		int currentYear = currentdate.getYear();

		String currentDate = formatz.format(datey);
		String datez[] = currentDate.split("-");
		String currentmonth = monthCaluculator(datez[1]);

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);

		try {

			List<ExpenseDetails> expenseDetails = expenseDetailsRepository
					.findByYearAndMonthAndUserIdOrderByIdDesc(String.valueOf(currentYear), currentmonth, user.get().getId());
			
			expenseDetails.stream().forEach(expenseDetail ->{
			
				ExpenseDto expenseDto = new ExpenseDto();

				BeanUtils.copyProperties(expenseDetail, expenseDto);

				expenseDto.setSerialId(counter.getAndIncrement());

				expenseDto.setCategoryName(expenseDetail.getExpenseCategory().getName());

				expenseDto.setCreatedDate(format.format(expenseDetail.getCreatedDate()));

				if (expenseDetail.getModifiedDate() == null) {

				} else {
					expenseDto.setModifiedDate(format.format(expenseDetail.getModifiedDate()));
				}

				String date = expenseDetail.getDate();

				String a[] = date.split("-");

				if (a[1].equals("01")) {
					expenseDto.setMonth("Jan");
				} else if (a[1].equals("02")) {
					expenseDto.setMonth("Feb");

				} else if (a[1].equals("03")) {

					expenseDto.setMonth("Mar");

				} else if (a[1].equals("04")) {

					expenseDto.setMonth("Apr");

				} else if (a[1].equals("05")) {

					expenseDto.setMonth("May");

				} else if (a[1].equals("06")) {

					expenseDto.setMonth("Jun");
				} else if (a[1].equals("07")) {

					expenseDto.setMonth("Jul");

				} else if (a[1].equals("08")) {

					expenseDto.setMonth("Aug");

				} else if (a[1].equals("09")) {

					expenseDto.setMonth("Sep");

				} else if (a[1].equals("10")) {

					expenseDto.setMonth("Oct");

				} else if (a[1].equals("11")) {

					expenseDto.setMonth("Nov");

				} else if (a[1].equals("12")) {

					expenseDto.setMonth("Dec");

				}

				expenseDtos.add(expenseDto);

				
			});
			
			

		} catch (Exception e) {

			log.error("Exception " + e);

		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime,endTime);
			log.info("API | *getAllExpenseDetails | latency = "+latency);
		}
		
		
		return expenseDtos;
	}
	
	
	public List<ExpenseDto> getExpenseDetailBasedpreviousmonth() {

		log.info("API name = *getExpenseDetailBasedpreviousmonth");

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		Date datey = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat formatz = new SimpleDateFormat("dd-MM-yyyy");
		LocalDate currentdate = LocalDate.now();

		int currentYear = currentdate.getYear();
		String currentDate = formatz.format(datey);
		String datez[] = currentDate.split("-");
		int n1 = Integer.parseInt(datez[1]) - 1;
		DecimalFormat dec = new DecimalFormat("00.##");

		LocalDate now = LocalDate.now();
		LocalDate earlier = now.minusMonths(1);

		earlier.getMonth();
		int lastMonth = earlier.getMonth().getValue();
		int year = earlier.getYear();

		String currentmonth = monthCaluculator(dec.format(lastMonth));
		
		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);

		try {

			List<ExpenseDetails> expenseDetails = expenseDetailsRepository
					.findByYearAndMonthAndUserId(String.valueOf(year), currentmonth, user.get().getId());

			System.out.println(expenseDetails);

			int n = 1;

			for (ExpenseDetails expenseDetail : expenseDetails) {

				ExpenseDto expenseDto = new ExpenseDto();

				BeanUtils.copyProperties(expenseDetail, expenseDto);
				expenseDto.setSerialId(n);
				expenseDto.setCategoryName(expenseDetail.getExpenseCategory().getName());
				expenseDto.setCreatedDate(format.format(expenseDetail.getCreatedDate()));
				// expenseDto.setModifiedDate(format.format(expenseDetail.getModifiedDate()));
				String date = expenseDetail.getDate();
				String a[] = date.split("-");

				if (a[1].equals("01")) {
					expenseDto.setMonth("Jan");
				} else if (a[1].equals("02")) {
					expenseDto.setMonth("Feb");

				} else if (a[1].equals("03")) {

					expenseDto.setMonth("Mar");

				} else if (a[1].equals("04")) {

					expenseDto.setMonth("Apr");

				} else if (a[1].equals("05")) {

					expenseDto.setMonth("May");

				} else if (a[1].equals("06")) {

					expenseDto.setMonth("Jun");
				} else if (a[1].equals("07")) {

					expenseDto.setMonth("Jul");

				} else if (a[1].equals("08")) {

					expenseDto.setMonth("Aug");

				} else if (a[1].equals("09")) {

					expenseDto.setMonth("Sep");

				} else if (a[1].equals("10")) {

					expenseDto.setMonth("Oct");

				} else if (a[1].equals("11")) {

					expenseDto.setMonth("Nov");

				} else if (a[1].equals("12")) {

					expenseDto.setMonth("Dec");

				}

				expenseDtos.add(expenseDto);

				n++;
			}

		} catch (Exception e) {

			log.info("Exception " + e);

		} finally {

		}

		return expenseDtos;
	}
	
	@Override
	public List<ExpenseDto> getExpenseDetailBasedpreviousmonthForEmailScheduling(long userid) {

		log.info("API name = *getExpenseDetailBasedpreviousmonth");

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		Date datey = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat formatz = new SimpleDateFormat("dd-MM-yyyy");
		LocalDate currentdate = LocalDate.now();

		int currentYear = currentdate.getYear();
		String currentDate = formatz.format(datey);
		String datez[] = currentDate.split("-");
		int n1 = Integer.parseInt(datez[1]) - 1;
		DecimalFormat dec = new DecimalFormat("00.##");

		LocalDate now = LocalDate.now();
		LocalDate earlier = now.minusMonths(1);

		earlier.getMonth();
		int lastMonth = earlier.getMonth().getValue();
		int year = earlier.getYear();

		String currentmonth = monthCaluculator(dec.format(lastMonth));

		Optional<User> user = userRepository.findById(userid);

		try {

			List<ExpenseDetails> expenseDetails = expenseDetailsRepository
					.findByYearAndMonthAndUserId(String.valueOf(year), currentmonth, user.get().getId());

			System.out.println(expenseDetails);

			int n = 1;

			for (ExpenseDetails expenseDetail : expenseDetails) {

				ExpenseDto expenseDto = new ExpenseDto();

				BeanUtils.copyProperties(expenseDetail, expenseDto);
				expenseDto.setSerialId(n);
				expenseDto.setCategoryName(expenseDetail.getExpenseCategory().getName());
				expenseDto.setCreatedDate(format.format(expenseDetail.getCreatedDate()));
				// expenseDto.setModifiedDate(format.format(expenseDetail.getModifiedDate()));
				String date = expenseDetail.getDate();
				String a[] = date.split("-");

				if (a[1].equals("01")) {
					expenseDto.setMonth("Jan");
				} else if (a[1].equals("02")) {
					expenseDto.setMonth("Feb");

				} else if (a[1].equals("03")) {

					expenseDto.setMonth("Mar");

				} else if (a[1].equals("04")) {

					expenseDto.setMonth("Apr");

				} else if (a[1].equals("05")) {

					expenseDto.setMonth("May");

				} else if (a[1].equals("06")) {

					expenseDto.setMonth("Jun");
				} else if (a[1].equals("07")) {

					expenseDto.setMonth("Jul");

				} else if (a[1].equals("08")) {

					expenseDto.setMonth("Aug");

				} else if (a[1].equals("09")) {

					expenseDto.setMonth("Sep");

				} else if (a[1].equals("10")) {

					expenseDto.setMonth("Oct");

				} else if (a[1].equals("11")) {

					expenseDto.setMonth("Nov");

				} else if (a[1].equals("12")) {

					expenseDto.setMonth("Dec");

				}

				expenseDtos.add(expenseDto);

				n++;
			}

		} catch (Exception e) {

			log.info("Exception " + e);

		} finally {

		}

		return expenseDtos;
	}
	
	@Override
	public List<ExpenseDto> getExpenseDetailBasedCategoryPreviousMonth() {

		log.info("API name = *getExpenseDetailBasedCategoryPreviousMonth");

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		Date datey = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat formatz = new SimpleDateFormat("dd-MM-yyyy");
		LocalDate currentdate = LocalDate.now();

		int currentYear = currentdate.getYear();
		String currentDate = formatz.format(datey);
		String datez[] = currentDate.split("-");
		int n1 = Integer.parseInt(datez[1]) - 1;
		DecimalFormat dec = new DecimalFormat("00.##");

		LocalDate earlier = currentdate.minusMonths(1);

		int lastMonth = earlier.getMonth().getValue();
		int year = earlier.getYear();

		String currentmonth = monthCaluculator(dec.format(lastMonth));

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);

		try {
			List<Object[]> expenses = expenseDetailsRepository.getexpensedetailsbasedonmonth(currentmonth,
					String.valueOf(year), user.get().getId());

			for (Object[] obj : expenses) {
				ExpenseDto expenseDto = new ExpenseDto();
				expenseDto.setCategoryName((String) obj[0]);
				expenseDto.setAmount((Double) obj[2]);
				expenseDtos.add(expenseDto);
			}
		} catch (Exception e) {
			log.info("Exception " + e);
			e.printStackTrace();

		}
		return expenseDtos;
	}
	
	@Override
	public List<ExpenseDto> getExpenseDetailBasedCategoryPreviousMonthForEmailScheduling(long userid) {
		log.info("API name = *getExpenseDetailBasedCategoryPreviousMonth");

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		Date datey = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat formatz = new SimpleDateFormat("dd-MM-yyyy");
		LocalDate currentdate = LocalDate.now();

		int currentYear = currentdate.getYear();
		String currentDate = formatz.format(datey);
		String datez[] = currentDate.split("-");
		int n1 = Integer.parseInt(datez[1]) - 1;
		DecimalFormat dec = new DecimalFormat("00.##");

		LocalDate earlier = currentdate.minusMonths(1);

		int lastMonth = earlier.getMonth().getValue();
		int year = earlier.getYear();

		String currentmonth = monthCaluculator(dec.format(lastMonth));

		Optional<User> user = userRepository.findById(userid);

		try {
			List<Object[]> expenses = expenseDetailsRepository.getexpensedetailsbasedonmonth(currentmonth,
					String.valueOf(year), user.get().getId());

			for (Object[] obj : expenses) {
				ExpenseDto expenseDto = new ExpenseDto();
				expenseDto.setCategoryName((String) obj[0]);
				expenseDto.setAmount((Double) obj[2]);
				expenseDtos.add(expenseDto);
			}
		} catch (Exception e) {
			log.info("Exception " + e);
			e.printStackTrace();

		}
		return expenseDtos;
	}


	public ExpenseDto getExpenseDetailsById(Long id) {

		log.info("API name = *getExpenseDetailsById");

		ExpenseDto expenseDto = new ExpenseDto();

		try {

			ExpenseDetails expenseDetails = expenseDetailsRepository.findById((long) id);
			BeanUtils.copyProperties(expenseDetails, expenseDto);
			expenseDto.setCategoryName(expenseDetails.getExpenseCategory().getName());

		} catch (Exception e) {

			log.error("Exception " + e);

		}

		return expenseDto;
	}

	public List<ExpenseDto> getExpenseDetailsForGraph() {

		log.info("API name = *getExpenseDetailsForGraph");

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);

		try {

			List<Object[]> expenses = expenseDetailsRepository.getexpensedetails(String.valueOf(currentYear),user.get().getId());
			
		//	List<Object[]> expenses = expenseDetailsRepository.getexpensedetails("2022",user.get().getId());

			expenses.stream().forEach(obj->{
				
				int month = (Integer) obj[0];

				ExpenseDto expenseDto = new ExpenseDto();

				expenseDto.setMonth((String) obj[1]);
				expenseDto.setAmount((Double) obj[2]);
				
				double averageAmt = calculateAverageAmt(month,expenseDto.getAmount());
				
				expenseDto.setAverageAmt(Math.round(averageAmt));
				
				expenseDto.setYear(String.valueOf(currentYear));

				expenseDtos.add(expenseDto);
			});

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return expenseDtos;
	}
	
	@Override
	public List<ExpenseDto> getExpenseDetailsForGraphSearch(String year) {

		log.info("API name = *getExpenseDetailsForGraph");

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);

		try {

			List<Object[]> expenses = expenseDetailsRepository.getexpensedetails(year, user.get().getId());

			expenses.stream().forEach(obj -> {

				int month = (Integer) obj[0];

				ExpenseDto expenseDto = new ExpenseDto();

				expenseDto.setMonth((String) obj[1]);
				expenseDto.setAmount((Double) obj[2]);

				double averageAmt = calculateAverageAmt(month, expenseDto.getAmount());

				expenseDto.setAverageAmt(Math.round(averageAmt));

				expenseDtos.add(expenseDto);
			});

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return expenseDtos;
	}	

	@Override
	public List<ExpenseDto> getFinancialYearExpenseDetailsForGraph() {
		
		log.info("API name = *getFinancialYearExpenseDetailsForGraph");
		
		LocalDateTime startTime = LocalDateTime.now();
		
		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		
		LocalDateTime ob = LocalDateTime.now();


		String startDate = "";
		String endDate = "";


		int yearLatest = ob.getYear();
		int yearPrevious = yearLatest - 1;
		int monthValue = ob.getMonthValue();


		if (monthValue >= 4) {
			startDate = String.valueOf(yearPrevious).concat("-03-31");
			endDate = String.valueOf(yearLatest).concat("-04-01");
		} else {
			startDate = String.valueOf(yearPrevious - 1).concat("-03-31");
			endDate = String.valueOf(yearLatest - 1).concat("-04-01");
		}

		log.info("Starting from "+startDate+" to "+endDate);
		
		try {

			List<Object[]> expenses = expenseDetailsRepository.getExpenseDetailsFy(startDate, endDate, userId);

			expenses.stream().forEach(obj->{

				ExpenseDto expenseDto = new ExpenseDto();

				expenseDto.setMonth((String) obj[1]);
				expenseDto.setAmount((Double) obj[2]);
				expenseDto.setYear(String.valueOf(yearLatest));
				
				expenseDtos.add(expenseDto);
			});
		
		} catch (Exception e) {

			log.error("Exception " + e);

		}finally{
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime,endTime);
			log.info("API | *getFinancialYearExpenseDetailsForGraph | latency = "+latency);
		}
		
		return expenseDtos;
	}
	
	@Override
	public List<ExpenseDto> getFinancialYearExpenseDetailsForGraphFilter(String year) {
		
		LocalDateTime startTime = LocalDateTime.now();
		
		log.info("API name = *getFinancialYearExpenseDetailsForGraph");
		
		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		
		LocalDateTime ob = LocalDateTime.now();


		String startDate = "";
		String endDate = "";


		int yearIn = Integer.valueOf(year);
		int yearPrevious = yearIn - 1;
		int monthValue = ob.getMonthValue();


		if (monthValue >= 4) {

			startDate = String.valueOf(yearPrevious).concat("-03-31");
			endDate = String.valueOf(yearIn).concat("-04-01");

		} else {
			
			startDate = String.valueOf(yearPrevious).concat("-03-31");
			endDate = String.valueOf(yearIn).concat("-04-01");

		}

		
		log.info("Starting from "+startDate+" to "+endDate);
		
		try {

			List<Object[]> expenses = expenseDetailsRepository.getExpenseDetailsFy(startDate, endDate, userId);

			
			expenses.stream().forEach(obj->{

				ExpenseDto expenseDto = new ExpenseDto();

				expenseDto.setMonth((String) obj[1]);
				expenseDto.setAmount((Double) obj[2]);
				
				expenseDtos.add(expenseDto);
			});

		} catch (Exception e) {
			log.error("Exception " + e);
		}finally{
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime,endTime);
			log.info("API | *getFinancialYearExpenseDetailsForGraphFilter | latency = "+latency);
		}
		
		return expenseDtos;
	}

	
	public static double calculateAverageAmt(int month,double amount) {
		
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();
		
		YearMonth yearmonth = YearMonth.of(currentYear,month);
		
		int n = yearmonth.lengthOfMonth();
		
		double averageAmt = amount/n;
		
		
		return averageAmt;
	}
	


	public List<ExpenseDto> getExpenseDetailsForMonthGraph() {

		log.info("API name = *getExpenseDetailsForMonthGraph");

		LocalDate localDate = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String currentDate[] = formatter.format(localDate).split("-");

		String input = monthCaluculator(currentDate[1]);

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		List<ExpenseCategory> expenseCategories = expenseCategoryRepository.findAll();

		for (ExpenseCategory expenseCategory : expenseCategories) {
			ExpenseDto expenseDto = new ExpenseDto();

			expenseDto.setAmount(0.0);

			expenseDtos.add(expenseDto);
		}

		LocalDate currentdate = LocalDate.now();

		int currentYear = currentdate.getYear();

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);

		try {

			List<Object[]> expenses = expenseDetailsRepository.getexpensedetailsbasedonmonth(input,
					String.valueOf(currentYear), user.get().getId());

			for (Object[] obj : expenses) {

				ExpenseDto expenseDto = new ExpenseDto();

				String name = (String) obj[0];

				double amount = (Double) obj[2];

				expenseDto.setAmount(amount);

				if (name.equals("Travel")) {
					expenseDtos.set(0, expenseDto);
				} else if (name.equals("Paying_Guest(PG)")) {
					expenseDtos.set(1, expenseDto);
				} else if (name.equals("Purchase")) {
					expenseDtos.set(2, expenseDto);
				} else if (name.equals("Others")) {
					expenseDtos.set(3, expenseDto);
				} else if (name.equals("Food")) {
					expenseDtos.set(4, expenseDto);
				} else if (name.equals("Medical")) {
					expenseDtos.set(5, expenseDto);
				}

			}

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return expenseDtos;
	}


	public List<ExpenseDto> getExpenseDetailBasedOnDate() {

		log.info("API name = *getExpenseDetailBasedOnDate");

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();

		try {

			List<Object[]> expenses = expenseDetailsRepository.getExpenseListBasedonDay(user.get().getId());

			for (Object[] obj : expenses) {

				ExpenseDto expenseDto = new ExpenseDto();

				expenseDto.setDate((String) obj[0]);

				expenseDto.setAmount((Double) obj[1]);

				expenseDtos.add(expenseDto);
			}

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return expenseDtos;
	}

	@Override
	public List<ExpenseDto> getExpenseDetailBasedPaymentModeSearch(String year) {
		
		log.info("API name = *getExpenseDetailBasedPaymentMode" );
		
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();

		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();
		long userId = (Long)httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
				
		//List<Object[]> expenses = expenseDetailsRepository.getExpenseListForPaymentMode(String.valueOf(currentYear),user.get().getId());
		
		List<Object[]> expenses = expenseDetailsRepository.getExpenseListForPaymentMode(year,user.get().getId());

		ExpenseDto expenseDto1 = new ExpenseDto();

		ExpenseDto expenseDto2 = null;

		ExpenseDto expenseDto3 = null;

		ExpenseDto expenseDto4 = null;

		ExpenseDto expenseDto5 = null;

		ExpenseDto expenseDto6 = null;

		ExpenseDto expenseDto7 = null;

		ExpenseDto expenseDto8 = null;

		ExpenseDto expenseDto9 = null;
		
		ExpenseDto expenseDto10 = null;
		
		ExpenseDto expenseDto11 = null;
		
		ExpenseDto expenseDto12 = null;

		for (Object[] obj : expenses) {

			String month = (String) obj[1];

			String paymentMode = (String) obj[2];

			double amount = (Double) obj[3];

			if (month.equals("Jan")) {

				if (paymentMode.equals("cash")) {

					expenseDto1.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto1.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto1.setAmountCard(amount);
				}

				expenseDto2 = new ExpenseDto();

			} else if (month.equals("Feb")) {

				if (paymentMode.equals("cash")) {

					expenseDto2.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto2.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto2.setAmountCard(amount);
				}

				expenseDto3 = new ExpenseDto();

			} else if (month.equals("Mar")) {

				if (paymentMode.equals("cash")) {

					expenseDto3.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto3.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto3.setAmountCard(amount);
				}

				expenseDto4 = new ExpenseDto();

			} else if (month.equals("Apr")) {

				if (paymentMode.equals("cash")) {

					expenseDto4.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto4.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto4.setAmountCard(amount);
				}

				expenseDto5 = new ExpenseDto();

			} else if (month.equals("May")) {

				if (paymentMode.equals("cash")) {

					expenseDto5.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto5.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto5.setAmountCard(amount);
				}

				expenseDto6 = new ExpenseDto();

			} else if (month.equals("Jun")) {

				if (paymentMode.equals("cash")) {

					expenseDto6.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto6.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto6.setAmountCard(amount);
				}

				expenseDto7 = new ExpenseDto();

			} else if (month.equals("Jul")) {

				if (paymentMode.equals("cash")) {

					expenseDto7.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto7.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto7.setAmountCard(amount);
				}

				expenseDto8 = new ExpenseDto();
			} else if (month.equals("Aug")) {

				if (paymentMode.equals("cash")) {

					expenseDto8.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto8.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto8.setAmountCard(amount);
				}

				expenseDto9 = new ExpenseDto();

			} else if (month.equals("Sep")) {

				if (paymentMode.equals("cash")) {

					expenseDto9.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto9.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto9.setAmountCard(amount);
				}
				
				expenseDto10 = new ExpenseDto();
				
			} else if (month.equals("Oct")) {
				if (paymentMode.equals("cash")) {

					expenseDto10.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto10.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto10.setAmountCard(amount);
				}
				
				expenseDto11 = new ExpenseDto();
				
			} else if (month.equals("Nov")){
				if (paymentMode.equals("cash")) {

					expenseDto11.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto11.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto11.setAmountCard(amount);
				}
				
				expenseDto12 = new ExpenseDto();
				
			} else if (month.equals("Dec")){
				if (paymentMode.equals("cash")) {

					expenseDto12.setAmount(amount);

				} else if (paymentMode.equals("online payment")) {

					expenseDto12.setAmountUPI(amount);

				} else if (paymentMode.equals("card")) {
					expenseDto12.setAmountCard(amount);
				}
			}

		}
		
		try {
			
			if(expenseDto1 != null) 
			expenseDtos.add(expenseDto1);
			if(expenseDto2 != null)
			expenseDtos.add(expenseDto2);
			if(expenseDto3 != null)
			expenseDtos.add(expenseDto3);
			if(expenseDto4 != null)
			expenseDtos.add(expenseDto4);
			if(expenseDto5 != null)
			expenseDtos.add(expenseDto5);
			if(expenseDto6 != null)
			expenseDtos.add(expenseDto6);
			if(expenseDto7 != null)
			expenseDtos.add(expenseDto7);
			if(expenseDto8 != null)
			expenseDtos.add(expenseDto8);
			if(expenseDto9 != null)
			expenseDtos.add(expenseDto9);
			if(expenseDto10 != null)
			expenseDtos.add(expenseDto10);
			if(expenseDto11 != null)
			expenseDtos.add(expenseDto11);
			if(expenseDto12 != null)
			expenseDtos.add(expenseDto12);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}


		return expenseDtos;
	}



	@Override
	public String getAverageAmount() {
		
		log.info("API name = *getAverageAmount" );

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		
		DecimalFormat dec = new DecimalFormat("00.##");

		LocalDate currentdate = LocalDate.now();
		LocalDate earlier = currentdate.minusMonths(0);

		int currentmonth = earlier.getMonth().getValue();
		int year = earlier.getYear();
		
		String currentmonth1 = monthCaluculator(dec.format(currentmonth));

		YearMonth yearMonthObject = YearMonth.of(year, currentmonth);
		int daysInMonth = yearMonthObject.lengthOfMonth();

		List<Object[]> expenses = expenseDetailsRepository.getexpensedetailsbasedonmonthForAvg(currentmonth1,
				String.valueOf(year), user.get().getId());

		double amount = 0.0;
		
		String output = "";

		try {

			if (expenses.size() != 0) {

				for (Object[] obj : expenses) {

					if ((Double) obj[1] != null) {
						amount = (Double) obj[1];
					}
				}
			}

			double avg = amount / daysInMonth;

			output = dec.format(avg);

		} catch (Exception e) {
			log.error("Exception " + e);
			e.printStackTrace();
		}

		return output;
	}



	@Override
	public List<ExpenseDto> getExpenseReport(String year, String month, String category) {
		
		log.info("API name = *getExpenseReport" );
		
		 AtomicInteger counter = new AtomicInteger(1);
		
		List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto> ();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		long userId = (Long)httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		
		//double sum = 0.0;

		
		log.info("To fetch data through stream >>>>>>");

	    List<ExpenseDetails> expenseDetails = null;
	    
	
		
		try {
			
			if (category.equals("All")) {

				expenseDetails = expenseDetailsRepository.findByYearAndMonthAndUserId(year, month, user.get().getId());

			} else {
				expenseDetails = expenseDetailsRepository.findByYearAndMonthAndExpenseCategoryNameAndUserId(year, month,category, user.get().getId());
			}

			
			expenseDetails.stream().forEach(expenseDetail ->{
			
				ExpenseDto expenseDto = new ExpenseDto();

				BeanUtils.copyProperties(expenseDetail, expenseDto);

				expenseDto.setSerialId(counter.getAndIncrement());

				expenseDto.setCategoryName(expenseDetail.getExpenseCategory().getName());

				expenseDto.setCreatedDate(format.format(expenseDetail.getCreatedDate()));

				if(expenseDetail.getModifiedDate() == null){
					
				}else{
					expenseDto.setModifiedDate(format.format(expenseDetail.getModifiedDate()));
				}
				
				

				String date = expenseDetail.getDate();

				String a[] = date.split("-");

				if (a[1].equals("01")) {
					expenseDto.setMonth("Jan");
				} else if (a[1].equals("02")) {
					expenseDto.setMonth("Feb");

				} else if (a[1].equals("03")) {

					expenseDto.setMonth("Mar");

				} else if (a[1].equals("04")) {

					expenseDto.setMonth("Apr");

				} else if (a[1].equals("05")) {

					expenseDto.setMonth("May");

				} else if (a[1].equals("06")) {

					expenseDto.setMonth("Jun");
				} else if (a[1].equals("07")) {

					expenseDto.setMonth("Jul");

				} else if (a[1].equals("08")) {

					expenseDto.setMonth("Aug");

				} else if (a[1].equals("09")) {

					expenseDto.setMonth("Sep");

				} else if (a[1].equals("10")) {

					expenseDto.setMonth("Oct");

				} else if (a[1].equals("11")) {

					expenseDto.setMonth("Nov");

				} else if (a[1].equals("12")) {

					expenseDto.setMonth("Dec");

				}

				expenseDtos.add(expenseDto);
				
			//	sum = sum + expenseDetail.getAmount();
			});
			
			log.info("year = "+year + " month = "+month+" category = "+category );
			
			httpsession.setAttribute("sumOfExpenseAmount", "null");

		} catch (Exception e) {

			log.info("Exception " + e);

		} finally {

		}


		
		return expenseDtos;
	}
	
	


	@Override
	public List<ExpenseDetails> getExpenseDetailsBasedOnCategory(String year,
			Predicate<ExpenseDetails> expensedetailz) {

		log.info("API name = *getExpenseDetailsBasedOnCategory");
		
		// AtomicInteger counter = new AtomicInteger(1);

		long userId = (Long) httpsession.getAttribute("userId");

		List<ExpenseDetails> expenseDetaily = new ArrayList<ExpenseDetails>();

		List<ExpenseDetails> expenseDetails = expenseDetailsRepository.findByYearAndUserId(year, userId);
		
		/*
		 * expenseDetails.stream().forEach(expenseDetail ->{
		 * 
		 * String date[] = expenseDetail.getDate().split("-");
		 * 
		 * String output = String.format("%04d",counter.getAndIncrement()); String
		 * expIdPrefix = "EXP".concat("22");
		 * 
		 * String expenseId =
		 * expIdPrefix.concat(date[1]).concat(String.valueOf(output));
		 * 
		 * expenseDetail.setExpenseId(expenseId);
		 * 
		 * log.info(expenseId);
		 * 
		 * expenseDetailsRepository.save(expenseDetail);
		 * 
		 * });
		 */

		for (ExpenseDetails expenseDetail : expenseDetails) {

			if (expensedetailz.test(expenseDetail)) {

				expenseDetaily.add(expenseDetail);
			}
		}

		return expenseDetaily;
	}

	@Override
	public Page<ExpenseDetails> findExpenseDetailsBasedOnPagination(int offset, int pagesize) {

		log.info("API name = *findExpenseDetailsBasedOnPagination");

		LocalDateTime startTime = LocalDateTime.now();

		Page<ExpenseDetails> expenseDetails = null;

		try {
			expenseDetails = expenseDetailsRepository.findAll(PageRequest.of(offset, pagesize));

		} catch (Exception e) {

			log.error("Exception " + e);

		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *findExpenseDetailsBasedOnPagination | latency = " + latency);
		}

		return expenseDetails;
	}

	@Override
	public List<ExpenseDto> getExpenseDetailBasedPaymentMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExpenseDetails> getExpenseDetailswithPriceRange(String year, String category, Double price) {

		log.info("API name = *getExpenseDetailswithPriceRange | input -> year = "+ year +" : category = "+ category + " price = "+ price);
		List<ExpenseDetails> expenseDetaily = new ArrayList<ExpenseDetails>();

		List<ExpenseDetails> expenseDetail = null;
		
		LocalDateTime startTime = LocalDateTime.now();

		long userId = 1;
		Optional<User> user = userRepository.findById(userId);

		try {

			expenseDetaily = expenseDetailsRepository.findByYearAndExpenseCategoryNameAndUserId(year, category, userId);

			// Stream output = expenseDetaily.stream().filter(ob -> ob.getAmount() >
			// price).map(expense -> new ExpenseDetails(expense));

			expenseDetail = expenseDetaily.stream().filter(ob -> ob.getAmount() > price).collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getExpenseDetailswithPriceRange | latency = " + latency+" output = "+ expenseDetail.toString() );

		}

		return expenseDetail;
	}

	@Override
	public List<ExpenseDetails> getExpenseDetailswithPriceRangeBetween(String year, String category, Integer minPrice,	Integer maxPrice) {
		
		log.info("API name = *getExpenseDetailswithPriceRangeBetween | input -> year = "+ year +" : category = "+ category + " minPrice = "+ minPrice+ " maxPrice = "+maxPrice);
	
		List<ExpenseDetails> expenseDetaily = new ArrayList<ExpenseDetails>();
		List<ExpenseDetails> expenseDetail = null;
		
		LocalDateTime startTime = LocalDateTime.now();

		long userId = 1;
		Optional<User> user = Optional.of(userRepository.findById(userId).orElseThrow(() ->  new NoDataFoundException("Data not found")));

		try {
			
			if (year == null) {
				year = String.valueOf(startTime.getYear());
			}

			if (category == null) {
				category = "Purchase";
			}

			expenseDetaily = expenseDetailsRepository.findByYearAndExpenseCategoryNameAndUserId(year, category, userId);

			// Stream output = expenseDetaily.stream().filter(ob -> ob.getAmount() >
			// price).map(expense -> new ExpenseDetails(expense)); // 10000 > 100 && 10000 < 2000

			expenseDetail = expenseDetaily.stream().filter(ob -> ob.getAmount() > minPrice && ob.getAmount() < maxPrice).collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getExpenseDetailswithPriceRangeBetween | latency = " + latency+" output = "+ expenseDetail.toString() );
		}

		return expenseDetail;
	}

	

	
	

	
	


	

}
