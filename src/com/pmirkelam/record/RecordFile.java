package com.pmirkelam.record;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.pmirkelam.Constants.*;

public class RecordFile {

    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";

    //private FileWriter initFileWriter;

    private String fileName;
    private List<Record> records;

    private Record record;

    private static RecordFile instance;

    private RecordFile() {
        this.fileName = FILE_NAME;
        createRecordFile();
        printList();
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
            FileWriter initFileWriter = new FileWriter(fileName, true);

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

        FileWriter initFileWriter = null;
        String FILE_HEADER = "Room ID, Availability, Guest ID";

        try {
            initFileWriter = new FileWriter(fileName, true);

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

                    System.out.println("Error while adding record to record file! initCsvFile");
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Error while initializing record file! initCsvFile");
            e.printStackTrace();

        } finally {

            try {
                if (initFileWriter != null) {
                    initFileWriter.flush();
                    initFileWriter.close();
                } else {
                    System.out.println("File writer is NULL! initCsvFile");
                }

            } catch (IOException e) {
                System.out.println("Error while flushing/closing file writer on create csv file method! initCsvFile");
                e.printStackTrace();
            }
        }
    }


    /**
     * Read the file line by line starting from the second line and assign it to 'records' list.
     */
    public List<Record> getRecordList() {

        BufferedReader bufferedReader = null;
        try {
            records = new ArrayList<Record>();
            String line = "";
            bufferedReader = new BufferedReader(new FileReader(fileName));

            if (bufferedReader.readLine() != null) {

                String[] tokens;

                for (int i = 0; i < TOTAL_ROOM_NUMBER; i++) {

                    line = bufferedReader.readLine();
                    tokens = line.split(COMMA_DELIMITER);

                    if (tokens.length == 3) {
                        //Create a new record object and fill its data
                        try {
                            record = new Record(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    Integer.parseInt(tokens[2]));
                            //System.out.println("Get list: " + record.toString());
                            records.add(record);
                        } catch (NumberFormatException e) {
                            System.out.println("List size:" + records.size() + " Last record: " + records.get(records.size() - 1).toString());
                            e.printStackTrace();
                        }
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
        return records;
    }

    public void printList() {
        List<Record> list = getRecordList();
        if (list != null && list.size() > 0) {
            System.out.println("List Size: " + list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } else {
            System.out.println("Record list is null");
        }
    }

    /**
     * @return index of available room. If not exist return -1
     */
    public int getFirstAvailableRoom() {

        List<Record> records = getRecordList();
        for (int i = 0; i < TOTAL_ROOM_NUMBER; i++) {
            if (records.get(i).getAvailability() == AVAILABLE) {
                return records.get(i).getRoomId();
            }
        }
        return -1;
    }

    public int getRoomOfGuestId(int guestId) {

        List<Record> records = getRecordList();
        for (Record record : records) {
            if (record.getGuestId() == guestId) {
                return record.getRoomId();
            }
        }
        return -1;
    }

    private void writeFile(Record replacedRecord) {

        BufferedWriter bufferedWriter = null;
        List<Record> list = getRecordList();

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));

            if (list != null) {
                System.out.println("replacedRecord.getRoomId() : " + replacedRecord.getRoomId());
                System.out.println("list.size() : " + list.size());

                list.set(replacedRecord.getRoomId()-1, replacedRecord);
                Record recordListElement;

                for (int i = 0; i < TOTAL_ROOM_NUMBER; i++) {

                    recordListElement = list.get(i);
                    try {
                        bufferedWriter.newLine();

                        bufferedWriter.write(recordListElement.getRoomId());
                        bufferedWriter.write(COMMA_DELIMITER);

                        bufferedWriter.write(recordListElement.getAvailability());
                        bufferedWriter.write(COMMA_DELIMITER);

                        bufferedWriter.write(recordListElement.getGuestId());
                        bufferedWriter.write(NEW_LINE_SEPARATOR);

                        System.out.println("Record added successfully. " + recordListElement.toString());

                    } catch (Exception e) {

                        System.out.println("Error while adding record to record file!");
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if(bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } else {
                    System.out.println("bufferedWriter is null!");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRecord(int guestId, int recordType) {

        int availableRoom = getFirstAvailableRoom();
        int reservedRoomNoOfGuestId = getRoomOfGuestId(guestId);
        int guestRoom = getRoomOfGuestId(guestId);
        Record replacedRecord;

        switch (recordType) {

            case BOOK:
                replacedRecord = new Record(availableRoom, BOOK, guestId);
                break;

            case CANCEL_RESERVATION:
                replacedRecord = new Record(guestRoom, AVAILABLE, NO_ANY_GUEST);
                break;

            case CHECK_IN:
                if (reservedRoomNoOfGuestId == -1) {
                    System.out.println("CHECK_IN , there is no reservation.");
                    replacedRecord = new Record(getFirstAvailableRoom(), CHECK_IN, guestId);
                } else {
                    System.out.println("CHECK_IN , there is reservation: " + reservedRoomNoOfGuestId);
                    replacedRecord = new Record(reservedRoomNoOfGuestId, CHECK_IN, guestId);
                }
                break;

            case CHECK_OUT:
                replacedRecord = new Record(guestRoom, AVAILABLE, NO_ANY_GUEST);
                break;

            default:
                return;
        }

        writeFile(replacedRecord);

    }
}



