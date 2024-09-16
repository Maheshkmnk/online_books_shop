package com.example.sql_queries.service.impl;

import com.example.sql_queries.dto.request_dto.AddressDto;
import com.example.sql_queries.dto.request_dto.RegisterCustomerRequestDto;
import com.example.sql_queries.exceptions.individual_exceptions.CSVFileProcessingError;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class ExcelFileProcessing {

    public List<RegisterCustomerRequestDto> processCsvFile(MultipartFile file) {
        Map<String, RegisterCustomerRequestDto> custReqDto = null;
        Instant start = Instant.now();
        try (InputStream inputStream = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {

            XSSFSheet customerSheet = workbook.getSheet("customer");
            CompletableFuture<Map<String, RegisterCustomerRequestDto>> custReqDtoFuture = processCustomerSheet(customerSheet);

            XSSFSheet addressSheet = workbook.getSheet("address");
            CompletableFuture<Map<String, AddressDto>> addReqDtoFuture = processAddressSheet(addressSheet);

            CompletableFuture.allOf(custReqDtoFuture, addReqDtoFuture).join();
            custReqDto = custReqDtoFuture.get();
            Map<String, AddressDto> addReqDto = addReqDtoFuture.get();

            for (Map.Entry<String, AddressDto> entry : addReqDto.entrySet()) {
                if (custReqDto.containsKey(entry.getKey())) {
                    custReqDto.get(entry.getKey()).getAddress().add(entry.getValue());
                }
            }

        } catch (IOException e) {
            throw new CSVFileProcessingError("excel file processing error");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        Instant end = Instant.now();
        Duration betweenTime = Duration.between(start, end);
        System.out.println("time in millies :" + betweenTime.toMillis());
        System.out.println("time in seconds :" + betweenTime.toSeconds());


        return new ArrayList<>(custReqDto.values());
    }

    @Async
    private CompletableFuture<Map<String, RegisterCustomerRequestDto>> processCustomerSheet(XSSFSheet customerSheet) {
        Map<String, RegisterCustomerRequestDto> customerDtoList = new HashMap<>();
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

                    customerDtoList.put(email, customerDto);
                }
            }
        }
        return CompletableFuture.completedFuture(customerDtoList);
    }

    @Async
    private CompletableFuture<Map<String, AddressDto>> processAddressSheet(XSSFSheet addressSheet) {
        Map<String, AddressDto> addressDtolist = new HashMap<>();
        if (addressSheet != null) {
            Map<String, Integer> addressHeader = getHeaderMap(addressSheet.getRow(0));

            for (int x = 1; x < addressSheet.getLastRowNum(); x++) {
                XSSFRow addRow = addressSheet.getRow(x);
                if (addRow != null) {
                    AddressDto addressDto = new AddressDto();
                    addressDto.setCity(addRow.getCell(addressHeader.get("city")).getStringCellValue());
                    addressDto.setState(addRow.getCell(addressHeader.get("state")).getStringCellValue());
                    addressDto.setNationality(addRow.getCell(addressHeader.get("nationality")).getStringCellValue());
                    String email = addRow.getCell(addressHeader.get("email")).getStringCellValue();

                    addressDtolist.put(email, addressDto);
                }
            }
        }
        return CompletableFuture.completedFuture(addressDtolist);
    }


    private Map<String, Integer> getHeaderMap(Row headerRow) {
        Map<String, Integer> headerData = new HashMap<>();
        if (headerRow != null) {
            for (Cell cell : headerRow) {
                headerData.put(cell.getStringCellValue().toLowerCase(), cell.getColumnIndex());
            }
        }
        return headerData;
    }

}
