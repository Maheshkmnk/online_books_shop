package com.example.sql_queries.service.impl;

import com.example.sql_queries.dao.mysql.CustomerDao;
import com.example.sql_queries.dto.AddressDto;
import com.example.sql_queries.dto.LoginRequestDto;
import com.example.sql_queries.dto.RegisterCustomerRequestDto;
import com.example.sql_queries.dto.responseDto.ResponseDto;
import com.example.sql_queries.entity.mysql.Customer;
import com.example.sql_queries.exceptions.individual_exceptions.CSVFileProcessingError;
import com.example.sql_queries.exceptions.individual_exceptions.DuplicateEntryException;
import com.example.sql_queries.mappers.CustomerPopulator;
import com.example.sql_queries.service.interfaces.ICustomerService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public ResponseDto register(RegisterCustomerRequestDto registerCustomerRequestDto) {
        Customer customerEntity = CustomerPopulator.INSTANCE.toEntity(registerCustomerRequestDto);
        boolean isExisted = customerDao.existsByEmail(registerCustomerRequestDto.getEmail());

        if (isExisted) {
            System.out.println("ajdfkjsdfksjfksdjbvksjdvksjvsjv");
            throw new DuplicateEntryException("User Already existed with the given email id");
        }

        Customer customer = customerDao.save(customerEntity);
        return CustomerPopulator.INSTANCE.toDto(customer);
    }

    @Override
    public ResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public List<ResponseDto> getCustomerDetailsByEmail(String email) {
        return null;
    }

    @Override
    public Customer updateCustomerDetailsByEmail(RegisterCustomerRequestDto registerCustomerRequestDto) {
        return null;
    }

    @Override
    public String updatePassword() {
        return null;
    }

    @Override
    public List<RegisterCustomerRequestDto> processCsvFile(MultipartFile file) {
//        ArrayList<RegisterCustomerRequestDto> arrayList = new ArrayList<>();
        HashMap<String, RegisterCustomerRequestDto> customerDtolist = new HashMap<>();
        HashMap<String, AddressDto> addressDtolist = new HashMap<>();

        try (InputStream inputStream = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {

            XSSFSheet customerSheet = workbook.getSheetAt(0);

            if (customerSheet != null) {
                Map<String, Integer> headerMap = getHeaderMap(customerSheet.getRow(0));
                for (int y = 1; y < customerSheet.getLastRowNum(); y++) {
                    XSSFRow row = customerSheet.getRow(y);
                    if (row != null) {
                        RegisterCustomerRequestDto customerDto = new RegisterCustomerRequestDto();
                        String email = row.getCell(headerMap.get("email")).getStringCellValue();
                        customerDto.setEmail(email);
                        customerDto.setAge((int) row.getCell(headerMap.get("age")).getNumericCellValue());
                        customerDto.setCname(row.getCell(headerMap.get("cname")).getStringCellValue());
                        customerDto.setPassword(row.getCell(headerMap.get("password")).getStringCellValue());
                        customerDto.setAddress(new ArrayList<>());

                        customerDtolist.put(email, customerDto);
                    }
                }
            }

            XSSFSheet addressSheet = workbook.getSheetAt(1);

            if (addressSheet != null) {
                Map<String, Integer> addressheader = getHeaderMap(addressSheet.getRow(0));

                for (int x = 1; x < addressSheet.getLastRowNum(); x++) {
                    XSSFRow addRow = addressSheet.getRow(x);
                    if (addRow != null) {
                        AddressDto addressDto = new AddressDto();
                        addressDto.setCity(addRow.getCell(addressheader.get("city")).getStringCellValue());
                        addressDto.setState(addRow.getCell(addressheader.get("state")).getStringCellValue());
                        addressDto.setNationality(addRow.getCell(addressheader.get("nationality")).getStringCellValue());
                        String email = addRow.getCell(addressheader.get("email")).getStringCellValue();

                        addressDtolist.put(email, addressDto);
                        if(customerDtolist.containsKey(email)){
                            customerDtolist.get(email).getAddress().add(addressDto);
                        }

                    }
                }
            }

        } catch (IOException e) {
            throw new CSVFileProcessingError("excel file processing error");
        }

        return new ArrayList<>(customerDtolist.values());
    }


    private static Map<String, Integer> getHeaderMap(Row headerRow) {

        HashMap<String, Integer> headerdata = new HashMap<>();

        if (headerRow != null) {
            for (Cell cell : headerRow) {
                headerdata.put(cell.getStringCellValue().toLowerCase(), cell.getColumnIndex());
            }
        }
        return headerdata;
    }
}
