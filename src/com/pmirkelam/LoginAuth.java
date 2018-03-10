package com.pmirkelam;

import com.pmirkelam.record.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pmirkelam.Constants.*;
import static com.pmirkelam.Constants.AVAILABLE;

public class LoginAuth {

    private String fileName;
    private String typeName;
    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    private String FILE_HEADER = "ID, Password";
    private int type;
    private int fileListSize;

    private List<Record> records;
    private Record record;

    private static LoginAuth instance;

    public static LoginAuth getInstance() {

        if (instance == null) {
            instance = new LoginAuth();
        }
        return instance;
    }

    private LoginAuth() {
        this.type = type;
        createRecordFile(LOGIN_GUEST_FILE_NAME);
        createRecordFile(LOGIN_RECEPTIONIST_FILE_NAME);
    }

    private void createRecordFile(String fileName) {

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);

        } catch (IOException e) {
            System.out.println("Error while creating login file!");
            e.printStackTrace();
        }

        if (fileListSize <= 0) {
            System.out.println("It is the first time for creating login file.");
            initCsvFile(fileName);

        } else {
            System.out.println("There is already login file. Record size: ");
        }
    }

    /**
     * Initialize csv file for set available each room of the hotel for the first use of the program.
     */
    private void initCsvFile(String fileName) {

        FileWriter initFileWriter = null;

        try {
            initFileWriter = new FileWriter(fileName, true);

            initFileWriter.append(FILE_HEADER);
            initFileWriter.append(NEW_LINE_SEPARATOR);
            System.out.println("Record file was created successfully. Initializing...");

            if (LOGIN_GUEST_FILE_NAME.equals(fileName)) {
                try {

                    initFileWriter.append("1");
                    initFileWriter.append(COMMA_DELIMITER);
                    initFileWriter.append("123");
                    initFileWriter.append(NEW_LINE_SEPARATOR);

                    initFileWriter.append("2");
                    initFileWriter.append(COMMA_DELIMITER);
                    initFileWriter.append("234");
                    initFileWriter.append(NEW_LINE_SEPARATOR);

                    initFileWriter.append("3");
                    initFileWriter.append(COMMA_DELIMITER);
                    initFileWriter.append("345");
                    initFileWriter.append(NEW_LINE_SEPARATOR);

                    System.out.println("Record guest login datas added successfully.");

                } catch (Exception e) {

                    System.out.println("Error while adding login datas to record file! initCsvFile");
                    e.printStackTrace();
                }

            } else {
                try {

                    initFileWriter.append("1");
                    initFileWriter.append(COMMA_DELIMITER);
                    initFileWriter.append("1abc");
                    initFileWriter.append(NEW_LINE_SEPARATOR);

                    initFileWriter.append("2");
                    initFileWriter.append(COMMA_DELIMITER);
                    initFileWriter.append("2abc");
                    initFileWriter.append(NEW_LINE_SEPARATOR);

                    System.out.println("Record receptionist login datas added successfully.");

                } catch (Exception e) {

                    System.out.println("Error while adding login datas to record file!");
                    e.printStackTrace();

                }
            }

        } catch (Exception e) {
            System.out.println("Error while initializing record file!");
            e.printStackTrace();

        } finally {

            try {
                if (initFileWriter != null) {
                    initFileWriter.flush();
                    initFileWriter.close();
                } else {
                    System.out.println("File writer is NULL!");
                }

            } catch (IOException e) {
                System.out.println("Error while flushing/closing file writer on create csv file method!");
                e.printStackTrace();
            }
        }
        fileListSize++;
    }

    /**
     * Read the login auth file line by line starting from the second line and assign it to 'records' list.
     */
    public boolean checkLogin(int type, String guestId, String password) {

        if(type == RECORDER_TYPE_GUEST){
            fileName=LOGIN_GUEST_FILE_NAME;
        } else {
            fileName=LOGIN_RECEPTIONIST_FILE_NAME;
        }
        BufferedReader bufferedReader = null;
        try {
            String line = "";
            bufferedReader = new BufferedReader(new FileReader(fileName));

            if (bufferedReader.readLine() != null) {

                String[] tokens = null;

                while ((line = bufferedReader.readLine()) != null) {

                    tokens = line.split(COMMA_DELIMITER);
                    if (tokens.length == 2) {
                        if (tokens[0].equals(guestId) && tokens[1].equals(password)) {
                            return true;
                        }
                    }
                }

            } else {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error while reading record file!");
            e.printStackTrace();

        } finally {

            try {
                if (bufferedReader != null) {
                    bufferedReader.close();

                } else {
                    System.out.println("File reader is NULL!");
                }

            } catch (IOException e) {
                System.out.println("Error while closing file reader!");
                e.printStackTrace();
            }
        }
        return false;
    }


}
