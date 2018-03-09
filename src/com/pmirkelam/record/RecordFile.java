package com.pmirkelam.record;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.pmirkelam.Constants.AVAILABLE;
import static com.pmirkelam.Constants.FILE_NAME;
import static com.pmirkelam.Constants.TOTAL_ROOM_NUMBER;

public class RecordFile {

    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    private final String FILE_HEADER = "Room ID, Availability, Guest ID";
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
    private StringBuilder stringBuilder;

    private RecordFile() {
        System.out.println("RecordFile constructor");
        this.fileName = FILE_NAME;
        createRecordFile();
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
    private void createRecordFile() {

        try {
            initFileWriter = new FileWriter(fileName,true);
        } catch (IOException e) {
            System.out.println("Error while creating record file!");
            e.printStackTrace();
        }
        records = getRecordList();

        if (records == null || records.size() <= 0) {
            System.out.println("It is the first time for creating record file.");
            initCsvFile();
        } else {
            System.out.println("There is already a record file. records.size: " + records.size());

        }
    }

    /**
     * Initialize csv file for set available each room of the hotel for the first use of the program.
     */
    private void initCsvFile() {

        try {
            initFileWriter.append(FILE_HEADER);
            initFileWriter.append(NEW_LINE_SEPARATOR);
            System.out.println("Record file was created successfully. Initializing...");
            Record initRecord;
            for (int i = 1; i <= TOTAL_ROOM_NUMBER; i++) {
                initRecord = new Record(i, AVAILABLE, -1);
                records.add(initRecord);

                try {

                    initFileWriter.append("" + initRecord.getRoomId());
                    initFileWriter.append(COMMA_DELIMITER);

                    initFileWriter.append("" + initRecord.getAvailability());
                    initFileWriter.append(COMMA_DELIMITER);

                    initFileWriter.append("" + initRecord.getGuestId());
                    initFileWriter.append(NEW_LINE_SEPARATOR);

                    System.out.println("Record added successfully. " + initRecord.toString());

                } catch (Exception e) {

                    System.out.println("Error while adding record to record file!");
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Error while initializing record file!");
            e.printStackTrace();

        }
        finally {

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
       // fileReader = null;
        try {

            records = new ArrayList<Record>();
            line = "";
            fileReader = new BufferedReader(new FileReader(fileName));

            if(fileReader.readLine()!= null) {
                while ((line = fileReader.readLine()) != null) {

                    String[] tokens = line.split(COMMA_DELIMITER);

                    if (tokens.length == 3) {

                        //Create a new record object and fill its data
                        record = new Record(Integer.parseInt(tokens[0]),
                                Integer.parseInt(tokens[1]),
                                Integer.parseInt(tokens[2]));
                        System.out.println("Adding list: " + record.toString());
                        records.add(record);
                    }
                }
            } else {
                return records;
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

    /**
     *  
     * @return index of available room. If not exist return -1
     */
    public int getFirstAvailableRoom() {

        List<Record> records = RecordFile.getInstance().getRecordList();
        for (Record record : records) {
            if (record.getAvailability() == AVAILABLE) {
                return record.getRoomId();
            }
        }
        return -1;
    }

    public void addRecords(Record record) {

        int availableRoom = getFirstAvailableRoom();
        if(availableRoom != -1) {

            try {
                addRecordFileWriter = new FileWriter(fileName, true);

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
        } else {
            System.out.println("Sorry! There is no any available room now.");
        }
    }
}
