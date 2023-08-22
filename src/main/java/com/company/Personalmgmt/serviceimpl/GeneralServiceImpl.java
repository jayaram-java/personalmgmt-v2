package com.company.Personalmgmt.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Supplier;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.GeneralDto;
import com.company.Personalmgmt.dto.MoveForwardDto;
import com.company.Personalmgmt.model.MoveForward;
import com.company.Personalmgmt.model.ScheduleEmailTask;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.ExpenseDetailsRepository;
import com.company.Personalmgmt.repository.IncomeDetailsRepository;
import com.company.Personalmgmt.repository.MoveForwardRepository;
import com.company.Personalmgmt.repository.ScheduleEmailTaskRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(GeneralServiceImpl.class);

	@Autowired
	private IncomeDetailsRepository incomeDetailsRepository;

	@Autowired
	private MoveForwardRepository moveForwardRepository;
	
	@Autowired
	private PersonalInfoServiceImpl personalInfoServiceImpl;
	
	@Autowired
	ExpenseDetailsRepository expenseDetailsRepository;
	
	@Autowired
	ScheduleEmailTaskRepository scheduleEmailTaskRepository;
	
	@Autowired
	HttpSession httpsession;
	
	@Autowired
	UserRepository userRepository;
	
	

	@Override
	public List<GeneralDto> getBalanceSheetDetails(String month, String year) {

		Double amount_in = 0.0;
		Double amount_out = 0.0;

		List<GeneralDto> generalDtos = new ArrayList<GeneralDto>();

		System.out.println("Input : " + month + "  ,  " + year);

		List<Object[]> response = incomeDetailsRepository.getBalanceSheetDetails(month, year);

		for (Object[] res : response) {
			String mode = (String) res[3];

			if (mode.equals("OUT")) {
				amount_out = amount_out + (Double) res[2];
			} else {
				amount_in = amount_in + (Double) res[2];
			}
		}
		GeneralDto generalDto = new GeneralDto();

		generalDto.setMonth(month);
		generalDto.setMode("In");
		generalDto.setAmount(amount_in);

		generalDtos.add(generalDto);

		GeneralDto generalDto1 = new GeneralDto();

		generalDto1.setMonth(month);
		generalDto1.setMode("Out");
		generalDto1.setAmount(amount_out);

		generalDtos.add(generalDto1);

		GeneralDto generalDto2 = new GeneralDto();

		generalDto2.setMonth(month);
		generalDto2.setMode("Balance");
		generalDto2.setAmount(amount_in - amount_out);

		generalDtos.add(generalDto2);

		return generalDtos;
	}

	@Override
	public List<MoveForwardDto> getMoveForwardDetails() {

		List<MoveForwardDto> moveForwardDtos = new ArrayList<MoveForwardDto>();
		List<MoveForward> moveForwards = moveForwardRepository.findAll();

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String endDate = format.format(date);

		for (MoveForward moveforward : moveForwards) {
			MoveForwardDto moveForwardDto = new MoveForwardDto();
			BeanUtils.copyProperties(moveforward, moveForwardDto);

			String d11 = format.format(moveforward.getTargetEndDate());
			String d33 = format.format(moveforward.getPersonDob());

			try {

				Date d1 = format.parse(d11);
				Date d2 = format.parse(endDate);
				Date d3 = format.parse(d33);

				moveForwardDto.setEndDate(format.format(moveforward.getTargetEndDate()));

				String remainingDays = personalInfoServiceImpl.ageCalculator(d2, d1);
				String personAge = personalInfoServiceImpl.ageCalculator(d3, d2);

				moveForwardDto.setRemainingDays(remainingDays);
				moveForwardDto.setPersonAge(personAge);

			} catch (Exception e) {
				e.printStackTrace();
			}

			moveForwardDtos.add(moveForwardDto);
		}

		return moveForwardDtos;
	}

	@Override
	public Map<String,Object> timeDifference(String startDate, String endDate) {
		
		log.info("API name = *timeDifference | input =" + startDate + " "+endDate);
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		LocalDateTime startTime = LocalDateTime.now();
		String result = "";
		String ds = startDate.replace("T", " ");
		String de = endDate.replace("T", " ");
		
		Map<String,Object> response = new HashMap<String,Object>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		Date d1 = null;
		Date d2 = null;
		
		Date d3 = null;
		Date d4 = null;

		try {

			d1 = format.parse(ds);
			d2 = format.parse(de);
			
			d3 = format2.parse(ds);
			d4 = format2.parse(de);

			long diff = d2.getTime() - d1.getTime();

			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			
			Instant instant1 = d3.toInstant();
			ZonedDateTime zdt1 = instant1.atZone(ZoneId.systemDefault());
			LocalDate date1 = zdt1.toLocalDate();
			Instant instant2 = d4.toInstant();
			ZonedDateTime zdt2 = instant2.atZone(ZoneId.systemDefault());
			LocalDate date2 = zdt2.toLocalDate();
			Period diff1 = Period.between(date1, date2);
			
			 LocalDate date5 = LocalDate.parse(ds, dtf);
			 LocalDate date6 = LocalDate.parse(de, dtf);
			
			long daysBetween = ChronoUnit.DAYS.between(date5, date6);
			
			
			
			int month = diff1.getMonths();
			int days = diff1.getDays();
			int year = diff1.getYears();

			if(month == 0 && year == 0){
				result = String.valueOf(diffDays).concat(" Days ").concat(String.valueOf(diffHours)).concat(" Hours ")
						.concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			}else if(year == 0 && month != 0){
				result = String.valueOf(month).concat(" Month ").concat(String.valueOf(days)).concat(" Days ").concat(String.valueOf(diffHours)).concat(" Hours ")
						.concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			}else if(year !=0 ){
				result = String.valueOf(year).concat(" Year ").concat(String.valueOf(month)).concat(" Month ").concat(String.valueOf(days)).concat(" Days ").concat(String.valueOf(diffHours)).concat(" Hours ")
						.concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			}

			response.put("DiffDate", result);
			response.put("DiffDays", String.valueOf(daysBetween));

		} catch (Exception e) {
			e.printStackTrace();

			log.error("Exception " + e);
		}finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *timeDifference | latency = " + latency +"response = "+ response);

		}

		return response;
	}
	
	
	public String timeDifference1(String startDate, String endDate) {

		String result = "";
		String ds = startDate.replace("T", " ");
		String de = endDate.replace("T", " ");
	

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date d1 = null;
		Date d2 = null;

		try {

			d1 = format.parse(ds);
			d2 = format.parse(de);

			long diff = d2.getTime() - d1.getTime();

			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			
			Instant instant1 = d1.toInstant();
			ZonedDateTime zdt1 = instant1.atZone(ZoneId.systemDefault());
			LocalDate date1 = zdt1.toLocalDate();
			Instant instant2 = d2.toInstant();
			ZonedDateTime zdt2 = instant2.atZone(ZoneId.systemDefault());
			LocalDate date2 = zdt2.toLocalDate();
			Period diff1 = Period.between(date1, date2);
			
		
			
			int month = diff1.getMonths();
			int days = diff1.getDays();
			int year = diff1.getYears();

			if(month == 0 && year == 0){
				result = String.valueOf(diffDays).concat(" Days ").concat(String.valueOf(diffHours)).concat(" Hours ")
						.concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			}else if(year == 0 && month != 0){
				result = String.valueOf(month).concat(" Month ").concat(String.valueOf(days)).concat(" Days ").concat(String.valueOf(diffHours)).concat(" Hours ")
						.concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			}else if(year !=0 ){
				result = String.valueOf(year).concat(" Year ").concat(String.valueOf(month)).concat(" Month ").concat(String.valueOf(days)).concat(" Days ").concat(String.valueOf(diffHours)).concat(" Hours ")
						.concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			}

			

		} catch (Exception e) {

		}

		return result;
	}

	@Override
	public GeneralDto getTimeFromZone(String timezone) {

		GeneralDto generalDto = new GeneralDto();

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		TimeZone tz = TimeZone.getTimeZone(timezone);

		df.setTimeZone(tz);
		format.setTimeZone(tz);
		generalDto.setTime(df.format(date));

		String d1 = format.format(date);

		TimeZone tz1 = TimeZone.getTimeZone("Asia/Kolkata");
		format.setTimeZone(tz1);
		String d2 = format.format(date);

		String timediff = null;

		if (timezone.equals("Asia/Shanghai")) {
			timediff = timeDifference1(d2, d1);
		} else {
			timediff = timeDifference1(d1, d2);
		}
		
		generalDto.setTimeDiff(timediff);

		return generalDto;
	}
	
	

	@Override
	public List<GeneralDto> getBalanceSheetDetailsThisYear() {

		log.info("API name = *getBalanceSheetDetailsThisYear ");

		// Supplier is a simple functional interface, which is represents an
		// operator
		// that provides a value for each call

		LocalDateTime startTime = LocalDateTime.now();

		long userId = (Long) httpsession.getAttribute("userId");

		Supplier<LocalDateTime> ob = () -> LocalDateTime.now();

		LocalDateTime time = ob.get();

		List<GeneralDto> generalDtos = new ArrayList<GeneralDto>();

		try {

			List<Object[]> incomes = incomeDetailsRepository.getincomedetails(time.getYear());

			incomes.stream().forEach(income -> {
				GeneralDto generalDto = new GeneralDto();

				generalDto.setMonth((String) income[1]);
				generalDto.setIncomeAmt((double) income[2]);

				generalDtos.add(generalDto);

			});

			List<Object[]> expenses = expenseDetailsRepository.getexpensedetails(String.valueOf(time.getYear()),
					userId);

			expenses.stream().forEach(expense -> {

				String month = (String) expense[1];

				generalDtos.stream().forEach(generaldto -> {

					if (month.equals(generaldto.getMonth())) {

						generaldto.setExpenseAmt((double) expense[2]);

						double balanceAmt = generaldto.getIncomeAmt() - generaldto.getExpenseAmt();

						generaldto.setBalanceAmt(balanceAmt);

						generaldto.setYear(time.getYear());

					}

				});

			});

		} catch (Exception e) {

			log.error("Exception " + e);

		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getBalanceSheetDetailsThisYear | latency = " + latency);

		}
		return generalDtos;
	}
	
	@Override
	public List<GeneralDto> getBalanceSheetDetailsThisYearFilter(String year) {
		// Supplier is a simple functional interface, which is represents an operator
		// that provides a value for each call
		
		long userId = (Long) httpsession.getAttribute("userId");

		Supplier<LocalDateTime> ob = () -> LocalDateTime.now();

		LocalDateTime time = ob.get();

		List<GeneralDto> generalDtos = new ArrayList<GeneralDto>();

		List<Object[]> incomes = incomeDetailsRepository.getincomedetails(Integer.valueOf(year));

		incomes.stream().forEach(income -> {
			GeneralDto generalDto = new GeneralDto();

			generalDto.setMonth((String) income[1]);
			generalDto.setIncomeAmt((double) income[2]);

			generalDtos.add(generalDto);

		});
		
		List<Object[]> expenses = expenseDetailsRepository.getexpensedetails(year, userId);
		
		expenses.stream().forEach(expense ->{
			
			String month = (String) expense[1];
			
			generalDtos.stream().forEach(generaldto ->{
				
				if(month.equals(generaldto.getMonth())) {
					
					generaldto.setExpenseAmt((double)expense[2]);
					
					double balanceAmt = generaldto.getIncomeAmt() - generaldto.getExpenseAmt();
					
					generaldto.setBalanceAmt(balanceAmt);
					
				}
				
			});
			
			
		});
		return generalDtos;
	}
	
	

	@Override
	public String enableExpenseDetail() {

		String response = "";

		try {

			LocalDateTime localDateTime = LocalDateTime.now();

			LocalDateTime executionTime = localDateTime.plusMonths(1);

			String month = String.valueOf(executionTime.getMonth());

			String year = String.valueOf(executionTime.getYear());

			String purpose = "Expense Module";

			long id = 1;

			ScheduleEmailTask scheduleEmailTask = scheduleEmailTaskRepository
					.findByPurposeAndMonthAndYearAndUserId(purpose, month, year, id);

			if (scheduleEmailTask == null) {
				
				long userId = 1;
				
				try {
					userId = (Long) httpsession.getAttribute("userId");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Optional<User> user = userRepository.findById(userId);
				ScheduleEmailTask scheduleEmailTasknew = new ScheduleEmailTask( purpose, month, year, "No", user.get(), new Date());
				scheduleEmailTaskRepository.save(scheduleEmailTasknew);
				response = "register successfully";
			} else {
				response = "Already registered.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			response = "Exception occured "+e;
		}

		return response;
	}

	
	
	
	
	
	
	
	
	

}
