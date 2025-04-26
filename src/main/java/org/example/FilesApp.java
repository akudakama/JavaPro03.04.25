package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/*
{
  "count": 2,
  "files": [
    {"name": "xxx.txt", "size": 234534543, "type": "txt"},
    {"name": "yyyy.dat", "size": 3454, "type": "dat"}
  ]
}
*/

class FileData {
    String name;
    long size;
    String type;

    @Override
    public String toString() {
        return "FileData{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}

class FilesWrapper {
    int count;
    List<FileData> files;

    public FilesWrapper() {}

    public FilesWrapper(List<FileData> files) {
        this.files = files;
        this.count = files.size();
    }

    public boolean checkCount() {
        return count == files.size();
    }

    @Override
    public String toString() {
        return "FilesWrapper{" +
                "count=" + count +
                ", files=" + files +
                '}';
    }
}

public class FilesApp {
    static final String JSON_FILES = """
        {
          "count": 2,
          "files": [
            {
              "name": "xxx.txt",
              "size": 234534543,
              "type": "txt"
            },
            {
              "name": "yyyy.dat",
              "size": 3454,
              "type": "dat"
            }
          ]
        }
        """;

    static final String JSON_ARRAY = """
        [
          {
            "name": "xxx.txt",
            "size": 234534543,
            "type": "txt"
          },
          {
            "name": "yyyy.dat",
            "size": 3454,
            "type": "dat"
          }
        ]
        """;

    public static void main(String[] args) {
        testFileArray();
        testWrapper();
    }

    private static void testFileArray() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // JSON -> FileData[]
        FileData[] files = gson.fromJson(JSON_ARRAY, FileData[].class);
        System.out.println("--- Testing FileData[] ---");

        for (FileData file : files) {
            System.out.println(file);
        }

        // Change data
        files[0].name = "new_file.docx";
        files[0].size = 5555;
        files[0].type = "docx";


        // FileData[] -> JSON
        String updatedJsonArray = gson.toJson(files);
        System.out.println("\nUpdated JSON Array:");
        System.out.println(updatedJsonArray);
    }

    private static void testWrapper() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // JSON -> FilesWrapper
        FilesWrapper wrapper = gson.fromJson(JSON_FILES, FilesWrapper.class);
        System.out.println("\n--- Testing FilesWrapper ---");

        System.out.println("Wrapper object:");
        System.out.println(wrapper);

        // checkCount
        System.out.println("Is count correct? " + wrapper.checkCount());

        // change data
        wrapper.files.get(0).name = "updated.txt";
        wrapper.files.get(0).size = 9999;
        wrapper.files.get(0).type = "log";

        // FilesWrapper -> JSON
        String updatedJson = gson.toJson(wrapper);
        System.out.println("\nUpdated JSON:");
        System.out.println(updatedJson);
    }

}
