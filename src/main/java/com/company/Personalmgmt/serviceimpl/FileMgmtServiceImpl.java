package com.company.Personalmgmt.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.company.Personalmgmt.dto.FileMgmtDto;
import com.company.Personalmgmt.exception.FileStorageException;
import com.company.Personalmgmt.model.FileCategory;
import com.company.Personalmgmt.model.FileMgmtDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.FileCategoryRepository;
import com.company.Personalmgmt.repository.FileMgmtRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.FileMgmtService;

@Service
@PropertySource("classpath:general.properties")
public class FileMgmtServiceImpl implements FileMgmtService {
	
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(FileMgmtServiceImpl.class);
	
	@Value("${file.upload-dir}")
	private String filePath;
	
	@Autowired
	FileMgmtRepository fileMgmtRepository;
	
	@Autowired
	FileCategoryRepository fileCategoryRepository;
	
	@Autowired
	HttpSession httpsession;
	
	@Autowired
	UserRepository userRepository;
	

	private final Path fileStorageLocation = null;

	/*@Autowired
	public FileMgmtServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",ex);
		}
	}*/
	
	
	public String storeFile(MultipartFile file) {
		
		log.info("Method name = *storeFile" );
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			
			log.info("Exception " + ex);
			
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	@Override
	public boolean saveFileWithDetails(FileMgmtDto fileMgmtDto, Map<String, MultipartFile> multiparts) {

		log.info("Method name = *saveFileWithDetails");

		try {

			long userId = (Long) httpsession.getAttribute("userId");

			Optional<User> user = userRepository.findById(userId);

			FileCategory fileCategory = fileCategoryRepository.findByName(fileMgmtDto.getFileCategory());

			FileMgmtDetails fileMgmtDetails = new FileMgmtDetails();
			fileMgmtDetails.setFileCategory(fileCategory);
			fileMgmtDetails.setFileName(fileMgmtDto.getFileName());
			fileMgmtDetails.setRemarks(fileMgmtDto.getRemarks());
			fileMgmtDetails.setUser(user.get());
			fileMgmtDetails.setCreatedBy(user.get().getEmployeename());
			fileMgmtDetails.setCreatedDate(new Date());

			fileMgmtRepository.save(fileMgmtDetails);

			uploadFiles(multiparts, fileMgmtDetails);

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return true;
	}

	private void uploadFiles(Map<String, MultipartFile> multiparts, FileMgmtDetails fileMgmtDetails) {

		try {
			for (Map.Entry<String, MultipartFile> entry : multiparts.entrySet()) {
				String fileName = entry.getValue().getOriginalFilename();
				String ext = FilenameUtils.getExtension(fileName);
				
				String originalName[] = fileName.split("\\.");

				byte[] file = entry.getValue().getBytes();
				log.info("byte >>>>>>  = "+ file);

				String base64Image = Base64.getEncoder().encodeToString(file);
				
				log.info("base64Image >>>>>>  = "+ base64Image);

				String	addedPath = "/" + fileMgmtDetails.getFileCategory().getName() + "/"  + fileMgmtDetails.getId() + "/" + fileName + "/" + originalName[originalName.length-2].concat(".") + ext;

				if (fileName != null) {

					if (file != null) {

						String reqFilePath = filePath + addedPath;
						
						// path create
						File fileToWrite = new File(reqFilePath);

						if (fileToWrite.getParentFile().exists()) {
							fileToWrite.createNewFile();
						} else {

							if (fileToWrite.getParentFile().mkdirs()) {
								fileToWrite.createNewFile();
							} else {
								throw new IOException("Failed to create directory " + fileToWrite.getParent());
							}
						}

						FileOutputStream fileOuputStreamLarge = new FileOutputStream(fileToWrite);
						fileOuputStreamLarge.write(file);
					
						fileOuputStreamLarge.close();
						
						
						
						fileMgmtDetails.setUploadDirection(reqFilePath);
						fileMgmtDetails.setDocumentFormat(ext);
						fileMgmtDetails.setBase64Format(base64Image);
						fileMgmtDetails.setFileNameFromUploadfile(fileName);
						
						fileMgmtRepository.save(fileMgmtDetails);
					}
				}
			}
		} catch (Exception e) {
			log.info("Exception " + e);
			e.printStackTrace();
		}
	}

	@Override
	public List<FileMgmtDto> getAllUploadedFiles() {

		Date datey = new Date();

		SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");

		List<FileMgmtDto> fileMgmtDtos = new ArrayList<FileMgmtDto>();
		long userId = (Long) httpsession.getAttribute("userId");

		try {
			List<FileMgmtDetails> fileMgmtDetails = fileMgmtRepository.findByUserId(userId);
			long n = 1;

			for (FileMgmtDetails fileMgmtDetail : fileMgmtDetails) {

				FileMgmtDto fileMgmtDto = new FileMgmtDto();

				fileMgmtDto.setFileCategory(fileMgmtDetail.getFileCategory().getName());
				fileMgmtDto.setFileName(fileMgmtDetail.getFileNameFromUploadfile());
				fileMgmtDto.setStoreDate(format.format(fileMgmtDetail.getCreatedDate()));
				fileMgmtDto.setId(fileMgmtDetail.getId());
				fileMgmtDto.setSerialId(n);
				fileMgmtDto.setUploadDirection(fileMgmtDetail.getUploadDirection().replace('/', '\\'));

				fileMgmtDtos.add(fileMgmtDto);

				n++;
			}

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return fileMgmtDtos;
	}

	@Override
	public FileMgmtDto getUploadedFileFromId(Long id) {

		log.info("API name = *getUploadedFileFromId");

		FileMgmtDto fileMgmtDto = new FileMgmtDto();

		try {

			Optional<FileMgmtDetails> fileMgmtDetail = fileMgmtRepository.findById(id);

			httpsession.setAttribute("baseImage", fileMgmtDetail.get().getBase64Format());

			if (fileMgmtDetail.get().getDocumentFormat().equals("jpg")) {

				httpsession.setAttribute("imageFormat", "data:image/jpg;base64");

			} else {

				httpsession.setAttribute("imageFormat", "data:image/png;base64");

			}

			log.info(" format =  " + fileMgmtDetail.get().getBase64Format());
			
			log.info(" baseimage = "+httpsession.getAttribute("baseImage") );
			
			log.info(" image format = "+httpsession.getAttribute("imageFormat") );

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return fileMgmtDto;
	}	

}
