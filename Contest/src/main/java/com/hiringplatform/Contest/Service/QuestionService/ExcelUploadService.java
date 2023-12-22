package com.hiringplatform.Contest.Service.QuestionService;

import com.hiringplatform.Contest.model.McqQuestion;
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
public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file) {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType());
    }

    public static List<McqQuestion> getMcqQuestions(InputStream inputStream) throws IOException {
        List<McqQuestion> mcq = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("McqQuestion");
        int rowIndex = 0;

        for (Row row : sheet) {
            if(rowIndex>101)
                break;
            if (rowIndex == 0) {
                rowIndex++;
                continue;
            }
            Iterator<Cell> cellIterator = row.iterator();
            int cellIndex = 0;

            McqQuestion mcqQuestion = new McqQuestion();
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                System.out.println("Cells => " + cell + " : " + cellIndex);
                System.out.println(cell);

                switch (cellIndex) {

                    case 0:
                        String qid = cell.toString();
                        mcqQuestion.setQid(qid);
                        System.out.println(qid+"dfg");
                        System.out.println(mcqQuestion.getQid());
                        break;
                    case 1:
                        String question = cell.toString();
                        mcqQuestion.setQuestion(question);
                        break;
                    case 2:
                        String correctOp = cell.toString();
                        mcqQuestion.setCorrectOp(correctOp);
                        break;
                    case 3:
                        String part = cell.toString();
                        mcqQuestion.setPart(part);
                        break;
                    case 4:
                        String wei = cell.toString();
                        mcqQuestion.setWeightage(wei);
                        break;
                    case 5:
                        String option1 = cell.toString();
                        mcqQuestion.setOption1(option1);
                        break;
                    case 6:
                        String option2 = cell.toString();
                        mcqQuestion.setOption2(option2);
                        break;
                    case 7:
                        String option3 = cell.toString();
                        mcqQuestion.setOption3(option3);
                        break;
                    case 8:
                        String option4 = cell.toString();
                        mcqQuestion.setOption4(option4);
                        break;
                    default:
                        break;
                }
                cellIndex++;
            }
            mcq.add(mcqQuestion);
            rowIndex++;
        }
        return mcq;
    }
}