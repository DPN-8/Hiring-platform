package com.hiringplatform.Contest.Service.QuestionService;

import com.hiringplatform.Contest.model.CodeQuestion;
import net.minidev.json.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service

public class ExcelCoding {
    public static boolean isValidExcelFile(MultipartFile file)
    {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType());
    }

    public static List<CodeQuestion> getCodeQuestions(InputStream inputStream) throws IOException {
        List<CodeQuestion> code=new ArrayList<>();
        XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet=workbook.getSheet("CodingQuestion");
        int rowIndex=0;
        for(Row row:sheet)
        {
            if(rowIndex == 0)
            {
                rowIndex++;
                continue;
            }
            Iterator <Cell> cellIterator=row.iterator();
            int cellIndex=0;
            CodeQuestion codeQuestion=new CodeQuestion();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cellIndex) {
                    case 0 -> codeQuestion.setQid(String.valueOf(cell.getStringCellValue()));
                    case 1 -> codeQuestion.setQuestion(String.valueOf(cell.getStringCellValue()));
                   case 2-> {
                       String inputString=cell.toString();
                           JSONObject jsonObject=new JSONObject();
                           jsonObject.put("sampleIp",inputString);
                       codeQuestion.setInput(jsonObject.toString());

                   }
                     case 3->
                     {
                         String outputString=cell.toString();
                         JSONObject jsonObject=new JSONObject();
                         jsonObject.put("sampleOp",outputString);
                         codeQuestion.setOutput(jsonObject.toString());
                     }
                    case 4 -> codeQuestion.setWeightage(String.valueOf(cell.getStringCellValue()));
                    default -> {

                    }
                }
                cellIndex++;
            }
            code.add(codeQuestion);
            }
        return code;
        }
    }

