package com.pmirkelam.record;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.pmirkelam.Constants.AVAILABLE;
import static com.pmirkelam.Constants.FILE_NAME;
import static com.pmirkelam.Constants.TOTAL_ROOM_NUMBER;

public class RecordFile {

    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    private final String FILE_HEADER = "Room ID, Availability, Recorder Type, Recorder ID, Guest ID";
    private FileWriter fileWriter;
    private FileWriter addRecordFileWriter;
    private FileWriter initFileWriter;

    private String fileName;
    private List<Record> records;
    private static RecordFile instance;

    private BufferedReader fileReader;

    private final int ROOM_ID = -1;
    private final int AVAILABILITY = -1;
    private final int GUEST_ID = -1;
    private Record record;
    private int roomNo;
    private String line;

    private RecordFile() {
        System.out.println("RecordFile constructor");
        this.fileName = FILE_NAME;
        initRecordFile();
    }

    public static RecordFile getInstance() {

        if (instance == null) {
            instance = new RecordFile();
        }
        return instance;
    }

    /**
     * Create a csv file for records if the file does not exist
     */
    private void initRecordFile() {
        System.out.println("initRecordFile");

        try {
            initFileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println("Error while creating record file!");
            e.printStackTrace();
        }
        records = getRecordList();

        if (records == null || records.size() <= 0) {
            System.out.println("It is the first time for creating record file. records.size: " + records.size());
            createCsvFile();
        } else {
            System.out.println("There is already a record file. records.size: " + records.size());

        }
    }

    private void createCsvFile() {

        try {
            initFileWriter.append(FILE_HEADER);
            initFileWriter.append(NEW_LINE_SEPARATOR);
            System.out.println("Record file was created successfully. Initializing...");
            Record initRecord;
            for (int i = 1; i <= TOTAL_ROOM_NUMBER; i++) {
                initRecord = new Record(i, AVAILABLE, -1);
                addRecord(initRecord);
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
    }


    /**
     * Read the file line by line starting from the second line and assign it to 'records' list.
     */
    public List<Record> getRecordList() {
        fileReader = null;
        try {
            records = new ArrayList<Record>();
            line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {

                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {

                    //Create a new record object and fill its data
                    record = new Record(Integer.parseInt(tokens[ROOM_ID]),
                            Integer.parseInt(tokens[AVAILABILITY]),
                            Integer.parseInt(tokens[GUEST_ID]));
                    System.out.println("Adding list: " + record.toString());
                    records.add(record);
                }
            }

        } catch (Exception e) {
            System.out.println("Error while reading record file!");
            e.printStackTrace();

        } finally {

            try {
                if (fileReader != null) {
                    fileReader.close();

                } else {
                    System.out.println("File reader is NULL!");
                }

            } catch (IOException e) {
                System.out.println("Error while closing file reader!");
                e.printStackTrace();
            }
        }
        return records;
    }

    public void addRecord(Record record) {

        try {
            addRecordFileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println("Error while creating record file!");
            e.printStackTrace();
        }

        records.add(record);

        try {

            addRecordFileWriter.append("" + record.getRoomId());
            addRecordFileWriter.append(COMMA_DELIMITER);

            addRecordFileWriter.append("" + record.getAvailability());
            addRecordFileWriter.append(COMMA_DELIMITER);

            addRecordFileWriter.append("" + record.getGuestId());
            addRecordFileWriter.append(NEW_LINE_SEPARATOR);

            System.out.println("Record added successfully. " + record.toString());

        } catch (Exception e) {

            System.out.println("Error while adding record to record file!");
            e.printStackTrace();

        } finally {

            try {
                addRecordFileWriter.flush();
                addRecordFileWriter.close();

            } catch (IOException e) {
                System.out.println("Error while flushing/closing file writer!");
                e.printStackTrace();
            }
        }
    }

    public void changeRecord(Record record) {
//        Record recordToChange;
//        int index = record.getRoomId();
//        records.get(index).setAvailability(record.getAvailability());
//
//        for (Record newElements : records) {
//            addRecord(newElements);
//        }
    }

    public void removeLinesOnFile(){
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
        } catch(IOException e){
            System.out.println("Error while accessing the record file!");
            e.printStackTrace();
        }
        File file = new File(FILE_NAME);
        file.delete();
    }

    public int getFirstAvailableRoom() {

        List<Record> records = RecordFile.getInstance().getRecordList();
        int index = -1;

        for (Record record : records) {
            if (record.getAvailability() == AVAILABLE) {
                index = record.getRoomId();
                break;
            }
        }
        return index;
    }
}
